package rewards.internal;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import rewards.RewardNetwork;
import rewards.internal.account.AccountRepository;
import rewards.internal.account.JdbcAccountRepository;
import rewards.internal.restaurant.JdbcRestaurantRepository;
import rewards.internal.restaurant.RestaurantRepository;
import rewards.internal.reward.JdbcRewardRepository;
import rewards.internal.reward.RewardRepository;


//Commented out to prevent this class from being picked up by component scanning,
//as the classes involved will be component scanned themselves already and this
//would otherwise result in double bean definitions.
//@Configuration
public class TestJavaBasedConfig {

	@Autowired
	private DataSource dataSource;
	
	@Bean
	public RewardNetwork rewardNetwork(){
		return new RewardNetworkImpl(accountRepository(), restaurantRepository(), rewardRepository());
	}
	
	@Bean
	public AccountRepository accountRepository(){
		JdbcAccountRepository ja = new JdbcAccountRepository();
		ja.setDataSource(dataSource);
		return ja;
	}
	
	@Bean
	public RestaurantRepository restaurantRepository(){
		JdbcRestaurantRepository jr = new JdbcRestaurantRepository();
		jr.setDataSource(dataSource);
		return jr;
	}
	
	@Bean
	public RewardRepository rewardRepository(){
		JdbcRewardRepository jrr = new JdbcRewardRepository();
		jrr.setDataSource(dataSource);
		return jrr;
	}
	
}
