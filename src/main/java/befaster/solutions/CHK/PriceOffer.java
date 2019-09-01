package befaster.solutions.CHK;

public class PriceOffer extends Offer implements Comparable {

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

    @Override
    public int compareTo(Object o) {
        PriceOffer that = (PriceOffer) o;

        return offerApplicationOrder.compareTo(that.offerApplicationOrder);
    }
}


