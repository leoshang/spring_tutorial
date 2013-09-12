package rewards;

/*import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;*/

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import rewards.internal.restaurant.Restaurant;

@Configuration
@Import(ConfigA.class)
public class ConfigB {

	@Bean
	public Restaurant restaurant(){
		System.out.println("initiate Restaurant in ConfigB");
		return new Restaurant("restaurantNumber", "aRestaurant");
	}
	
	public static void main(String[] args){
		ApplicationContext context = new AnnotationConfigApplicationContext(ConfigB.class);
		ConfigA  a = context.getBean(ConfigA.class);
		ConfigB b= context.getBean(ConfigB.class);
	}
}
