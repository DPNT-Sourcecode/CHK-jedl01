package befaster.solutions.CHK;

import org.junit.Test;

import static org.junit.Assert.*;

public class CheckoutSolutionTest {

    @Test
    public void test_check() {
        // given
        CheckoutSolution checkout = new CheckoutSolution();

        // when
        Integer result = checkout.checkout("AAaBBAADC");

        // then
        assertEquals(Integer.valueOf(50), result);
    }

}