package VoiceInput;

public class MockWhisper {

    // Mock implementation for testing purposes
    public static String processIngredients(String input, boolean understands) {
        // Simulate the behavior of the real method during testing

        if (understands) {
            return (input + "understand");
        } else {
            return (input + "misunderstand");
        }
    }

    public static boolean processMealtype(String mealtype) {
        if (mealtype == "breakfast" || mealtype == "dinner" || mealtype == "lunch") {
            return true;
        } else {
            return false;
        }

    }
}