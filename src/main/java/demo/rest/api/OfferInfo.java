package demo.rest.api;

import java.io.Serializable;

public class OfferInfo implements Serializable {

    private String description;
    private long price;
    private String currency;

    public String getDescription() {
        return description;
    }

    public long getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public OfferInfo(String description, long price, String currency) {
        this.description = description;
        this.price = price;
        this.currency = currency;
    }

    public OfferInfo() {
    }
}
