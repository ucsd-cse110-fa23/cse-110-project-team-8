package PantryPal;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class SharelinkScreen {
    private Text urlContainer;
    private Text copyResponse;
    private String url;
    private static final String RESPONSE = "URL Copied to Clipboard!";
    private Button copy;

    public SharelinkScreen(String url, Stage stage) {
        this.url = url;
        this.urlContainer = new Text(url);

        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();

        // COPY button
        copy = new Button("Copy");
        copy.setOnAction(e -> {
            // copy url to clipboard
            content.putString(url);
            clipboard.setContent(content);
            System.out.println(RESPONSE);

            // show response window
            Stage copyResponsewindow = new Stage();
            copyResponsewindow.initModality(Modality.APPLICATION_MODAL);
            VBox response_layout = new VBox(10);
            copyResponse = new Text(RESPONSE);
            response_layout.getChildren().add(copyResponse);
            response_layout.setAlignment(Pos.CENTER);
            Scene copyScene = new Scene(response_layout, 300, 250);
            copyResponsewindow.setScene(copyScene);
            copyResponsewindow.showAndWait();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(urlContainer, copy);
        layout.setAlignment(Pos.CENTER);
        Scene copyScene = new Scene(layout, 300, 250);
        stage.setScene(copyScene);

    }

}
