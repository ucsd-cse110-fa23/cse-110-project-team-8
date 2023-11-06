package test;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import javafx.application.Platform;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import VoiceInput.*;
import PantryPal.*;

public class VoiceInputTest {
    private TestRecipe testRecipe;

    @BeforeEach
    void setUp() {
        testRecipe = new TestRecipe();
        testRecipe.setPath("audio/userAudio.wav");
    }

    @Test
    public void testRecipe() {
        String testinput = testRecipe.getUserInput();
        assertEquals();
    }

    @Test
    public void testGPT() {
        String testGPTinput = "I want you to give print the numbers '123' exactly like that, do not type anything else.";

        String testGPToutput;
        
        try {
            testGPToutput = chatGPT.chefGPT(testGPTinput);
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
            testGPToutput = "Error";
        }

        assertEquals("\n" + "\n" + "123", testGPToutput);
    }
}