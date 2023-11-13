package PantryPal;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class RecipeListHeader extends HBox {

    public RecipeListHeader() {

        this.setPrefSize(500, 70); // Size of the header
        this.setStyle("-fx-background-color: #BF2C34;");

        Text titleText = new Text("Pantry Pal"); // Text of the Header
        titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 30; -fx-fill: #ecf0f1;");
        this.getChildren().add(titleText);
        this.setAlignment(Pos.CENTER); // Align the text to the Center
    }
}
