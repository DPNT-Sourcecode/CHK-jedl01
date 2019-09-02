package befaster.solutions.CHK;

public class PriceOffer extends Offer implements Comparable<PriceOffer> {

    private Integer offerPrice;

    public PriceOffer(Integer offerRequiredQuantity, Integer offerPrice) {
        super(offerRequiredQuantity);
        this.offerPrice = offerPrice;
    }

    public Integer getOfferPrice() {
        return offerPrice;
    }

    public boolean isApplicable(Integer objectQuantity) {
        return objectQuantity >= getOfferRequiredQuantity();
    }

    public Double getUnitPrice() {
        return (double) offerPrice / getOfferRequiredQuantity();
    }

    @Override
    public int compareTo(PriceOffer o) {
        return (getUnitPrice().compareTo(o.getUnitPrice()));
    }
}
