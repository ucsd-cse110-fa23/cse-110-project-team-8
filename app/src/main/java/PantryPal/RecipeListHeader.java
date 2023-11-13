package PantryPal;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.geometry.Insets;
import javafx.scene.text.*;
import java.io.*;
import java.util.*;

public class RecipeListHeader extends HBox {

    public RecipeListHeader() {
        // this.setPrefSize(500, 60); // Size of the header
        // this.setStyle("-fx-background-color: #F0F8FF;");

        // Text titleText = new Text("Pantry Pal"); // Text of the Header
        // titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");
        // this.getChildren().add(titleText);
        // this.setAlignment(Pos.CENTER); // Align the text to the Center

        this.setPrefSize(500, 70); // Size of the header
        this.setStyle("-fx-background-color: #BF2C34; -fx-border-color: #2980b9; -fx-border-width: 0 0 2 0;");

        Text titleText = new Text("Pantry Pal"); // Text of the Header
        titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 30; -fx-fill: #ecf0f1;");
        this.getChildren().add(titleText);
        this.setAlignment(Pos.CENTER); // Align the text to the Center
    }
}
