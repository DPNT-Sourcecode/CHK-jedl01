package befaster.solutions.CHK;

public class PriceOffer extends Offer implements Comparable<PriceOffer> {

    private Integer offerPrice;
    private Integer offerApplicationOrder;

    public PriceOffer(Integer offerRequiredQuantity, Integer offerPrice, Integer offerApplicationOrder) {
        super(offerRequiredQuantity);
        this.offerPrice = offerPrice;
        this.offerApplicationOrder = offerApplicationOrder;
    }

    public Integer getOfferPrice() {
        return offerPrice;
    }

    public Integer getOfferApplicationOrder() {
        return offerApplicationOrder;
    }

    public boolean isApplicable(Integer objectQuantity) {
        return objectQuantity >= getOfferRequiredQuantity();
    }

    @Override
    public int compareTo(PriceOffer o) {
        return offerApplicationOrder.compareTo(o.offerApplicationOrder);
    }
}

