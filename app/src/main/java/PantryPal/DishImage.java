package PantryPal;

import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import java.io.*;

public class DishImage {

    // To display images
    static public void uploadImage(HBox hbox, Stage secondStage, String filePath) {
        // static public void uploadImage(HBox hbox, Stage secondStage) {
        File selectedFile = new File(filePath);
        ImageView imageView = new ImageView();

        if (selectedFile.exists()) {
            Image image = new Image(selectedFile.toURI().toString());

            imageView.setImage(image);
            imageView.setFitWidth(260); // Set the fixed width
            imageView.setFitHeight(260); // Set the fixed height
        } else {
            System.out.println("File not found: " + filePath);
            return;
        }

        VBox vbox = new VBox(imageView);

        vbox.setAlignment(Pos.CENTER);

        hbox.getChildren().add(0, vbox);
    }
}
