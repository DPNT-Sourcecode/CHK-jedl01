package befaster.solutions.CHK;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CheckoutSolution {

    private Map<String, Integer> prices = new HashMap(){{
        put("A", 50);
        put("B", 30);
        put("C", 20);
        put("D", 15);
    }} ;

    private Map<String, Offer> offers = new HashMap(){{
        put("A", new Offer(3, 130));
        put("B", new Offer(2, 45));
    }};

    public Integer checkout(String skus) {
        Map<Character, Integer> orderedSkus= skus.chars().boxed()
                .collect(Collectors.toMap(
                        k -> (char) k.intValue(),
                        v -> 1,
                        Integer::sum))
                );
    }

    private class Offer {
        private Integer quantity;
        private Integer price;

        public Offer(Integer quantity, Integer price) {
            this.quantity = quantity;
            this.price = price;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public Integer getPrice() {
            return price;
        }
    }
}



