package demo.rest.api;

import java.util.List;

public interface OffersService {

    List<Offer> getAllOffers();
    Offer getOfferById(long id);
    boolean addOffer(Offer newOffer);
}