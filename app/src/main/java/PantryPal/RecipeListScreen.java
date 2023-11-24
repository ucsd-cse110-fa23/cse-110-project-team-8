package PantryPal;

import java.io.IOException;

import com.opencsv.exceptions.CsvValidationException;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import Server.*;


public class RecipeListScreen extends BorderPane {
    private RecipeListHeader header;
    public RecipeListFooter footer;
    private RecipeListBody recipeList;

    private Button addButton;
    private Button logout;
    private Stage primaryStage;
    private Scene mainScene; // Field to store the main scene
    private Scene logoutScene;
    private Generate generate;
    private UserInputScreen userInputScreen;
    private Controller controller;
    private Scene scene;
    private Server server;

    public RecipeListScreen(Stage primaryStage, Controller controller, Server server) throws Exception {
        this.controller = controller;
        this.primaryStage = primaryStage; // Store the stage
        this.mainScene = this.getScene(); // Store the main scene
        this.server = server;
        generate = new Generate();
        header = new RecipeListHeader();
        footer = new RecipeListFooter();
        RecipeList recipeListArray = new RecipeList();
        recipeList = new RecipeListBody(recipeListArray);
        ScrollPane scrollPane = new ScrollPane(recipeList);
        scrollPane.setFitToWidth(true);
        this.setTop(header);
        this.setCenter(scrollPane);
        this.setBottom(footer);
        addButton = footer.getAddButton();
        logout = footer.getLogoutButton();
        addListeners();
        this.scene = new Scene(this);
    }

    // rebuild the recipelist
    public void rebuild() throws Exception {
        recipeList.setStage(this.primaryStage);
        recipeList.setScene(this.mainScene);
        recipeList.getArray().loadDB(recipeList, this.controller);
    }

    // add listeners to the buttons
    public void addListeners() throws Exception {
        addButton.setOnAction(e -> {
            userInputScreen = new UserInputScreen(recipeList, primaryStage, this.mainScene, this.generate,
                    this.controller, this.server);
            userInputScreen.switchToThisScene();
        }); // Set the action on the button

        logout.setOnAction(e -> {
            primaryStage.setScene(logoutScene);
            this.getChildren().clear();
            try {
                server.deactivateServer();
                server.activateServer();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.out.println("logout pressed");
        }); // Set the action on the button
    }

    public Generate getGenerate() {
        return this.generate;
    }

    public void setMainScene(Scene scene) {
        this.mainScene = scene;
    }

    public RecipeListBody getRecipeList() {
        return this.recipeList;
    }

    public void switchToThisScene() {
        primaryStage.setScene(this.getScene());
    }
    public Scene getRecipeListScene(){
        return this.scene;
    }
    public void setLogoutScene(Scene scene){
        this.logoutScene = scene;
    }
}
