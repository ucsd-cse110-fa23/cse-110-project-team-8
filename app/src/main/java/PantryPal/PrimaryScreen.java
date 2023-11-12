package PantryPal;

import com.opencsv.exceptions.CsvValidationException;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

public class PrimaryScreen extends BorderPane {
    private Header header;
    public Footer footer;
    private RecipeList recipeList;

    private Button addButton;
    private Stage primaryStage;
    private Scene mainScene; // Field to store the main scene
    private Generate generate;
    private UserInputScreen userInputScreen;

    public PrimaryScreen(Stage primaryStage) throws CsvValidationException {
        this.primaryStage = primaryStage;
        this.mainScene = this.getScene();
        generate = new Generate();
        header = new Header();
        footer = new Footer();
        recipeList = new RecipeList();
        ScrollPane scrollPane = new ScrollPane(recipeList);
        scrollPane.setFitToWidth(true);
        this.setTop(header);
        this.setCenter(scrollPane);
        this.setBottom(footer);
        addButton = footer.getAddButton();
        addListeners();
    }

    // rebuild the recipelist
    public void rebuild() throws CsvValidationException {
        recipeList.setStage(this.primaryStage);
        recipeList.setScene(this.mainScene);
        recipeList.loadCSV();
    }

    public void addListeners() {
        addButton.setOnAction(e -> {
            userInputScreen = new UserInputScreen(recipeList, primaryStage, this.mainScene, this.generate);
            userInputScreen.switchToThisScene();
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
