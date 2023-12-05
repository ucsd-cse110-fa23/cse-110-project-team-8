package PantryPal;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.geometry.Insets;
import javafx.scene.text.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class DishImage {

    // To display images
    static public void uploadImage(HBox hbox, Stage secondStage, String filePath) {
    //static public void uploadImage(HBox hbox, Stage secondStage) {
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

        /*Center align the contents of the vbox.*/
        vbox.setAlignment(Pos.CENTER);

        // Assuming that this class extends some kind of Pane or Node
        //hbox.getChildren().set(0, vbox);
        hbox.getChildren().add(0,vbox);
    }
}
