package PantryPal;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

public class Footer extends HBox {

    private Button addButton;

    public Footer() {
        this.setPrefSize(500, 70); // Increased height for a larger button
        this.setStyle("-fx-background-color: #BF2C34;");
        this.setSpacing(15);

        //String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 11 arial;";
        String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF; " + 
                                    "-fx-font-weight: bold; -fx-font: 15 Arial; -fx-text-fill: #000000;" +
                                    " -fx-pref-width: 170; -fx-pref-height: 40;";
        addButton = new Button("New Recipe");
        this.getChildren().add(addButton);
        addButton.setStyle(defaultButtonStyle);
        this.setAlignment(Pos.CENTER);
    }

    public Button getAddButton() {
        return this.addButton;
    }
}