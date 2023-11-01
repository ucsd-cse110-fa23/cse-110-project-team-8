package PantryPal;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class AppFrame extends BorderPane {
    private Header header;
    public Footer footer;
    private RecipeList RecipeList;
    private ScrollPane scroller;

    private Button addButton;

    public AppFrame() {
        header = new Header();
        footer = new Footer();
        RecipeList = new RecipeList();
        // scroller = new ScrollPane();
        // scroller.setContent(RecipeList);
        // scroller.setFitToWidth(true);

        this.setTop(header);
        this.setCenter(scroller);
        this.setBottom(footer);

        addButton = footer.getAddButton();
        addListeners();
    }

    public void addListeners() {
        addButton.setOnAction(e -> {
            Recipe recipe = new Recipe();
            RecipeList.getChildren().add(recipe);
        });
    }
}
