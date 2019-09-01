package befaster.solutions.CHK;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CheckoutSolution {

    private Map<Character, Integer> prices = new HashMap(){{
        put('A', 50);
        put('B', 30);
        put('C', 20);
        put('D', 15);
    }} ;

    private Map<Character, Offer> offers = new HashMap(){{
        put('A', new Offer(3, 130));
        put('B', new Offer(2, 45));
    }};

    public Integer checkout(String skus) {
        Map<Character, Integer> orderedSkus = skus.chars().boxed()
                .collect(Collectors.toMap(k -> ((char) k.intValue()), v -> 1, Integer::sum));

        int totalPrice = 0;
        for (Character c : orderedSkus.keySet()) {
            if (!prices.containsKey(c)) {
                return -1;
            }

            Integer itemQuantity = orderedSkus.get(c);
            Integer itemPrice = prices.get(c);
            if (offers.containsKey(c)) {
                Offer offer = offers.get(c);
                totalPrice += ( (itemQuantity / offer.getQuantity()) * offer.getPrice()) +
                        ( (itemQuantity % offer.getQuantity()) * itemPrice);
            } else {
                totalPrice += itemQuantity * itemPrice;
            }
        }

        return totalPrice;
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

