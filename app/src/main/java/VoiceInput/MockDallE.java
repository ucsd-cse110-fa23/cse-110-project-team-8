package VoiceInput;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class MockDallE {

    // Mock implementation for testing purposes
    public File chefDallE(String userInstructions, String recipeTitle) {
        // Simulate the behavior of the real method during testing
        File TestFile;

        TestFile = new File(recipeTitle + ".txt");

        try {
            // Create a BufferedWriter with a FileWriter
            BufferedWriter writer = new BufferedWriter(new FileWriter(TestFile));

            // Write the string to the file
            writer.write(userInstructions);

            // Close the writer to release resources
            writer.close();

            System.out.println("String has been written to the file.");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return TestFile;
    }
}