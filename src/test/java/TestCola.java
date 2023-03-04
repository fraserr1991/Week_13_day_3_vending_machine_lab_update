import org.junit.Before;
import org.junit.Test;
import product.Cola;

import static org.junit.Assert.assertEquals;

public class TestCola {
    Cola coke;

    @Before
    public void before(){
        coke = new Cola("coke", "cola", 1.00);
    }

    @Test
    public void hasName(){
        assertEquals("cola", coke.getName());
    }
    @Test
    public void hasPrice(){
        assertEquals(1.00, coke.getPrice(), 0.0);
    }
}
