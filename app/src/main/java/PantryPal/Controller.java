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
            while (reader.peek() != null) {
                String[] row = reader.readNext();
                Recipe recipe = new Recipe(row[0], row[1], row[2]);
                String title = row[0];
                String ingredients = row[1];
                String instructions = row[2];
                String response = model.performRequest("POST", title, ingredients, instructions);
            }
            // Close the CSV reader
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String handleGetAll() {
        String response = model.performRequest("GET", " ", " ", " ");
        System.out.println(response);
        return response;
    }
}