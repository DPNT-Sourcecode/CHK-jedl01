package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {

    private Map<String, Integer> prices = new HashMap(){{
        put("A", 50);
        put("B", 30);
        put("C", 20);
        put("D", 15);
    }} ;

    private Map<String, Offer> offers = new HashMap<>(){{
        put("A", new Offer())
    }};

    public Integer checkout(String skus) {
        throw new SolutionNotImplementedException();
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

