import org.junit.Test;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

public class IngredientTypeTest {
    @Test
    public void values() {
        assertEquals("SAUCE", IngredientType.SAUCE.name());
    }

    @Test
    public void valueOf() {
        assertEquals("FILLING", IngredientType.FILLING.name());
    }
}
