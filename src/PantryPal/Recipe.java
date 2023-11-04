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
import javax.sound.sampled.AudioFormat;

import VoiceInput.*;

public class Recipe extends HBox {
    AudioRecorder recorder;
    Whisper whisper;
    ChatGPT chatGPT;
    Button startButton;
    Button stopButton;

    public Recipe() {
        recorder = new AudioRecorder();
        whisper = new Whisper();
        chatGPT = new ChatGPT();
        startButton = new Button("Start");
        stopButton = new Button("Stop");
        recorder.audioFormat = recorder.getAudioFormat();

        this.setPrefSize(500, 50); // sets size of task
        this.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold;");
    }

    public void CreateRecipe() {

    }

    public void addListeners() {
        // Start Button
        startButton.setOnAction(e -> {
            recorder.startRecording();
        });

        // Stop Button
        stopButton.setOnAction(e -> {
            recorder.stopRecording();
        });
    }

    // Method for getting user's voice, return a formated string for the input of
    // chatGPT
    public String getUserInput() {
        String voiceInput;
        // Create file object from file path
        try {
            voiceInput = whisper.ActivateWhisper();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            voiceInput = "Error";
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            voiceInput = "Error";
        }

        return voiceInput;
    }

    public void createUI() { // create the UI of recipe (record button, stop button and prompt)

    }

    public String processUserInput() { // whisper recognizes user input and then chapGPT generate response (reformat
                                       // the main method of Whisper and ChatGPT class)
        String mealType = getUserInput();
        String ingredients = getUserInput();

        String skeletonGPTinput = "I am going to give you a list of ingredients and my preferred " +
        "meal type either: breakfast, lunch, or dinner. I want you to create a simple recipe for " + 
        "the preferred meal type I tell you. You have to use the ingredients I give you in your "  + 
        "recipe, you cannot remove any, however you can add a few extra ingredients but not too " +
        "many. Remember to keep the recipe simple. For your output I only want you to display: a " +
        "recipe title, the ingredients, and the instructions. Do not add any extra text.";


        String userInput = " My preffered meal type is " + mealType + " and my ingredients are " + ingredients;
        String finalGPTinput = skeletonGPTinput + userInput;


        String generatedRecipe;
        try {
            generatedRecipe = chatGPT.chefGPT(finalGPTinput);
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
            generatedRecipe = "Error";
        }

        return generatedRecipe;
    }

}
