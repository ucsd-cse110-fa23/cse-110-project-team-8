package PantryPal;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class RecipeListHeader extends HBox {

    private ComboBox<String> sortOptions;
    private Button sortButton;
    private ComboBox<String> filterOptions;
    private Button filterButton;
    private static final String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF; " +
    "-fx-font-weight: bold; -fx-font: 15 Arial; -fx-text-fill: #000000;" +
    " -fx-pref-width: 170; -fx-pref-height: 40;";
    public RecipeListHeader() {

        this.setPrefSize(500, 70); // Size of the header
        this.setStyle("-fx-background-color: #BF2C34;");

        Text titleText = new Text("Pantry Pal             "); // Text of the Header
        titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 30; -fx-fill: #ecf0f1;");
        this.getChildren().add(titleText);
        this.setAlignment(Pos.CENTER); // Align the text to the Center

        sortOptions = new ComboBox<String>();
        sortOptions.getItems().add("A-Z");
        sortOptions.getItems().add("Z-A");
        sortOptions.getItems().add("Old-New");
        sortOptions.getItems().add("New-Old");
        
        this.getChildren().add(sortOptions);

        sortButton = new Button("Sort");
        this.getChildren().add(sortButton);

        //Filter
        filterOptions = new ComboBox<String>();
        filterOptions.getItems().add("Breakfast");
        filterOptions.getItems().add("Lunch");
        filterOptions.getItems().add("Dinner");
        filterOptions.getItems().add("All");
        
        this.getChildren().add(filterOptions);

        filterButton = new Button("Filter");
        this.getChildren().add(filterButton);

    }
    public Button getSortButton() {
        return sortButton;
    }

    public ComboBox<String> getSortOptions() {
        return sortOptions;
    }

    public Button getFilterButton() {
        return filterButton;
    }

    public ComboBox<String> getFilterOptions() {
        return filterOptions;
    }


}
