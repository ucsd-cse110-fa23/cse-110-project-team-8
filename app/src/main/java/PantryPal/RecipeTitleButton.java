package PantryPal;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class RecipeTitleButton extends HBox {
    private Button titleButton;
    private Scene description;
    private String titleString;
    private String ingredient;
    private String instructions;
    private Recipe recipe;

    public RecipeTitleButton(Recipe recipe) {
        this.recipe = recipe;
        titleButton = new Button(recipe.getTitle()); // creates a button for marking the Contact as done
        titleString = recipe.getTitle();
        ingredient = recipe.getIngredients();
        instructions = recipe.getInstructions();
        titleButton.setPrefSize(10000, 50); // sets size of button
        titleButton.setStyle(" -fx-font-size: 18; -fx-background-color: #ffffff; -fx-border-width: 0;"); // sets style of button
        this.getChildren().add(titleButton);
    }

    // getters and setters
    public Recipe getRecipe() {
        return this.recipe;
    }

    public Button getRecipeTitleButton() {
        return this.titleButton;
    }

    public Scene getDescription() {
        return this.description;
    }

    public void setDescription(Scene scene) {
        this.description = scene;
    }
}
