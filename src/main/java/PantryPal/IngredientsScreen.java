package main.java.PantryPal;

// Ingredients.java
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class IngredientsScreen {
    private Scene scene;
    private String ingredients;
    private Stage primaryStage;
    private Scene mainScene;

    private Button startRecButton;
    private Button stopRecButton;
    private Button goBack;

    public IngredientsScreen(RecipeList recipeList, String mealType, Stage primaryStage, Scene mainScene,
            Generate generate) {
        this.primaryStage = primaryStage;
        this.mainScene = mainScene;

        VBox newRoot = new VBox(); // Create a new root for the new scene

        Text text1 = new Text("Please state your ingredients. \n");
        Text text2 = new Text(
                "'Start Recording' then say your ingredients. Press 'Stop Recording' when you are done speaking. ");
        TextFlow recipeInstructions = new TextFlow(text1, text2); // adding all lines of text into one big text
        recipeInstructions.setPrefSize(200, 200);
        recipeInstructions.setStyle("-fx-font-style: italic; ");
        recipeInstructions.setTextAlignment(TextAlignment.CENTER); // aligning text to the center
        newRoot.getChildren().add(recipeInstructions); // add the textbox to the new root

        startRecButton = new Button("Start Recording"); // Create a new button
        startRecButton.setOnAction(e -> {
            generate.startRecording();
            System.out.println("Start Recording on Ingredients scene pressed");
        }); // Add Temporary action
        newRoot.getChildren().add(startRecButton); // Add the new button to the new root

        stopRecButton = new Button("Stop Recording"); // Create a new button
        stopRecButton.setOnAction(e -> {
            generate.stopRecording();
            this.ingredients = generate.getUserInput(); // Get the user input
            System.out.println("Stop Recording on Ingredients scene pressed");
            System.out.println(this.ingredients);
            GeneratedRecipeScreen generatedRecipeScreen = new GeneratedRecipeScreen(recipeList, mealType, ingredients,
                    primaryStage, mainScene, generate);
            generatedRecipeScreen.switchToThisScene();
        });
        newRoot.getChildren().add(stopRecButton); // Add the new button to the new root

        goBack = new Button("Go Back Home"); // Create a new button
        goBack.setOnAction(e -> {
            switchToMainScene();
            System.out.println("Go Back on Ingredients scene pressed");
        }); // Switch back to meal select screen
        newRoot.getChildren().add(goBack); // Add the new button to the new root

        newRoot.setSpacing(10); // Set the spacing between the children of newRoot

        newRoot.setAlignment(Pos.CENTER); // Set the alignment of the children of newRoot

        this.scene = new Scene(newRoot, 800, 800); // Create a new scene
    }

    public Scene getScene() {
        return this.scene;
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