package PantryPalTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import VoiceInput.*;

public class WhisperTest {
    MockWhisper mock;

    @BeforeEach
    void setUp() throws Exception {
        mock = new MockWhisper();
    }

    // BDD1
    @Test
    void understands() {
        String input = "Hello, World!";
        String result = MockWhisper.processIngredients(input, true);
        assertEquals(result.equals(input + "understand"), true);
    }

    // BDD2
    @Test
    void notUnderstands() {
        String input = "sdgfadfasdfasdff!";
        String result = MockWhisper.processIngredients(input, false);
        assertEquals(result.equals(input + "misunderstand"), true);
    }

    // BDD3
    @Test
    void breakfastMealTypeTest() {
        String mealType = "breakfast";
        boolean result = MockWhisper.processMealtype(mealType);
        assertEquals(result, true);
    }

    @Test
    void lunchMealTypeTest() {
        String mealType = "lunch";
        boolean result = MockWhisper.processMealtype(mealType);
        assertEquals(result, true);
    }

    @Test
    void dinnerMealTypeTest() {
        String mealType = "dinner";
        boolean result = MockWhisper.processMealtype(mealType);
        assertEquals(result, true);
    }

    @Test
    void dessertMealTypeTest() {
        String mealType = "dessert";
        boolean result = MockWhisper.processMealtype(mealType);
        assertEquals(result, false);
    }
}