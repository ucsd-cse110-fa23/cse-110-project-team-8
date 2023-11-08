package PantryPal;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import PantryPal.Footer;

public class App extends Application {

    public AppFrame root;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Setting the Layout of the Window- Should contain a Header, Footer and the
        // ContactList
        root = new AppFrame(primaryStage);

        // Set the title of the app
        primaryStage.setTitle("Pantry Pal");
        // Create scene of mentioned size with the border pane
        primaryStage.setScene(new Scene(root, 800, 800));
        root.setMainScene(primaryStage.getScene());
        // Make window non-resizable
        primaryStage.setResizable(false);
        // Show the app
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}