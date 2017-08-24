package demo.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OffersController.class)
public class OffersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OffersService offersService;

    @Test
    public void postShouldAddOffer() throws Exception {
        OfferInfo newOfferInfo = new OfferInfo("A simple offer", 50, "GBP");
        Offer newOffer = new Offer(1, "A simple offer", 50, "GBP");

        when(offersService.addOffer(newOffer)).thenReturn(true);
        this.mockMvc.perform(post("/offers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newOfferInfo)))
                .andExpect(status().isCreated());
        verify(offersService, times(1)).addOffer(newOffer);
    }

    @Test
    public void getShouldGetAllOffers() throws Exception {
        List<Offer> offers = Arrays.asList(
                new Offer(1,"First offer", 10, "GBP"),
                new Offer(2,"Second offer", 20, "USD")
        );

        when(offersService.getAllOffers()).thenReturn(offers);
        this.mockMvc.perform(get("/offers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].description", is("First offer")))
                .andExpect(jsonPath("$[0].price", is(10)))
                .andExpect(jsonPath("$[0].currency", is("GBP")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].description", is("Second offer")))
                .andExpect(jsonPath("$[1].price", is(20)))
                .andExpect(jsonPath("$[1].currency", is("USD")));
        verify(offersService, times(1)).getAllOffers();
    }

    @Test
    public void getShouldGiveNoContentIfNoOffersExist() throws Exception {
        List<Offer> emptyList = new ArrayList<>();

        when(offersService.getAllOffers()).thenReturn(emptyList);
        this.mockMvc.perform(get("/offers"))
                .andExpect(status().isNoContent());
        verify(offersService, times(1)).getAllOffers();
    }

    @Test
    public void getWithIdShouldGetSpecificOffer() throws Exception {
        Offer offer = new Offer(1, "A simple offer", 50, "GBP");

        when(offersService.getOfferById(1)).thenReturn(offer);
        this.mockMvc.perform(get("/offers/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.description", is("A simple offer")))
                .andExpect(jsonPath("$.price", is(50)))
                .andExpect(jsonPath("$.currency", is("GBP")));
        verify(offersService, times(1)).getOfferById(1);
    }

    @Test
    public void getWithIdShouldGiveNotFoundIfOfferDoesNotExist() throws Exception {
        when(offersService.getOfferById(1)).thenReturn(null);
        this.mockMvc.perform(get("/offers/{id}", 1))
                .andExpect(status().isNotFound());
        verify(offersService, times(1)).getOfferById(1);
    }
}
