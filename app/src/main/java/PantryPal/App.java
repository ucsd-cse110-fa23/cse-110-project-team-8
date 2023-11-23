package PantryPal;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import Server.*;
import java.io.*;

public class App extends Application {

    public LoginUI root;
    // public RecipeListScreen root;

    @Override
    public void start(Stage primaryStage) throws Exception{

        // Setting the Layout of the Window- Should contain a Header, Footer and the
        // ContactList
        Server server = new Server();
        server.activateServer();

        Model model = new Model();
        Controller controller = new Controller(model);
        
        root = new LoginUI(primaryStage, controller, server);

        // Set the title of the app
        primaryStage.setTitle("Pantry Pal");

        // Create scene of mentioned size with the border pane
        primaryStage.setScene(root.getLoginScene());
        // Make  resizable
        primaryStage.setResizable(true);

        // Show the app
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event){
                // Handle the close event
                try{
                    server.deactivateServer();
                    primaryStage.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
                

            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
