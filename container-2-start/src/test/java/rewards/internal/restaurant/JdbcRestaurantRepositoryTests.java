package rewards.internal.restaurant;

import common.money.Percentage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests the JDBC restaurant repository with a test data source to verify data access and relational-to-object mapping
 * behavior works as expected.
 */
public class JdbcRestaurantRepositoryTests {

	private JdbcRestaurantRepository repository;
	private ConfigurableApplicationContext context;

	@Before
	public void setUp() throws Exception {
		// TODO 1: lastly, initialize the bean
		System.out.println("setup called");
		context = new ClassPathXmlApplicationContext(new String[]{"classpath:rewards/internal/application-config.xml","classpath:rewards/test-infrastructure-config.xml"});
		repository = (JdbcRestaurantRepository)context.getBean(JdbcRestaurantRepository.class);
	}

	@After
	public void tearDown() throws Exception {
		// simulate the Spring bean destruction lifecycle:
		/*
		 * ApplicaitonContext doesn't provide the close() interface. ConfigurableApplicationContext does!
		 */
		// TODO 2: destroy the bean
		context.close();
		System.out.println("teardown called");
	}

	@Test
	public void findRestaurantByMerchantNumber() {
		System.out.println("Start testing findRestaurantByMerchantNumber");
		Restaurant restaurant = repository.findByMerchantNumber("1234567890");
		assertNotNull("restaurant is null - check your repositories cache", restaurant);
		assertEquals("number is wrong", "1234567890", restaurant.getNumber());
		assertEquals("name is wrong", "AppleBees", restaurant.getName());
		assertEquals("benefitPercentage is wrong", Percentage.valueOf("8%"), restaurant.getBenefitPercentage());
		System.out.println("End testing findRestaurantByMerchantNumber");
        Restaurant restaurant2 = repository.findByMerchantNumber("1234567890");
	}

	@Test
	public void findRestaurantByBogusMerchantNumber() {
		System.out.println("Start testing findRestaurantByBogusMerchantNumber");
		try {
			repository.findByMerchantNumber("bogus");
		} catch (EmptyResultDataAccessException e) {
			// expected
			System.err.println(e.getMessage());
		}
		System.out.println("End testing findRestaurantByBogusMerchantNumber");
	}

	@Test
	public void restaurantCacheClearedAfterDestroy() throws Exception {
		// force early tear down
		System.out.println("Start testing restaurantCacheClearedAfterDestroy");
		tearDown();
		try {
			// try what normally is a valid number
			repository.findByMerchantNumber("1234567890");
		} catch (EmptyResultDataAccessException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("End testing restaurantCacheClearedAfterDestroy");
	}

	private DataSource createTestDataSource() {
		return new EmbeddedDatabaseBuilder()
			.setName("rewards")
			.addScript("/rewards/testdb/schema.sql")
			.addScript("/rewards/testdb/test-data.sql")
			.build();
	}
}
