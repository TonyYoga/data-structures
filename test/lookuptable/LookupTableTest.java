package lookuptable;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class LookupTableTest {
    LookupTable<Integer> lt = new LookupTable<>();
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void put() {
        lt.put("He", 0);
        lt.put("Hell", 1);
        lt.put("Hello", 2);
        assertEquals(0, (int) lt.get("He"));
        assertEquals(1, (int) lt.get("Hell"));
        assertEquals(2, (int) lt.get("Hello"));
        assertFalse(lt.put("He", 3));
    }

    @Test(expected = NoSuchElementException.class)
    public void get() {
        lt.get("test");
        lt.put("Hello", 1);
        assertEquals(1, (int) lt.get("Hello"));
        lt.get("Hell");
        lt.get("Hellow");

    }
}