package befaster.solutions.CHK;

public abstract class Offer {

    private Integer quantity;

    public Offer(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }
}

