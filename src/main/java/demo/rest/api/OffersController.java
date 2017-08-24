package demo.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(path = "/offers")
public class OffersController {

    @Autowired
    private OffersService offersService;

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(method = POST)
    public ResponseEntity<Void> addOffer(@RequestBody OfferInfo info) {
        long id = counter.incrementAndGet();
        Offer newOffer = new Offer(id, info);

        boolean added = offersService.addOffer(newOffer);

        if (added) {
            System.out.println("Added");
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(id).toUri();
            return ResponseEntity.created(location).build();
        }
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = GET)
    public ResponseEntity<List<Offer>> getAllOffers() {
        List<Offer> offers = offersService.getAllOffers();
        if (offers == null || offers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(offers);
    }

    @RequestMapping(value = "{id}", method = GET)
    public ResponseEntity<Offer> getOffer(@PathVariable long id) {
        Offer offer = offersService.getOfferById(id);
        if (offer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(offer);
    }

    @RequestMapping(value = "{id}", method = DELETE)
    public ResponseEntity<Void> deleteOffer(@PathVariable long id) {
        boolean deleted = offersService.deleteOffer(id);

        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "{id}", method = PUT)
    public ResponseEntity<Offer> updateOffer(@PathVariable long id, @RequestBody OfferInfo info) {
        Offer updatedOffer = new Offer(id, info);
        Offer result = offersService.updateOffer(updatedOffer);

        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
