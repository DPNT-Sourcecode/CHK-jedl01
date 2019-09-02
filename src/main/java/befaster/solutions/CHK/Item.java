package befaster.solutions.CHK;

import java.util.Objects;

public class Item implements Comparable<Item> {

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

    @Override
    public int compareTo(Item o) {
        return getPrice().compareTo(o.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(item);
    }
}


