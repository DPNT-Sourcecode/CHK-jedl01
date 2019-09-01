package befaster.solutions.CHK;

import java.util.*;
import java.util.stream.Collectors;

public class CheckoutSolution {

    private Map<Character, Integer> prices = new HashMap(){{
        put('A', 50);
        put('B', 30);
        put('C', 20);
        put('D', 15);
        put('E', 40);
    }} ;

    private Map<Character, List<PriceOffer>> offers = new HashMap(){{
        put('A', Arrays.asList(
                new PriceOffer(3, 130, 1),
                new PriceOffer(5, 200, 0)
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

            int itemQuantity = orderedSkus.get(item);
            int itemPrice = prices.get(item);
            if (offers.containsKey(item)) {
                List<PriceOffer> itemOffers = offers.get(item);
                Collections.sort(itemOffers);

                for (PriceOffer offer: itemOffers) {
                    while(offer.isApplicable(itemQuantity)) {
                        totalPrice += offer.getNewPrice();
                        itemQuantity -= offer.getQuantity();
                    }
                }
            }

            totalPrice += itemQuantity * itemPrice;
        }

        return totalPrice;
    }
}



