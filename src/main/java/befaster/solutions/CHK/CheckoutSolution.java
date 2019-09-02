package befaster.solutions.CHK;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

public class CheckoutSolution {

    private Map<Character, Integer> itemsPrice = new ConcurrentHashMap(){{
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
        put('K', 70);
        put('L', 90);
        put('M', 15);
        put('N', 40);
        put('O', 10);
        put('P', 50);
        put('Q', 30);
        put('R', 50);
        put('S', 20);
        put('T', 20);
        put('U', 40);
        put('V', 50);
        put('W', 20);
        put('X', 17);
        put('Y', 20);
        put('Z', 21);
    }} ;

    private Map<Character, List<PriceOffer>> itemPriceOffers = new ConcurrentHashMap(){{
        put('A', Arrays.asList(
                new PriceOffer(3, 130),
                new PriceOffer(5, 200)
        ));
        put('B', Arrays.asList(
                new PriceOffer(2, 45)
        ));
        put('F', Arrays.asList(
                new PriceOffer(3, 20)
        ));
        put('H', Arrays.asList(
                new PriceOffer(5, 45),
                new PriceOffer(10, 80)
        ));
        put('K', Arrays.asList(
                new PriceOffer(2, 120)
        ));
        put('P', Arrays.asList(
                new PriceOffer(5, 200)
        ));
        put('Q', Arrays.asList(
                new PriceOffer(3, 80)
        ));
        put('U', Arrays.asList(
                new PriceOffer(4, 120)
        ));
        put('V', Arrays.asList(
                new PriceOffer(2, 90),
                new PriceOffer(3, 130)
        ));
    }};

    private Map<Character, CrossOffer> itemCrossOffers = new ConcurrentHashMap(){{
        put('E', new CrossOffer(2, 'B'));
        put('N', new CrossOffer(3, 'M'));
        put('R', new CrossOffer(3, 'Q'));
    }};

    private List<BundleOffer> bundleOffers = Collections.synchronizedList(Arrays.asList(
            new BundleOffer(3, new HashSet<>(Arrays.asList('S', 'T', 'X', 'Y', 'Z')), 45)
    ));

    public Integer checkout(String skus) {
        Map<Character, Integer> itemsWithQuantity = skus
                .chars().boxed()
                .collect(Collectors.toConcurrentMap(k -> ((char) k.intValue()), v -> 1, Integer::sum));

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

        for (BundleOffer bundleOffer: bundleOffers) {

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



