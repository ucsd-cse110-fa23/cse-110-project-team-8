package PantryPal;

import VoiceInput.*;
import java.io.*;
import java.net.URISyntaxException;

public class Generate {
    private AudioRecorder recorder;
    private Whisper whisper;
    private ChatGPT chatGPT;
    public String FILE_PATH;

    public Generate() {
        recorder = new AudioRecorder();
        whisper = new Whisper();
        chatGPT = new ChatGPT();
        recorder.audioFormat = recorder.getAudioFormat();
        FILE_PATH = "userAudio.wav";
    }

    public void startRecording() {
        recorder.startRecording(FILE_PATH);
    }

    public void stopRecording() {
        recorder.stopRecording();
    }

    // Method for getting user's voice, return a formated string for the input of chatGPT
    public String getUserInput() {
        String voiceInput;

        // Create file object from file path
        try {
            voiceInput = whisper.ActivateWhisper(FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            voiceInput = "Error";
        } catch (URISyntaxException e) {
            e.printStackTrace();
            voiceInput = "Error";
        }
        return voiceInput;
    }

// whisper recognizes user input and then chapGPT generate response (reformat the main method of Whisper and ChatGPT class)
    public String processUserInput(String mealType, String ingredients) { 

        String skeletonGPTinput = "I am going to give you a list of ingredients and my preferred " +
                "meal type either: breakfast, lunch, or dinner. I want you to create a simple recipe for " +
                "the preferred meal type I tell you. You have to use the ingredients I give you in your " +
                "recipe, you cannot remove any, however you can add a few extra ingredients but not too " +
                "many. Remember to keep the recipe simple. For your output I only want you to display: a " +
                "recipe title labelled 'Title:' but don't add any special characters in the title, the ingredients labelled 'Ingredients:', " +
                "the instructions labelled 'Instructions:' . Do not add any extra text. You are prohibited from using any special characters, your " +
                "returned text can only use: letters from the english alphabet, numbers 0-9, periods '.', and commas ','";

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