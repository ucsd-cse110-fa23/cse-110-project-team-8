package PantryPal;

import com.opencsv.exceptions.CsvValidationException;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

public class AppFrame extends BorderPane {
    private Header header;
    public Footer footer;
    private RecipeList recipeList;

    private Button addButton;
    private Stage primaryStage;
    private Scene mainScene; // Field to store the main scene
    private Generate generate;
    private MealSelectScreen mealSelectScreen;

    public AppFrame(Stage primaryStage) throws CsvValidationException {
        this.primaryStage = primaryStage;
        this.mainScene = this.getScene();
        generate = new Generate();
        header = new Header();
        footer = new Footer();
        recipeList = new RecipeList();
        recipeList.setStage(primaryStage);
        recipeList.setScene(mainScene);
        recipeList.loadCSV();
        ScrollPane scrollPane = new ScrollPane(recipeList);
        this.setTop(header);
        this.setCenter(scrollPane);
        this.setBottom(footer);
        addButton = footer.getAddButton();
        addListeners();
    }

    public void addListeners() {
        addButton.setOnAction(e -> {
            mealSelectScreen = new MealSelectScreen(recipeList, primaryStage, this.mainScene, this.generate);
            mealSelectScreen.switchToThisScene();
        }); // Set the action on the button
    }

    public Generate getGenerate() {
        return this.generate;
    }

    public void setMainScene(Scene scene) {
        this.mainScene = scene;
    }

    public RecipeList getRecipeList() {
        return this.recipeList;
    }

}
