package befaster.solutions.CHK;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CheckoutSolutionTest {

    private CheckoutSolution checkout;

    @Before
    public void setUp() {
        checkout = new CheckoutSolution();
    }

    @Test
    public void test_check() {
        // when
        Integer result = checkout.checkout("EEB");

        // then
        assertEquals(Integer.valueOf(80), result);
    }

    @Test
    public void checkout_withEItemAndSeveralBs_shouldApplyOneFreeB() {
        // when
        Integer result = checkout.checkout("EEBB");

        // then
        assertEquals(Integer.valueOf(110), result);
    }

    @Test
    public void checkout_withEItemAndEnoughRemainingBsForOffer_shouldApplyOneFreeBAndOfferOnB() {
        // when
        Integer result = checkout.checkout("EEBBB");

        // then
        assertEquals(Integer.valueOf(125), result);
    }

    @Test
    public void checkout_withTwoEsOfferItemAndEnoughRemainingBsForOffer_shouldApplyOneFreeBAndOfferOnB() {
        // when
        Integer result = checkout.checkout("EEEEBBB");

        // then
        assertEquals(Integer.valueOf(190), result);
    }
}