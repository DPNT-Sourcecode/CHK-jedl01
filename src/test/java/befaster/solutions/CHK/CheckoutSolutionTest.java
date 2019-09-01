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
    public void checkout_shouldApplyBasicOffers() {
        // when
        Integer result = checkout.checkout("BBD");

        // then
        assertEquals(Integer.valueOf(60), result);
    }

    @Test
    public void checkout_with2EAnd2B_shouldApply1FreeBOffer() {
        // when
        Integer result = checkout.checkout("EEBB");

        // then
        assertEquals(Integer.valueOf(110), result);
    }

    @Test
    public void checkout_with2EAnd3B_shouldApply1FreeBAndOfferOnB() {
        // when
        Integer result = checkout.checkout("EEBBB");

        // then
        assertEquals(Integer.valueOf(125), result);
    }

    @Test
    public void checkout_with4EAnd3B_shouldApply2FreeB() {
        // when
        Integer result = checkout.checkout("EEEEBBB");

        // then
        assertEquals(Integer.valueOf(190), result);
    }

    @Test
    public void checkout_with2F_shouldNotApply1FreeF() {
        // when
        Integer result = checkout.checkout("FF");

        // then
        assertEquals(Integer.valueOf(20), result);
    }

    @Test
    public void checkout_with3F_shouldApply1FreeF() {
        // when
        Integer result = checkout.checkout("FFF");

        // then
        assertEquals(Integer.valueOf(20), result);
    }

    @Test
    public void checkout_with4F_shouldApply1FreeF() {
        // when
        Integer result = checkout.checkout("FFFF");

        // then
        assertEquals(Integer.valueOf(30), result);
    }

    @Test
    public void checkout_with6F_shouldApply2FreeF() {
        // when
        Integer result = checkout.checkout("FFFFFF");

        // then
        assertEquals(Integer.valueOf(40), result);
    }

    @Test
    public void checkout_with8F_shouldApply2FreeF() {
        // when
        Integer result = checkout.checkout("FFFFFFFF");

        // then
        assertEquals(Integer.valueOf(60), result);
    }
}