package befaster.solutions.CHK;

public class Item {

    private Character item;
    private Integer price;

    public Item(Character item, Integer price) {
        this.item = item;
        this.price = price;
    }

    public Character getItem() {
        return item;
    }

    public Integer getPrice() {
        return price;
    }
}

