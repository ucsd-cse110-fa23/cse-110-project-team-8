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
    public Recipe recipe;

    public Footer() {
        this.setPrefSize(500, 60);
        this.setStyle("-fx-background-color: #F0F8FF;");
        this.setSpacing(15);

        String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 11 arial;";

        addButton = new Button("New Recipe");

        this.getChildren().add(addButton);
        this.setAlignment(Pos.CENTER);
        recipe = new Recipe();
    }

    public void switchScene1() {

        mainScene = this.getScene(); // Store the main scene
        mainStage = (Stage) this.getScene().getWindow(); // Store the main stage

        VBox newRoot = new VBox(); // Create a new root for the new scene

        Text text1 = new Text("To create a new recipe please state your meal type. \n");
        Text text2 = new Text("(Breakfast, Lunch, Dinner) \n");
        Text text3 = new Text(
                "'Start Recording' then say your meal type. Press 'Stop Recording' when you are done speaking. ");
        TextFlow recipeInstructions = new TextFlow(text1, text2, text3); // adding all lines of text into one big text
        recipeInstructions.setPrefSize(200, 200);
        recipeInstructions.setStyle("-fx-font-style: italic; ");
        recipeInstructions.setTextAlignment(TextAlignment.CENTER); // alligning text to the center
        newRoot.getChildren().add(recipeInstructions); // add the textbox to the new root

        Button startRecButton = new Button("Start Recording"); // Create a new button
        startRecButton.setOnAction(e -> {
            recipe.startRecording();
            System.out.println("Start Recording on Meal scene pressed");
        }); // Add Temporary action
        newRoot.getChildren().add(startRecButton); // Add the new button to the new root

        Button stopRecButton = new Button("Stop Recording"); // Create a new button
        stopRecButton.setOnAction(e -> {
            recipe.stopRecording();
            String out = recipe.getUserInput(); // Get the user input
            System.out.println("Stop Recording on meal scene pressed");
            System.out.println(out);
            switchScene2();
        }); // Switching to second scene
        newRoot.getChildren().add(stopRecButton); // Add the new button to the new root

        Button goBack = new Button("Go Back"); // Create a new button
        goBack.setOnAction(e -> {
            switchToMainScene();
            System.out.println("Go Back on meal scene pressed");
        }); // Switch back to main screen
        newRoot.getChildren().add(goBack); // Add the new button to the new root
        newRoot.setSpacing(10); // Set the spacing between the children of newRoot
        newRoot.setAlignment(Pos.CENTER); // Set the alignment of the children of newRoot

        Scene newScene = new Scene(newRoot, 800, 800); // Create a new scene

        mainStage.setScene(newScene); // Set the new scene on the current stage
    }

    public void switchScene2() {
        secondScene = mainScene; // Store the main scene
        secondStage = (Stage) mainStage.getScene().getWindow(); // Store the main stage

        VBox newRoot = new VBox(); // Create a new root for the new scene

        Text text1 = new Text("Now please state your ingredients \n");
        Text text2 = new Text(
                "'Start Recording' then say your meal type. Press 'Stop Recording' when you are done speaking. ");
        TextFlow recipeInstructions = new TextFlow(text1, text2); // adding all lines of text into one big text
        recipeInstructions.setPrefSize(200, 200);
        recipeInstructions.setStyle("-fx-font-style: italic; ");
        recipeInstructions.setTextAlignment(TextAlignment.CENTER); // alligning text to the center
        newRoot.getChildren().add(recipeInstructions); // add the textbox to the new root

        Button startRecButton = new Button("Start Recording"); // Create a new button
        startRecButton.setOnAction(e -> {
            recipe.startRecording();
            System.out.println("Start Recording on ingredient scene pressed");
        }); // Add Temporary action
                                                                  // to the button
        newRoot.getChildren().add(startRecButton); // Add the new button to the new root

        Button stopRecButton = new Button("Stop Recording"); // Create a new button
        stopRecButton.setOnAction(e -> {
            switchScene3();
            System.out.println("Stop Recording on ingredient scene pressed");
        }); // swith to scene3
        newRoot.getChildren().add(stopRecButton); // Add the new button to the new root

        Button goBack = new Button("Go Back"); // Create a new button
        goBack.setOnAction(e -> {
            switchToMainScene();
            System.out.println("Go Back on ingredient scene pressed");
        }); // Switch back to main screen
        newRoot.getChildren().add(goBack); // Add the new button to the new root
        newRoot.setSpacing(10); // Set the spacing between the children of newRoot
        newRoot.setAlignment(Pos.CENTER); // Set the alignment of the children of newRoot

        Scene newScene = new Scene(newRoot, 800, 800); // Create a new scene

        secondStage.setScene(newScene); // Set the new scene on the current stage
    }

    public void switchScene3() {
        thirdScene = secondScene; // Store the main scene
        thirdStage = (Stage) secondStage.getScene().getWindow(); // Store the main stage

        VBox newRoot = new VBox(); // Create a new root for the new scene

        Text text1 = new Text("This is where the created recipe will be displayed");
        Text text2 = new Text("(fill this through interactions)");
        TextFlow recipeInstructions = new TextFlow(text1, text2); // adding all lines of text into one big text
        recipeInstructions.setPrefSize(200, 200);
        recipeInstructions.setStyle("-fx-font-style: italic; ");
        recipeInstructions.setTextAlignment(TextAlignment.CENTER); // alligning text to center
        newRoot.getChildren().add(recipeInstructions); // add the textbox to the new root

        Button editButton = new Button("Edit"); // Create a new button
        editButton.setOnAction(e -> System.out.println("Edit Button Clicked")); // Add Temporary action to the button
        newRoot.getChildren().add(editButton); // Add the new button to the new root

        Button saveButton = new Button("Save"); // Create a new button
        saveButton.setOnAction(e -> {
            switchToMainScene();
            System.out.println("Save Button Clicked on third(recipe) scene");
        }); // Switch to main screen after saving
        newRoot.getChildren().add(saveButton); // Add the new button to the new root

        Button goBack = new Button("Go Back"); // Create a new button
        goBack.setOnAction(e -> {
            switchToMainScene();
            System.out.println("Go Back on third(recipe) scene pressed");
        }); // Switch back to main screen
        newRoot.getChildren().add(goBack); // Add the new button to the new root
        newRoot.setSpacing(10); // Set the spacing between the children of newRoot
        newRoot.setAlignment(Pos.CENTER); // Set the alignment of the children of newRoot

        Scene newScene = new Scene(newRoot, 800, 800); // Create a new scene

        thirdStage.setScene(newScene); // Set the new scene on the current stage
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
}