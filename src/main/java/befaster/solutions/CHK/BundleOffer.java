package befaster.solutions.CHK;

import java.util.List;

public class BundleOffer extends Offer {

    private Integer bundlePrice;
    private List<Item> bundlableItems;

    public BundleOffer(Integer offerRequiredQuantity, Integer bundlePrice, List<Item> bundlableItems) {
        super(offerRequiredQuantity);
        this.bundlePrice = bundlePrice;
        this.bundlableItems = bundlableItems;
    }

    public List<Item> getBundlableItems() {
        return bundlableItems;
    }

    public Integer getBundlePrice() {
        return bundlePrice;
    }
}


