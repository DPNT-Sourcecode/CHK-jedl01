package befaster.solutions.CHK;

public class PriceOffer extends Offer implements Comparable<PriceOffer> {

    private Integer newPrice;
    private Integer offerApplicationOrder;

    public PriceOffer(Integer quantity, Integer newPrice, Integer offerApplicationOrder) {
        super(quantity);
        this.newPrice = newPrice;
        this.offerApplicationOrder = offerApplicationOrder;
    }

    public Integer getNewPrice() {
        return newPrice;
    }

    public Integer getOfferApplicationOrder() {
        return offerApplicationOrder;
    }

    public boolean isApplicable(Integer objectQuantity) {
        return objectQuantity >= getQuantity();
    }

    @Override
    public int compareTo(PriceOffer o) {
        return offerApplicationOrder.compareTo(o.offerApplicationOrder);
    }
}
