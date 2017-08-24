package demo.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class OffersController {

    @Autowired
    private OffersService offersService;

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(path="/offers", method=POST)
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
        else {
            return ResponseEntity.noContent().build();
        }
    }

}
