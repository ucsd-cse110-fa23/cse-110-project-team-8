package PantryPal;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.geometry.Insets;
import javafx.scene.text.*;
import java.util.random.*;
import java.io.*;
import java.util.*;
import PantryPal.App;

public class Footer extends HBox {

    private Button addButton;
    private Button startRecButton;
    private Button stopRecButton;
    private Button goBack;
    private Scene mainScene; // Field to store the main scene
    private Stage mainStage;
    private Scene secondScene; // Field to store second scene(stating meal type)
    private Stage secondStage;
    private Scene thirdScene; // Field to store third scene(stating ingredients)
    private Stage thirdStage;
    private Button saveButton;
    public Recipe recipe;
    private String recipeGenerated;

    // ------------------
    public String mealType;
    public String ingredients;
    // ----------------

    public Footer() {
        this.setPrefSize(500, 60);
        this.setStyle("-fx-background-color: #F0F8FF;");
        this.setSpacing(15);

        String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 11 arial;";

        addButton = new Button("New Recipe");
        saveButton = new Button("Save"); // Create a new button

        this.getChildren().add(addButton);
        this.setAlignment(Pos.CENTER);
        recipe = new Recipe();
        recipe.FILE_PATH = "userAudio.wav";
    }

    public void switchToMainScene() {
        mainStage.setScene(mainScene); // Set the main scene on the current stage
    }

    public Button getAddButton() {
        return this.addButton;
    }

    public Button getStartRecButton() {
        return this.startRecButton;
    }

    public Button getStopRecButton() {
        return this.stopRecButton;
    }

    public Button getGoBack() {
        return this.goBack;
    }
    public Button getSaveButton(){
        return this.saveButton;
    }
    public Recipe getRecipe(){
        return this.recipe;
    }
    public String getGeneratedRecipe(){
        return recipeGenerated;
    }

    // -----------------------------------------
    // public String getMealTypeString() {
    // return this.mealType;
    // }

    // public String getIngredientsString() {
    // return this.ingredients;
    // }
}