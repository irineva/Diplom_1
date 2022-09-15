import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTest {
    private final IngredientType expectedType;
    private final String expectedName;
    private final float expectedPrice;

    public IngredientTest(IngredientType expectedType, String expectedName, float expectedPrice) {
        this.expectedType = expectedType;
        this.expectedName = expectedName;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Object[][] getBunData() {
        return new Object[][]{
                {IngredientType.FILLING, "Начинка", 15.5f},
                {IngredientType.SAUCE, "Соус", 10.3f},
        };
    }

    @Test
    public void getTypeTest() {
        Ingredient ingredient = new Ingredient(expectedType, expectedName, expectedPrice);
        IngredientType actualType = ingredient.getType();
        assertEquals(expectedType, actualType);
    }

    @Test
    public void getNameTest() {
        Ingredient ingredient = new Ingredient(expectedType, expectedName, expectedPrice);
        String actualName = ingredient.getName();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void getPriceTest() {
        Ingredient ingredient = new Ingredient(expectedType, expectedName, expectedPrice);
        float actualPrice = ingredient.getPrice();
        assertEquals(expectedPrice, actualPrice, 0);
    }
}
