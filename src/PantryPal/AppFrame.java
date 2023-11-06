package PantryPal;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import PantryPal.Footer;

public class AppFrame extends BorderPane {
    private Header header;
    public Footer footer;
    private RecipeList recipeList;
    private ScrollPane scroller;
    private Button addButton;
    private Button goBack;
    private Button saveButton;
    private Button title;
    private Stage mainStage;
    private Recipe recipe;
    private Scene mainScene; // Field to store the main scene

    public AppFrame(Stage mainStage) {
        this.mainStage = mainStage;
        this.recipe = new Recipe();
        this.mainScene = this.getScene();
        header = new Header();
        footer = new Footer();
        recipeList = new RecipeList();
        ScrollPane scrollPane = new ScrollPane(recipeList);
        // scroller = new ScrollPane();
        // scroller.setContent(RecipeList);
        // scroller.setFitToWidth(true);

        this.setTop(header);
        this.setCenter(scrollPane);
        this.setBottom(footer);
        addButton = footer.getAddButton();
        addListeners();
    }

    public void addListeners() {
        addButton.setOnAction(e -> {
            MealSelectScreen mealSelectScreen = new MealSelectScreen(recipe, mainStage, this.mainScene);
            mealSelectScreen.switchToThisScene();
            //this.setCenter(mealSelectScreen.getScene().getRoot());
        }); // Set the action on the button
        saveButton = footer.getSaveButton();
        saveButton.setOnAction(e -> {
            //recipe = new Recipe();
            footer.getRecipe().getRecipeTitle().setText(footer.getGeneratedRecipe());
            recipeList.getChildren().add(recipe);//footer.getRecipe());
            footer.switchToMainScene();
            System.out.println("Save Button Clicked on third(recipe) scene");
        });
        title = footer.getRecipe().getRecipeTitle();
        title.setOnAction(e -> {
            
        });
    }

    public void switchToThisScene() {
        mainStage.setScene(this.getScene());
    }

}
