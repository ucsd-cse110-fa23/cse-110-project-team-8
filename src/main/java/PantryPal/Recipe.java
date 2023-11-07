package main.java.PantryPal;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

public class Recipe extends HBox {
    private Button titleButton;
    private TextField title;
    private TextField body;
    private Scene description;

    public Recipe() {
        titleButton = new Button(); // creates a button for marking the Contact as done
        titleButton.setPrefSize(800, 50); // sets size of button
        titleButton.setStyle("-fx-background-color: #ffffff; -fx-border-width: 0;"); // sets style of button
        this.getChildren().add(titleButton);

        title = new TextField();
        body = new TextField();
    }

    public Button getRecipeTitleButton() {
        return this.titleButton;
    }

    public TextField getRecipeTitle() {
        return this.title;
    }

    public TextField getRecipeBody() {
        return this.body;
    }

    public Scene getDescription() {
        return this.description;
    }

    public void setDescription(Scene scene) {
        this.description = scene;
    }

}
