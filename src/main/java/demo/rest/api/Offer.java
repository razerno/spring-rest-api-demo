package demo.rest.api;

public class Offer {

    private final long id;
    private final String description;
    private final long price;
    private final String currency;

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public long getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Offer offer = (Offer) o;

        if (id != offer.id) return false;
        if (price != offer.price) return false;
        if (!description.equals(offer.description)) return false;
        return currency.equals(offer.currency);
    }

    public Offer(long id, String description, long price, String currency) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.currency = currency;
    }

    public Offer(long id, OfferInfo info) {
        this.id = id;
        this.description = info.getDescription();
        this.price = info.getPrice();
        this.currency = info.getCurrency();
    }
}
