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
    private static final String CREATE_ACCOUNT = "createAccount";
    private static final String LOGIN = "Login";

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
                String response = model.performRequest("POST", null, null, title, ingredients, instructions,
                        null);
            }
            // Close the CSV reader
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String createAccount(String username, String password) {
        String response = model.performRequest("POST", username, password, " ", " ", " ", this.CREATE_ACCOUNT);
        return response;
    }

    public String login(String username, String password) {
        String response = model.performRequest("POST", username, password, " ", " ", " ", this.LOGIN);
        return response;
    }

    public String handleGetAll() {
        String response = model.performRequest("GET", null, null, null, null, null, null);
        return response;
    }

    public String handlePut(String title, String ingredients, String instructions) {
        String response = model.performRequest("PUT", null, null, title, ingredients, instructions, null);
        return response;
    }

    public String handleDelete(String title) {
        String response = model.performRequest("DELETE", null, null, title, null, null, null);
        return response;
    }
}