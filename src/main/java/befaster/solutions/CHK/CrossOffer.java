package befaster.solutions.CHK;

public class CrossOffer extends Offer {

    private Character freeItem;

    public CrossOffer(Integer offerRequiredQuantity, Character freeItem) {
        super(offerRequiredQuantity);
        this.freeItem = freeItem;
    }

    public Character getFreeItem() {
        return freeItem;
    }
}

