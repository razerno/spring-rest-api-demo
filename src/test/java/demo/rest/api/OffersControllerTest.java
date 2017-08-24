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

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
}
