package befaster.solutions.CHK;

import java.util.Set;

public class BundleOffer extends Offer {

    private Set<Character> bundlableItems;
    private Integer bundlePrice;

    public BundleOffer(Integer offerRequiredQuantity, Set<Character> bundlableItems, Integer bundlePrice) {
        super(offerRequiredQuantity);
        this.bundlableItems = bundlableItems;
        this.bundlePrice = bundlePrice;
    }

    public Set<Character> getBundlableItems() {
        return bundlableItems;
    }

    public Integer getBundlePrice() {
        return bundlePrice;
    }
}

