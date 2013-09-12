package rewards.internal.restaurant;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import common.money.Percentage;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Loads restaurants from a data source using the JDBC API.
 *
 * This implementation should cache restaurants to improve performance. The cache should be populated on initialization
 * and cleared on destruction.
 */
public class JdbcRestaurantRepository implements RestaurantRepository {

    final Logger logger = LoggerFactory.getLogger(JdbcRestaurantRepository.class);
	private DataSource dataSource;

	/**
	 * The Restaurant object cache manager. Cached restaurants are indexed by their merchant numbers.
	 */

    public CacheManager getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    private CacheManager cacheManager;

    /**
	 * Sets the data source this repository will use to load restaurants.
	 *
	 * @param dataSource the data source
	 */
	@Required
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Restaurant findByMerchantNumber(String merchantNumber) {
		// TODO 3: query the restaurant cache to find the restaurant with the
        logger.debug("testing...888888888888888888888888888888888888888888...testing");
        return queryRestaurantCache(merchantNumber);

        /*Restaurant restaurant = getRestaurants();
        if(restaurant==null || !restaurant.getNumber().equals(merchantNumber)){
            throw new EmptyResultDataAccessException("cannot find the restaurant["+merchantNumber+"] in the cache.",1);
        }
        return restaurant;
        */

	}

    /**
     * Testing Ehcache Annotations for Spring from  https://code.google.com/p/ehcache-spring-annotations/
     *
       NOTE, CacheKeyGenerator needs to be implemented!

    @Cacheable(cacheName = "restaurant")
    Restaurant getRestaurants(){
        Restaurant restaurant = null;
        String sql = "select MERCHANT_NUMBER, NAME, BENEFIT_PERCENTAGE from T_RESTAURANT";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                restaurant = mapRestaurant(rs);
                return restaurant;
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception occurred finding by merchant number", e);
        } finally {
            if (rs != null) {
                try {
                    // Close to prevent database cursor exhaustion
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (ps != null) {
                try {
                    // Close to prevent database cursor exhaustion
                    ps.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    // Close to prevent database connection exhaustion
                    conn.close();
                } catch (SQLException ex) {
                }
            }
            return restaurant;
        }
    } */

    /**
     * Helper method that populates the restaurant object from rows in the T_RESTAURANT
     * table. Cached restaurants are indexed by their merchant numbers. This method should be called on initialization.
     */
    @PostConstruct
    void populateRestaurantCache(){
        Cache cache = cacheManager.getCache("restaurant");
        String sql = "select MERCHANT_NUMBER, NAME, BENEFIT_PERCENTAGE from T_RESTAURANT";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Restaurant restaurant = mapRestaurant(rs);
                // index the restaurant by its merchant number
                Element element = new Element(restaurant.getNumber(), restaurant);
                cache.put(element);

            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception occurred finding by merchant number", e);
        } finally {
            if (rs != null) {
                try {
                    // Close to prevent database cursor exhaustion
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (ps != null) {
                try {
                    // Close to prevent database cursor exhaustion
                    ps.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    // Close to prevent database connection exhaustion
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
    }


	/**
	 * Helper method that simply queries the cache of restaurants.
	 *
	 * @param merchantNumber the restaurant's merchant number
	 * @return the restaurant
	 * @throws EmptyResultDataAccessException if no restaurant was found with that merchant number
	 */
	private Restaurant queryRestaurantCache(String merchantNumber) {
        Cache cache = null;
        try{
            cache= cacheManager.getCache("restaurant");
        }catch (Exception e){
            throw new EmptyResultDataAccessException(e.getMessage(), 1);
        }
        if(cache == null || cache.get(merchantNumber) == null){
            throw new EmptyResultDataAccessException("cannot find the restaurant["+merchantNumber+"] in the cache.",1);
        }
        Element element=cache.get(merchantNumber);
		Restaurant restaurant = (Restaurant)element.getObjectValue();
		if (restaurant == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return restaurant;
	}

	/**
	 * Helper method that clears the cache of restaurants.  This method should be called on destruction
	 */
	// TODO 2: Register for a destruction lifecycle callback that will execute this method and clear the cache
	@PreDestroy
	void clearRestaurantCache() {
		System.out.println("clear restaurant caching");
        Cache restaurantCache= cacheManager.getCache("restaurant");
		restaurantCache.removeAll();
	}

    /**
     * Testing Ehcache Annotations for Spring from  https://code.google.com/p/ehcache-spring-annotations/
     * */
    @PreDestroy @TriggersRemove(cacheName = "restaurant")
    void clearCache(){

    }

	/**
	 * Maps a row returned from a query of T_RESTAURANT to a Restaurant object.
	 *
	 * @param rs the result set with its cursor positioned at the current row
	 */
	private Restaurant mapRestaurant(ResultSet rs) throws SQLException {
		// get the row column data
		String name = rs.getString("NAME");
		String number = rs.getString("MERCHANT_NUMBER");
		Percentage benefitPercentage = Percentage.valueOf(rs.getString("BENEFIT_PERCENTAGE"));
		// map to the object
		Restaurant restaurant = new Restaurant(number, name);
		restaurant.setBenefitPercentage(benefitPercentage);
		return restaurant;
	}
}