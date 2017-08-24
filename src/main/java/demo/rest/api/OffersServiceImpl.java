package demo.rest.api;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OffersServiceImpl implements OffersService {

    private static List<Offer> offers = new ArrayList<>();

    @Override
    public List<Offer> getAllOffers() {
        return new ArrayList<>(offers);
    }

    @Override
    public Offer getOfferById(long id) {
        for(Offer offer : offers) {
            if (offer.getId() == id) {
                return offer;
            }
        }
        return null;
    }

    @Override
    public boolean addOffer(Offer newOffer) {
        return offers.add(newOffer);
    }
}