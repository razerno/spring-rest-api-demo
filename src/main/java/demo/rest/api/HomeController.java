package demo.rest.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String greeting() {
        return "Offers can be accessed at /offers";
    }
}
