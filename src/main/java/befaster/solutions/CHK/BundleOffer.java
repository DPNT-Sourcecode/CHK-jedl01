package befaster.solutions.CHK;

import java.util.Set;

public class BundleOffer extends Offer {

    private Set<Character> bundlableItems;

    public BundleOffer(Integer offerRequiredQuantity, Set<Character> bundlableItems) {
        super(offerRequiredQuantity);
        this.bundlableItems = bundlableItems;
    }

    public Set<Character> getBundlableItems() {
        return bundlableItems;
    }
}
