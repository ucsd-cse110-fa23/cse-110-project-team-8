package PantryPal;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class UserInputScreen {
    private Scene scene;
    private String mealType;
    private String ingredients;
    private Stage primaryStage;
    private Scene mainScene;

    private Boolean firstInput;

    private Button startRecButton;
    private Button stopRecButton;
    private Button goBack;

    public UserInputScreen(RecipeList recipeList, Stage primaryStage, Scene mainScene, Generate generate) {
        this.primaryStage = primaryStage;
        this.mainScene = mainScene;

        firstInput = true;

        VBox newRoot = new VBox(); // Create a new root for the new scene
        newRoot.setStyle("-fx-background-color: #BF2C34;"); // Ashy white background color

        Text text1 = new Text(" \n\n\n\n\n To create a new recipe please state your meal type. \n\n (Breakfast, Lunch, Dinner) \n\n\n\n");
        Text text3 = new Text("Press'Start Recording' then say your meal type. \n\n Press 'Stop Recording' when you are done speaking. ");
        
        TextFlow recipeInstructions = new TextFlow(text1, text3); // adding all lines of text into one big text
        recipeInstructions.setPrefSize(200, 600);
        //recipeInstructions.setStyle("-fx-font-style: italic; ");
        recipeInstructions.setStyle("-fx-font-style: italic; -fx-background-color: #FFFFFF; " + 
        "-fx-font-weight: bold; -fx-font: 25 Arial; -fx-text-fill: #000000;");
        recipeInstructions.setTextAlignment(TextAlignment.CENTER); // aligning text to the center
        newRoot.getChildren().add(recipeInstructions); // add the textbox to the new root

         String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF; " + 
                                    "-fx-font-weight: bold; -fx-font: 15 Arial; -fx-text-fill: #000000;" +
                                    " -fx-pref-width: 170; -fx-pref-height: 40;";


                                    
        HBox footer = new HBox();
        newRoot.getChildren().add(footer);
        footer.setPrefSize(500, 100); // Increased height for a larger button
        footer.setStyle("-fx-background-color: #BF2C34;");
        footer.setSpacing(15);

    // START RECORDING ------------------------------------------------------------------------------------------------------
        startRecButton = new Button("Start Recording"); // Create a new button
        startRecButton.setStyle(defaultButtonStyle);
        startRecButton.setOnAction(e -> {
            generate.startRecording();
            System.out.println("Start Recording on Meal scene pressed");
        });
        //newRoot.getChildren().add(startRecButton); // Add the new button to the new root

    
    // STOP RECORDING ------------------------------------------------------------------------------------------------------    
        stopRecButton = new Button("Stop Recording"); // Create a new button
        stopRecButton.setStyle(defaultButtonStyle);

        stopRecButton.setOnAction(e -> {
            generate.stopRecording();
            if (firstInput == true) {
                String userInput = generate.getUserInput();
                if (userInput != null) { // Check if userInput is not null
                    this.mealType = userInput;
                    System.out.println("Stop Recording on meal scene pressed");
                    System.out.println(this.mealType);
                }
                text1.setText("\n\n\n\n Please state your ingredients. \n\n");
                text3.setText("(Example: 'salt, pepper, eggs, and bacon!') \n\n\n\n" +
                              "Press 'Start Recording' then say your ingredients. \n\n" + 
                              "Press 'Stop Recording' when you are done speaking. ");
                firstInput = false;
            } else {
                this.ingredients = generate.getUserInput(); // Get the user input
                System.out.println("Stop Recording on Ingredients scene pressed");
                System.out.println(this.ingredients);

                String recipeGenerated = generate.processUserInput(mealType, ingredients);
                String processedTitle = this.getTitle(recipeGenerated);
                String processedIngredients = this.getIngredient(recipeGenerated);
                String processedInstructions = this.getInstructions(recipeGenerated);

                RecipeDescriptionScreen generatedRecipeScreen = new RecipeDescriptionScreen(null, processedTitle, processedIngredients, processedInstructions,
                        primaryStage, mainScene,recipeList);
                generatedRecipeScreen.switchToThisScene();
            }
        });
        
    
    // GO BACK ------------------------------------------------------------------------------------------------------    
        goBack = new Button("Go Back"); // Create a GO BACK button
        goBack.setStyle(defaultButtonStyle);
        goBack.setOnAction(e -> {
            switchToMainScene();
            System.out.println("Go Back on input scene pressed");
        }); // Switch back to main screen

        footer.getChildren().add(goBack); // Add the new button to the new root
        footer.getChildren().add(startRecButton); // Add the new button to the new root
        footer.getChildren().add(stopRecButton); // Add the new button to the new root
        footer.setAlignment(Pos.CENTER);


        newRoot.setSpacing(10); // Set the spacing between the children of newRoot

        newRoot.setAlignment(Pos.CENTER); // Set the alignment of the children of newRoot

        this.scene = new Scene(newRoot, 800, 800); // Create a new scene
    }



    public String getTitle(String recipeGenerated) {
        System.out.println(recipeGenerated);
        String output = "";
        String[] words = recipeGenerated.split("\\s+");

        int i = 0;
        while (i < words.length - 1) {
            System.out.println(words[i]);
            i++;
            
        }

        int index = 0;
        while (index < words.length) {
            if (words[index].equals("Title:")) {
                break;
            }
            index++;
        }
        index++;
        while (index < words.length && !words[index].equals("Ingredients:")) {
            System.out.println("getTitle: " + index + " " + words[index]);
            output += words[index] + " ";
            index++;
        }
        return output;
    }

    public String getIngredient(String recipeGenerated) {
        String output = "";
        String[] words = recipeGenerated.split("\\s+");
        int index = 0;
        
        while (index < words.length) {
            if (words[index].equals("Ingredients:")) {
                break;
            }
            index++;
        }
        index++;
        while (index < words.length && !words[index].equals("Instructions:")) {
            output += words[index] + " ";
            index++;
        }
        return output;
    }

    public String getInstructions(String recipeGenerated) {
        String output = "";
        String[] words = recipeGenerated.split("\\s+");
        int index = 0;
        while (index < words.length) {
            if (words[index].equals("Instructions:")) {
                break;
            }
            index++;
        }
        index++;
        while (index < words.length) {
            output += words[index] + " ";
            index++;
        }
        return output;
    }



    public Scene getScene() {
        return this.scene;
    }

    public String getMealType() {
        return this.mealType;
    }

    public String getIngredients() {
        return this.ingredients;
    }

    public Button getStartRecButton() {
        return this.startRecButton;
    }

    public Button getStopRecButton() {
        return this.stopRecButton;
    }

    public Button getGoBackButton() {
        return this.goBack;
    }

    public void switchToThisScene() {
        primaryStage.setScene(this.scene);
    }

    public void switchToMainScene() {
        primaryStage.setScene(mainScene);
    }

}