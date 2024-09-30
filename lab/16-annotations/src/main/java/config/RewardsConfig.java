package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import rewards.RewardNetwork;
import rewards.internal.RewardNetworkImpl;
import rewards.internal.account.AccountRepository;
import rewards.internal.account.JdbcAccountRepository;
import rewards.internal.restaurant.JdbcRestaurantRepository;
import rewards.internal.restaurant.RestaurantRepository;
import rewards.internal.reward.JdbcRewardRepository;
import rewards.internal.reward.RewardRepository;

import javax.sql.DataSource;

/**
 * TODO-07: Perform component-scanning and run the test again
 * - Add an appropriate annotation to this class to cause component scanning.
 * - Set the base package to pick up all the classes we have annotated so far.
 * - Save all changes, Re-run the RewardNetworkTests.  It should now pass.
 */
@Configuration
@ComponentScan("rewards.internal")
public class RewardsConfig {

	DataSource dataSource;

	public RewardsConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
		
	public RewardNetwork rewardNetwork(){
		return new RewardNetworkImpl(
			accountRepository(), 
			restaurantRepository(), 
			rewardRepository());
	}
	

	public AccountRepository accountRepository(){
		JdbcAccountRepository repository = new JdbcAccountRepository();
		repository.setDataSource(dataSource);
		return repository;
	}
	
	public RestaurantRepository restaurantRepository(){
		JdbcRestaurantRepository repository = new JdbcRestaurantRepository(dataSource);
		return repository;
	}
	
	public RewardRepository rewardRepository(){
		JdbcRewardRepository repository = new JdbcRewardRepository();
		repository.setDataSource(dataSource);
		return repository;
	}
	
	// TODO-02: Remove all of the @Bean methods above.
	// - Remove the code that autowires DataSource as well.
    // - Run the RewardNetworkTests test. It should fail. Why?
	
}
