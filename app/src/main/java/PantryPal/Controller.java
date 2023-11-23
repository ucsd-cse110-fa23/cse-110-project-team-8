package PantryPal;

import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.event.ActionEvent;

public class Controller {
    private Model model;

    public Controller(Model model) throws Exception {
        this.model = model;
    }

    public void handleSave() throws Exception {
        try {
            // Create a CSV reader and specify the file to read
            CSVReader reader = new CSVReader(new FileReader("RecipeList.csv"));
            reader.readNext();
            // Read the CSV file and process each row
            String[] line;
            String[] lastline = {};
            while ((line = reader.readNext()) != null) {
                lastline = line;
            }
            Recipe recipe = new Recipe(lastline[0], lastline[1], lastline[2]);
            String title = lastline[0];
            String ingredients = lastline[1];
            String instructions = lastline[2];
            String response = model.performRequest("POST", title, ingredients, instructions);
            // Close the CSV reader
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String handleGetAll() {
        String response = model.performRequest("GET", null, null, null);
        return response;
    }
}