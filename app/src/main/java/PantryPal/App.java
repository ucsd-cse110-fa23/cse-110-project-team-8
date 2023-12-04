package PantryPal;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import Server.*;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.dropbox.core.DbxException;

public class App extends Application {

    public LoginUI root;
    // public RecipeListScreen root;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Setting the Layout of the Window- Should contain a Header, Footer and the
        // ContactList

        Server server = new Server();
        Model model = new Model();
        Controller controller = new Controller(model);
        root = new LoginUI(primaryStage, controller, server);
        // Set the title of the app
        primaryStage.setTitle("Pantry Pal");

        // Create scene of mentioned size with the border pane
        primaryStage.setScene(root.getLoginScene());
        // Make resizable
        primaryStage.setResizable(true);
        // Show the app
        primaryStage.show();

        try {
            server.activateServer();
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Server Start Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());

            // Show the pop-up window
            alert.showAndWait();
            System.exit(1);
        }

        if (new File("AutoLogin.txt").exists()) {
            if (AutoLogin.autoLoginEnabled("AutoLogin.txt")) {
                root.autoLoginTrue();
                root.setUsername(AutoLogin.autoLoginUsername("AutoLogin.txt"));
                root.setPassword(AutoLogin.autoLoginPassword("AutoLogin.txt"));
                root.getLoginButton().fire();
            }
        }
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                // Handle the close event
                try {
                    server.deactivateServer();
                    DropBox dropBox = new DropBox();
                    dropBox.deleteAll();
                    primaryStage.close();
                } catch (IOException | DbxException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}
