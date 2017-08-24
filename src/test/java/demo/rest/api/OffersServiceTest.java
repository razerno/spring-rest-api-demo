package demo.rest.api;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OffersServiceTest {

    private OffersService offersService;

    @Before
    public void setUp() {
        offersService = new OffersServiceImpl();
    }

    @Test
    public void testGetAndAddOffer() {
        Offer newOffer = new Offer(1, "A simple offer", 50, "GBP");

        assertTrue(offersService.getAllOffers().isEmpty());
        assertTrue(offersService.addOffer(newOffer));
        assertEquals(offersService.getOfferById(1), newOffer);
    }

    @Test
    public void testGetAllOffers() {
        Offer offer1 = new Offer(1,"First offer", 10, "GBP");
        Offer offer2 = new Offer(2,"Second offer", 20, "USD");
        List<Offer> offers = Arrays.asList(offer1, offer2);

        assertTrue(offersService.addOffer(offer1));
        assertTrue(offersService.addOffer(offer2));
        assertEquals(offersService.getAllOffers(), offers);
    }
}
