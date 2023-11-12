package PantryPal;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

public class Recipe extends HBox {
    private Button titleButton;
    private TextField title;
    private TextField body;
    private Scene description;

    private String titleString;
    private String ingredient;
    private String instructions;
    private String time;

    public Recipe() {
        titleButton = new Button(); // creates a button for marking the Contact as done
        titleButton.setPrefSize(800, 50); // sets size of button
        //titleButton.setStyle("-fx-background-color: #FFFFFF; -fx-border-width: 0;"); // sets style of button
        titleButton.setStyle("-fx-font-style: italic; -fx-background-color: #FFFFFF; " + 
        "-fx-font-weight: bold; -fx-font: 15 Arial; -fx-text-fill: #000000;");
        this.getChildren().add(titleButton);


        title = new TextField();
        body = new TextField();
    }

    public void setButtonTitle() {
        this.titleButton.setText(titleString);
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

    public void setTitle(String title) {
        this.titleString = title;
    }

    public void setIngredients(String ingredient) {
        this.ingredient = ingredient;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String setTime(String time) {
        return this.time = time;
    }

    public String getTitle() {
        return this.titleString;
    }

    public String getIngredients() {
        return this.ingredient;
    }

    public String getInstructions() {
        return this.instructions;
    }

    public String getTime() {
        return this.time;
    }
}
