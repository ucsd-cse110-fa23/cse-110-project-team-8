package PantryPal;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

public class Footer extends HBox {

    private Button addButton;

    public Footer() {
        this.setPrefSize(500, 60);
        this.setStyle("-fx-background-color: #F0F8FF;");
        this.setSpacing(15);

        String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 11 arial;";

        addButton = new Button("New Recipe");
        this.getChildren().add(addButton);
        addButton.setStyle(defaultButtonStyle);
        this.setAlignment(Pos.CENTER);
    }

    public Button getAddButton() {
        return this.addButton;
    }
}