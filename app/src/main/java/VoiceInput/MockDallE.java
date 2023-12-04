package VoiceInput;

import java.io.FileWriter;
import java.io.IOException;

class MockDallE {

    // Mock implementation for testing purposes
    public String chefDallE(String userInstructions, String recipeTitle) {
        // Simulate the behavior of the real method during testing

        try (FileWriter writer = new FileWriter(recipeTitle + ".txt")) {
            writer.write(userInstructions);
            System.out.println("Saved to file: " + recipeTitle + ".txt");
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
            e.printStackTrace();
            // Handle the exception as needed
        }

        return "MockedGeneratedImageURL";
    }
}