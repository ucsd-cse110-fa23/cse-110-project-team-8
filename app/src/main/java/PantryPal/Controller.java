package PantryPal;

import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class Controller {
    private ScreenManager screenManager;
    private Model model;

    public Controller(ScreenManager screenManager, Model model) throws Exception {
        this.screenManager = screenManager;
        this.model = model;
        if (screenManager.getRecipeDescriptionScreen() != null) {
            this.screenManager.getRecipeDescriptionScreen().setSaveButtonAction(event -> {
                try {
                    handlePOST(event);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

    }

    private void handlePOST(ActionEvent event) throws Exception {
        RecipeDescriptionScreen rd = this.screenManager.getRecipeDescriptionScreen();
        rd.handleSaveButton();
        String title = rd.getTitle();
        String ingredients = rd.getIngredients();
        String instructions = rd.getInstructions();

        String response = model.performRequest("POST", title, ingredients, instructions);
        screenManager.showAlert("Response", response);
    }
}