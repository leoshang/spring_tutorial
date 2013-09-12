package rewards;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import common.money.MonetaryAmount;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import rewards.internal.RewardNetworkImpl;

/**
 * Created with IntelliJ IDEA.
 * User: Lei
 * Date: 10/09/13
 * Time: 10:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class RewardNetworkTest {

    RewardNetwork rewardNetwork;
    @Before
    public void setUp(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/rewards/internal/application-config.xml","classpath:/rewards/internal/infrastructure-config.xml");
        rewardNetwork = context.getBean("rewardNetowrk", RewardNetworkImpl.class);
    }

    @Test
    public void testRewardForDining(){
        Dining dining = Dining.createDining("100.00", "1234123412341234", "1234567890");

        RewardConfirmation confirmation= rewardNetwork.rewardAccountFor(dining);

        assertNotNull(confirmation);
        assertNotNull(confirmation.getConfirmationNumber());
        AccountContribution contribution = confirmation.getAccountContribution();
        assertNotNull(contribution);
        // the account number should be '123456789'
        assertEquals("123456789", contribution.getAccountNumber());

        // the total contribution amount should be 8.00 (8% of 100.00)
        assertEquals(MonetaryAmount.valueOf("8.00"), contribution.getAmount());

        // the total contribution amount should have been split into 2 distributions
        assertEquals(2, contribution.getDistributions().size());

        // each distribution should be 4.00 (as both have a 50% allocation)
        assertEquals(MonetaryAmount.valueOf("4.00"), contribution.getDistribution("Annabelle").getAmount());
        assertEquals(MonetaryAmount.valueOf("4.00"), contribution.getDistribution("Corgan").getAmount());
    }
}
