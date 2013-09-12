package rewards;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import rewards.internal.account.Account;


@Configuration
public class ConfigA {

	@Bean
	public Account accountContribution(){
		System.out.println("initiate Account in ConfigA");
		return new Account("configA", "configA");
	}
}
