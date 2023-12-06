package VoiceInput;

public class MockChatGPT {

    // Mock implementation for testing purposes
    public String chefGPT(String Mealtype, String userIngredients) {
        String generatedRecipe;

        generatedRecipe = "testTitle" + " - " + Mealtype +
                userIngredients + "testInstrutions";

        return generatedRecipe;
    }
}