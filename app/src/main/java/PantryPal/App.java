package PantryPal;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Server.*;

public class App extends Application {

    public RecipeListScreen root;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Server server = new Server();
        server.activateServer();
        ScreenManager screenManager = new ScreenManager();
        Model model = new Model();

        // Setting the Layout of the Window- Should contain a Header, Footer and the
        // ContactList
        root = new RecipeListScreen(primaryStage, screenManager);
        // Set the title of the app
        primaryStage.setTitle("Pantry Pal");

        // Create scene of mentioned size with the border pane
        primaryStage.setScene(new Scene(root, 800, 800));
        root.setMainScene(primaryStage.getScene());
        root.rebuild();

        // set the controller for model and screenManager
        screenManager.setRecipeListScreen(root);
        Controller controller = new Controller(screenManager, model);
        // Make window non-resizable
        primaryStage.setResizable(false);

        // Show the app
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
