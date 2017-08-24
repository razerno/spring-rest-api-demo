package demo.rest.api;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

    @Test
    public void testGetOfferDoesNotExist() {
        assertEquals(offersService.getAllOffers(), new ArrayList<Offer>());
        assertEquals(offersService.getOfferById(1), null);
    }

    @Test
    public void testDeleteOffer() {
        Offer newOffer = new Offer(1, "A simple offer", 50, "GBP");

        assertTrue(offersService.addOffer(newOffer));
        assertTrue(offersService.deleteOffer(1));
        assertEquals(offersService.getOfferById(1), null);
    }

    @Test
    public void testDeleteOfferDoesNotExist() {
        assertFalse(offersService.deleteOffer(1));
    }

    @Test
    public void testUpdateOffer() {
        Offer newOffer = new Offer(1, "Initial Offer", 10, "GBP");
        Offer updatedOffer = new Offer(1, "Updated Offer", 20, "USD");

        assertTrue(offersService.addOffer(newOffer));
        assertEquals(offersService.updateOffer(updatedOffer), updatedOffer);
        assertEquals(offersService.getOfferById(1), updatedOffer);
    }

    @Test
    public void testUpdateOfferDoesNotExist() {
        Offer updatedOffer = new Offer(1, "Updated Offer", 20, "USD");

        assertEquals(offersService.updateOffer(updatedOffer), null);
    }
}
