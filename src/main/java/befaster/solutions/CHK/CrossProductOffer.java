package befaster.solutions.CHK;

public class CrossProductOffer extends Offer {

    private Character freeItem;

    public CrossProductOffer(Integer quantity, Character freeItem) {
        super(quantity);
        this.freeItem = freeItem;
    }

    public Character getFreeItem() {
        return freeItem;
    }
}
