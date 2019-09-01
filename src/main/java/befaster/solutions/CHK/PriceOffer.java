package befaster.solutions.CHK;

public class PriceOffer extends Offer {

    private Integer newPrice;

    public PriceOffer(Integer quantity, Integer newPrice) {
        super(quantity);
        this.newPrice = newPrice;
    }

    public Integer getNewPrice() {
        return newPrice;
    }
}

