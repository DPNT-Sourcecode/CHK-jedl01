package befaster.solutions.CHK;

import java.util.*;
import java.util.stream.Collectors;

public class CheckoutSolution {

    private Map<Character, Integer> itemsPrice = new HashMap(){{
        put('A', 50);
        put('B', 30);
        put('C', 20);
        put('D', 15);
        put('E', 40);
        put('F', 10);
        put('G', 20);
        put('H', 10);
        put('I', 35);
        put('J', 60);
        put('K', 80);
        put('L', 90);
        put('M', 15);
        put('N', 40);
        put('O', 10);
        put('P', 50);
        put('Q', 30);
        put('R', 50);
        put('S', 30);
        put('T', 20);
        put('U', 40);
        put('V', 50);
        put('W', 20);
        put('X', 90);
        put('Y', 10);
        put('Z', 50);
    }} ;

    private Map<Character, List<PriceOffer>> itemPriceOffers = new HashMap(){{
        put('A', Arrays.asList(
                new PriceOffer(3, 130, 1),
                new PriceOffer(5, 200, 0)
        ));
        put('B', Arrays.asList(
                new PriceOffer(2, 45, 0)
        ));
        put('F', Arrays.asList(
                new PriceOffer(3, 20, 0)
        ));
        put('H', Arrays.asList(
                new PriceOffer(5, 45, 0),
                new PriceOffer(10, 20, 0)
        ));
    }};

    private Map<Character, CrossOffer> itemCrossOffers = new HashMap(){{
        put('E', new CrossOffer(2, 'B'));
    }};

    public Integer checkout(String skus) {
        Map<Character, Integer> itemsWithQuantity = skus
                .chars().boxed()
                .collect(Collectors.toMap(k -> ((char) k.intValue()), v -> 1, Integer::sum));

        for (Character item : itemsWithQuantity.keySet()) {
            if (itemCrossOffers.containsKey(item)) {
                // This item will trigger a cross item offer (another item free)
                int itemQuantity = itemsWithQuantity.get(item);
                CrossOffer crossItemOffer = itemCrossOffers.get(item);

                int nbItemsBeforeCrossOffer = itemsWithQuantity.getOrDefault(crossItemOffer.getFreeItem(), 0);
                int nbItemsDeductible =  itemQuantity / crossItemOffer.getOfferRequiredQuantity();

                int nbItemsAfterCrossOffer = Math.max(0, nbItemsBeforeCrossOffer - nbItemsDeductible);

                if (nbItemsAfterCrossOffer == 0 && itemsWithQuantity.containsKey(crossItemOffer.getFreeItem())) {
                    itemsWithQuantity.remove(crossItemOffer.getFreeItem());
                } else {
                    itemsWithQuantity.put(crossItemOffer.getFreeItem(), nbItemsAfterCrossOffer);
                }
            }
        }

        int checkoutPrice = 0;
        for (Character item : itemsWithQuantity.keySet()) {
            if (!itemsPrice.containsKey(item)) {
                return -1;
            }

            int itemQuantity = itemsWithQuantity.get(item);
            int itemUnitPrice = itemsPrice.get(item);

            if (itemPriceOffers.containsKey(item)) {
                List<PriceOffer> currentItemOffers = itemPriceOffers.get(item);
                Collections.sort(currentItemOffers);

                for (PriceOffer offer: currentItemOffers) {

                    while(offer.isApplicable(itemQuantity)) {
                        checkoutPrice += offer.getOfferPrice();
                        itemQuantity -= offer.getOfferRequiredQuantity();
                    }
                }
            }

            // Remaining items without any offer
            checkoutPrice += itemQuantity * itemUnitPrice;
        }

        return checkoutPrice;
    }
}


