package befaster.solutions.CHK;

public abstract class Offer {

    private Integer offerRequiredQuantity;

    public Offer(Integer offerRequiredQuantity) {
        this.offerRequiredQuantity = offerRequiredQuantity;
    }

    public Integer getOfferRequiredQuantity() {
        return offerRequiredQuantity;
    }
}
