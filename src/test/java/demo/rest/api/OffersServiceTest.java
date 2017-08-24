package demo.rest.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class OffersServiceTest {

    @TestConfiguration
    static class OffersServiceImplTestContextConfig {

        @Bean
        public OffersService offersService() {
            return new OffersServiceImpl();
        }
    }

    @Autowired
    private OffersService offersService;

    @Test
    public void testGetAndAddOffer() {
        Offer newOffer = new Offer(1, "A simple offer", 50, "GBP");
        assertTrue(offersService.getAllOffers().isEmpty());
        assertTrue(offersService.addOffer(newOffer));
        assertEquals(offersService.getOfferById(1), newOffer);
    }
}
