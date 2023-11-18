package PantryPal;

import javafx.scene.control.Alert;

public class ScreenManager {
    private RecipeDescriptionScreen recipeDescriptionScreen;
    private RecipeListScreen recipeListScreen;

    public ScreenManager() {

    }

    public void setRecipeDescriptionScreen(RecipeDescriptionScreen recipeDescriptionScreen) {
        this.recipeDescriptionScreen = recipeDescriptionScreen;
    }

    public void setRecipeListScreen(RecipeListScreen recipeListScreen) {
        this.recipeListScreen = recipeListScreen;
    }

    public RecipeDescriptionScreen getRecipeDescriptionScreen() {
        return this.recipeDescriptionScreen;
    }

    public RecipeListScreen getRecipeListScreen() {
        return this.recipeListScreen;
    }

    public void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}