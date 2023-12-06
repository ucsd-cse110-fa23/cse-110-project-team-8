package PantryPal;

import java.io.IOException;

import com.dropbox.core.DbxException;
import com.opencsv.exceptions.CsvValidationException;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import Server.*;

public class RecipeListScreen extends BorderPane {
    private RecipeListHeader header;
    public RecipeListFooter footer;
    private RecipeListBody recipeList;
    private RecipeList recipeListArray;
    private RecipeList recipeListArrayCopy;

    private Button addButton;
    private Button logout;
    private Button sortButton;
    private ComboBox sortOptions;

    private Button filterButton;
    private ComboBox filterOptions;

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
        recipeListArray = new RecipeList();
        recipeList = new RecipeListBody(recipeListArray);
        ScrollPane scrollPane = new ScrollPane(recipeList);
        scrollPane.setFitToWidth(true);
        this.setTop(header);
        this.setCenter(scrollPane);
        this.setBottom(footer);
        addButton = footer.getAddButton();
        logout = footer.getLogoutButton();
        sortButton = header.getSortButton();
        sortOptions = header.getSortOptions();
        filterButton = header.getFilterButton();
        filterOptions = header.getFilterOptions();
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
                    this.controller);
            userInputScreen.switchToThisScene();
        }); // Set the action on the button

        logout.setOnAction(e -> {
            DropBox dropBox = new DropBox();
            try {
                dropBox.deleteAll();
            } catch (DbxException e1) {
                e1.printStackTrace();
            }
            ;
            primaryStage.setScene(logoutScene);
            AutoLogin.clearFile();
            // this.getChildren().clear();
            // try {
            // server.deactivateServer();
            // server.activateServer();
            // } catch (IOException e1) {
            // // TODO Auto-generated catch block
            // e1.printStackTrace();
            // }
            System.out.println("logout pressed");
        }); // Set the action on the button

        sortButton.setOnAction(e -> {
            switch ((String) sortOptions.getValue()) {
                case "A-Z":
                    recipeListArray.sortAtoZ();
                    try {
                        recipeList.reloadAll(controller, "sort", primaryStage, mainScene);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    break;
                case "Z-A":
                    recipeListArray.sortZtoA();
                    try {
                        recipeList.reloadAll(controller, "sort",  primaryStage, mainScene);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    break;
                case "Old-New":
                    recipeListArray.sortOldtoNew();
                    try {
                        recipeList.reloadAll(controller, "sort",  primaryStage, mainScene);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    break;
                case "New-Old":
                    recipeListArray.sortNewtoOld();
                    try {
                        recipeList.reloadAll(controller, "sort",  primaryStage, mainScene);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    break;
            }
        });

        filterButton.setOnAction(e -> {
            System.out.println("filter button pressed");
            switch ((String) filterOptions.getValue()) {
                case "Breakfast":
                    try {
                        recipeList.reloadAll(controller, "breakfast",  primaryStage, mainScene);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    break;

                case "Lunch":
                    try {
                        recipeList.reloadAll(controller, "lunch",  primaryStage, mainScene);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    break;

                case "Dinner":
                    try {
                        recipeList.reloadAll(controller, "dinner",  primaryStage, mainScene);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    break;

                case "All":
                    try {
                        recipeList.reloadAll(controller, "all",  primaryStage, mainScene);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    break;
            }
        });
        
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

    public Scene getRecipeListScene() {
        return this.scene;
    }

    public void setLogoutScene(Scene scene) {
        this.logoutScene = scene;
    }
}
