package befaster.solutions.CHK;

import java.util.*;
import java.util.stream.Collectors;

public class CheckoutSolution {

    private Map<Character, Integer> prices = new HashMap(){{
        put('A', 50);
        put('B', 30);
        put('C', 20);
        put('D', 15);
    }} ;

    private Map<Character, List<PriceOffer>> offers = new HashMap(){{
        put('A', Arrays.asList(
                new PriceOffer(3, 130, 0),
                new PriceOffer(5, 200, 1)
        ));
        put('B', Arrays.asList(
                new PriceOffer(2, 45, 0)
        ));
    }};

    private Map<Character, CrossProductOffer> crossPromotions = new HashMap(){{
        put('E', new CrossProductOffer(2, 'B'));
    }};

    public Integer checkout(String skus) {
        Map<Character, Integer> orderedSkus = skus.chars().boxed()
                .collect(Collectors.toMap(k -> ((char) k.intValue()), v -> 1, Integer::sum));

        int totalPrice = 0;

        for (Character item : orderedSkus.keySet()) {
            if (crossPromotions.containsKey(item)) {
                int itemQuantity = orderedSkus.get(item);
                CrossProductOffer offer = crossPromotions.get(item);

                if (orderedSkus.containsKey(offer.getFreeItem())) {
                    int numberOfItemBeforePromotion = orderedSkus.get(offer.getFreeItem());
                    int numberOfFreeItemDeductible =  itemQuantity / offer.getQuantity();

                    int numberOfItemAfterPromotion = Math.max(0, numberOfItemBeforePromotion - numberOfFreeItemDeductible);

                    if (numberOfItemAfterPromotion == 0) {
                        orderedSkus.remove(offer.getFreeItem());
                    } else {
                        orderedSkus.put(offer.getFreeItem(), numberOfItemAfterPromotion);
                    }
                }
            }
        }

        for (Character item : orderedSkus.keySet()) {
            if (!prices.containsKey(item)) {
                return -1;
            }

            Integer itemQuantity = orderedSkus.get(item);
            Integer itemPrice = prices.get(item);
            if (offers.containsKey(item)) {
                List<PriceOffer> itemOffers = offers.get(item);
                Collectors.so

                ;
                PriceOffer offer = (PriceOffer) offers.get(item);
                totalPrice += ( (itemQuantity / offer.getQuantity()) * offer.getNewPrice()) +
                        ( (itemQuantity % offer.getQuantity()) * itemPrice);
            } else {
                totalPrice += itemQuantity * itemPrice;
            }
        }

        return totalPrice;
    }
}



