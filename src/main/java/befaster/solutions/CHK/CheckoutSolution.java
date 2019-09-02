package befaster.solutions.CHK;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class CheckoutSolution {

    private Map<Character, Item> itemsPrice = new ConcurrentHashMap(){{
        put('A', new Item('A', 50));
        put('B', new Item('B', 30));
        put('C', new Item('C', 20));
        put('D', new Item('D', 15));
        put('E', new Item('E', 40));
        put('F', new Item('F', 10));
        put('G', new Item('G', 20));
        put('H', new Item('H', 10));
        put('I', new Item('I', 35));
        put('J', new Item('J', 60));
        put('K', new Item('K', 70));
        put('L', new Item('L', 90));
        put('M', new Item('M', 15));
        put('N', new Item('N', 40));
        put('O', new Item('O', 10));
        put('P', new Item('P', 50));
        put('Q', new Item('Q', 30));
        put('R', new Item('R', 50));
        put('S', new Item('S', 20));
        put('T', new Item('T', 20));
        put('U', new Item('U', 40));
        put('V', new Item('V', 50));
        put('W', new Item('W', 20));
        put('X', new Item('X', 17));
        put('Y', new Item('Y', 20));
        put('Z', new Item('Z', 21));
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
            new BundleOffer(3, 45,
                    Arrays.asList(
                        itemsPrice.get('S'),
                        itemsPrice.get('T'),
                        itemsPrice.get('X'),
                        itemsPrice.get('Y'),
                        itemsPrice.get('Z')
                    )
            )
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

        int checkoutPrice = 0;
        for (BundleOffer bundleOffer: bundleOffers) {
            List<Item> bundlableItems = bundleOffer.getBundlableItems();
            Collections.sort(bundlableItems);
            Collections.reverse(bundlableItems);

            // Sorted by decreasing price so we add most expensive items into bundles
            Map<Item, Integer> bundlableItemsQuantityMap = new ConcurrentHashMap<>();
            int nbItemsWaitingFullBundle = 0;

            for (Item item: bundlableItems) {
                if (!itemsWithQuantity.containsKey(item.getItem())) {
                    continue;
                }

                int newItemQuantity = itemsWithQuantity.get(item.getItem());

                int totalItemBundlable = nbItemsWaitingFullBundle + newItemQuantity;
                if (totalItemBundlable >= bundleOffer.getOfferRequiredQuantity()) {
                    int numberOfBundles = totalItemBundlable / bundleOffer.getOfferRequiredQuantity();
                    checkoutPrice += numberOfBundles * bundleOffer.getBundlePrice();

                    // All items waiting for a full bundle are now included in a bundle
                    // Therefore, they can be removed from cart
                    for (Item previousItem: bundlableItemsQuantityMap.keySet()) {
                        itemsWithQuantity.remove(previousItem.getItem());
                    }
                    // Cleaning bundlableItemsQuantityMap
                    bundlableItemsQuantityMap.clear();


                    // Adding potential extra new items that are in excess to create the bundle
                    nbItemsWaitingFullBundle = totalItemBundlable % bundleOffer.getOfferRequiredQuantity();

                    bundlableItemsQuantityMap.put(item, nbItemsWaitingFullBundle);

                    // Remove number of items used from cart
                    itemsWithQuantity.put(item.getItem(), nbItemsWaitingFullBundle);
                } else { // not enough items to create a bundle, save it for later
                    nbItemsWaitingFullBundle += newItemQuantity;
                    bundlableItemsQuantityMap.put(item, newItemQuantity);
                }
            }
        }

        for (Character item : itemsWithQuantity.keySet()) {
            if (!itemsPrice.containsKey(item)) {
                return -1;
            }

            int itemQuantity = itemsWithQuantity.get(item);
            int itemUnitPrice = itemsPrice.get(item).getPrice();

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
