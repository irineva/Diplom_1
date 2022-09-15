import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerTest {
    private final String expectedBunName;
    private final IngredientType expectedIngredientType;
    private final String expectedIngredientName;
    private final float expectedPrice;

    public BurgerTest(String expectedBunName, IngredientType expectedIngredientType, String expectedIngredientName, float expectedPrice) {
        this.expectedBunName = expectedBunName;
        this.expectedIngredientType = expectedIngredientType;
        this.expectedIngredientName = expectedIngredientName;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Object[][] getBurgerData() {
        return new Object[][] {
                {"Big mac bun", IngredientType.SAUCE, "special souce", 30.0f},
                {"Bigtasty bun", IngredientType.FILLING, "special filling", 50.0f},
        };
    }

    @Mock
    Bun bun;

    @Mock
    Ingredient firstIngredient;

    @Mock
    Ingredient secondIngredient;

//    @Test
//    public void setBunsTest() {
//        burger.setBuns(bun);
//        Mockito.verify(burger).setBuns(bun);
//    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void setBuns() {
        Burger burger = new Burger();
        Mockito.when(bun.getName()).thenReturn(expectedBunName);
        burger.setBuns(bun);
        assertEquals(expectedBunName, burger.bun.getName());
    }
    @Test
    public void addIngredientTest() {
        Burger burgerWithIngredient = new Burger();
        Mockito.when(firstIngredient.getType()).thenReturn(expectedIngredientType);
        Mockito.when(firstIngredient.getName()).thenReturn(expectedIngredientName);
        Mockito.when(firstIngredient.getPrice()).thenReturn(expectedPrice);
        burgerWithIngredient.addIngredient(firstIngredient);
        assertEquals(expectedIngredientType, burgerWithIngredient.ingredients.get(0).getType());
    }
    @Test
    public void removeIngredientTest() {
        Burger burger = new Burger();
        Mockito.when(firstIngredient.getName()).thenReturn(expectedIngredientName);
        Mockito.when(secondIngredient.getName()).thenReturn("Name of second ingredient");

        burger.addIngredient(secondIngredient);
        burger.addIngredient(firstIngredient);
        burger.removeIngredient(0);

        assertEquals(expectedIngredientName, burger.ingredients.get(0).getName());

    }
    @Test
    public void moveIngredientTest() {
        Burger burger = new Burger();
        Mockito.when(firstIngredient.getName()).thenReturn(expectedIngredientName);
        Mockito.when(secondIngredient.getName()).thenReturn("Name of second ingredient");

        burger.addIngredient(secondIngredient);
        burger.addIngredient(firstIngredient);
        burger.moveIngredient(0,1);

        assertEquals(expectedIngredientName, burger.ingredients.get(0).getName());
    }
    @Test
    public void getPriceTest() {
        Burger burger = new Burger();
        Mockito.when(firstIngredient.getPrice()).thenReturn(expectedPrice);
        Mockito.when(bun.getPrice()).thenReturn(10.0f);

        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);

        float actualPrice = burger.getPrice();
        float expectedPrice = bun.getPrice()*2 + firstIngredient.getPrice();
        assertEquals(expectedPrice, actualPrice, 0);
    }

    @Test
    public void getReceiptTest() {
        Burger burger = new Burger();

        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);

        Mockito.when(bun.getName()).thenReturn(expectedBunName);
        Mockito.when(firstIngredient.getName()).thenReturn(expectedIngredientName);
        Mockito.when(firstIngredient.getType()).thenReturn(expectedIngredientType);
        Mockito.when(firstIngredient.getPrice()).thenReturn(expectedPrice);

        String expectedReceipt = "(==== " + expectedBunName + " ====)\n" +
                "= " + expectedIngredientType.toString().toLowerCase() + " " + expectedIngredientName + " =\n" +
                "(==== " + expectedBunName + " ====)\n" +
                "\n" +
                String.format("Price: %.6f", firstIngredient.getPrice()) +
                "\n";
        String actualReceipt = burger.getReceipt();
        assertEquals(expectedReceipt, actualReceipt);

    }
}
