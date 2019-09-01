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
        put('A', new PriceOffer(3, 130));
        put('B', new PriceOffer(2, 45));
    }};

    private Map<Character, Character> crossPromotion = new HashMap(){{
        put('E', 'B');
    }};

    public Integer checkout(String skus) {
        Map<Character, Integer> orderedSkus = skus.chars().boxed()
                .collect(Collectors.toMap(k -> ((char) k.intValue()), v -> 1, Integer::sum));

        int totalPrice = 0;

        for (Character c : orderedSkus.keySet()) {
            if (crossPromotion.containsKey(c)) {
                Character reducedItem = crossPromotion.get(c);

                if (orderedSkus.containsKey(crossPromotion)) {

                }
            }
        }

        for (Character c : orderedSkus.keySet()) {
            if (!prices.containsKey(c)) {
                return -1;
            }

            Integer itemQuantity = orderedSkus.get(c);
            Integer itemPrice = prices.get(c);
            if (offers.containsKey(c)) {
                PriceOffer offer = (PriceOffer) offers.get(c);
                totalPrice += ( (itemQuantity / offer.getQuantity()) * offer.getNewPrice()) +
                        ( (itemQuantity % offer.getQuantity()) * itemPrice);
            } else {
                totalPrice += itemQuantity * itemPrice;
            }
        }

        return totalPrice;
    }
}


