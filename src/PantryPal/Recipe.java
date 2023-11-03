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
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import javax.print.DocFlavor.URL;

import VoiceInput.*;

public class Recipe extends HBox {
    AudioRecorder recorder;
    Whisper whisper;
    ChatGPT chatGPT;

    public Recipe() {

        this.setPrefSize(500, 50); // sets size of task
        this.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold;");

    }

    public void CreateRecipe() {

        Whisper whisper = new Whisper();
        // Create file object from file path
        try {
            whisper.ActivateWhisper();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void createUI(){   //create the UI of recipe (record button, stop button and prompt)
  
    }

    public String processUserInput(){   //whisper recognizes user input and then chapGPT generate response (reformat the main method of Whisper and ChatGPT class)
        return "";
    }



}
