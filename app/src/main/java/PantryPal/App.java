package PantryPal;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

// probably will need to remove these
// import javafx.stage.FileChooser;
// import javafx.stage.WindowEvent;
// import javafx.stage.FileChooser.ExtensionFilter;
// import java.lang.ModuleLayer.Controller;
// import PantryPal.RecipeListFooter;

public class App extends Application {

    public RecipeListScreen root;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Setting the Layout of the Window- Should contain a Header, Footer and the ContactList
        root = new RecipeListScreen(primaryStage);

        // Set the title of the app
        primaryStage.setTitle("Pantry Pal");

        // Create scene of mentioned size with the border pane
        primaryStage.setScene(new Scene(root, 800, 800));
        root.setMainScene(primaryStage.getScene());
        root.rebuild();
        

        // Make window non-resizable
        primaryStage.setResizable(false);

        // Show the app
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
