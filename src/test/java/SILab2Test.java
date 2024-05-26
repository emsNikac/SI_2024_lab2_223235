import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;

public class SILab2Test {

    @Test
    public void testEveryBranch() {
        try {
            SILab2.checkCart(null, 100);
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            assertEquals("allItems list can't be null!", e.getMessage());
        }

        List<Item> items = List.of();
        assertTrue(SILab2.checkCart(items, 100));

        items = List.of(new Item(null, "12345", 100, 0));
        assertTrue(SILab2.checkCart(items, 100));

        items = List.of(new Item("item1", "12a45", 100, 0));
        try {
            SILab2.checkCart(items, 100);
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            assertEquals("Invalid character in item barcode!", e.getMessage());
        }

        items = List.of(new Item("item1", null, 100, 0));
        try {
            SILab2.checkCart(items, 100);
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            assertEquals("No barcode!", e.getMessage());
        }

        items = List.of(new Item("item1", "12345", 100, 0.2f));
        assertTrue(SILab2.checkCart(items, 20));

        items = List.of(new Item("item1", "012345", 350, 0.1f));
        assertTrue(SILab2.checkCart(items, 32));

        items = Arrays.asList(
                new Item("item1", "12345", 100, 0),
                new Item("item2", "54321", 200, 0.1f),
                new Item("item3", "67890", 400, 0.2f)
        );
        assertTrue(SILab2.checkCart(items, 200));

        items = List.of(new Item("item1", "12345", 50, 0));
        assertTrue(SILab2.checkCart(items, 60));

        items = Arrays.asList(
                new Item("item1", "12345", 100, 0),
                new Item("item2", "67890", 200, 0)
        );
        assertFalse(SILab2.checkCart(items, 250));
    }

    @Test
    public void testMultipleCondition() {
        List<Item> items = List.of(new Item("item1", "012345", 350, 0.1f));
        assertTrue(SILab2.checkCart(items, 35));

        items = List.of(new Item("item1", "112345", 350, 0.1f));
        assertFalse(SILab2.checkCart(items, 35));

        items = List.of(new Item("item1", "012345", 350, 0.0f));
        assertFalse(SILab2.checkCart(items, 35));

        items = List.of(new Item("item1", "112345", 350, 0.0f));
        assertFalse(SILab2.checkCart(items, 35));

        items = List.of(new Item("item1", "012345", 250, 0.1f));
        assertFalse(SILab2.checkCart(items, 25));

        items = List.of(new Item("item1", "112345", 250, 0.1f));
        assertFalse(SILab2.checkCart(items, 25));

        items = List.of(new Item("item1", "012345", 250, 0.0f));
        assertFalse(SILab2.checkCart(items, 25));

        items = List.of(new Item("item1", "112345", 250, 0.0f));
        assertFalse(SILab2.checkCart(items, 25));
    }
}
