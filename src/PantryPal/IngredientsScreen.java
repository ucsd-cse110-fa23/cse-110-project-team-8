package PantryPal;

// Ingredients.java
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class IngredientsScreen {
    private Scene scene;
    private Recipe recipe;
    private String ingredients;
    private Stage mainStage;
    private String mealType;

    private Button startRecButton;
    private Button stopRecButton;
    private Button goBack;

    public IngredientsScreen(Recipe recipe, String mealType ,Stage mainStage) {
        this.recipe = recipe;
        this.mealType = mealType;
        this.mainStage = mainStage;

        VBox newRoot = new VBox(); // Create a new root for the new scene

        Text text1 = new Text("Please state your ingredients. \n");
        Text text2 = new Text("'Start Recording' then say your ingredients. Press 'Stop Recording' when you are done speaking. ");
        TextFlow recipeInstructions = new TextFlow(text1, text2); // adding all lines of text into one big text
        recipeInstructions.setPrefSize(200, 200);
        recipeInstructions.setStyle("-fx-font-style: italic; ");
        recipeInstructions.setTextAlignment(TextAlignment.CENTER); // aligning text to the center
        newRoot.getChildren().add(recipeInstructions); // add the textbox to the new root

        startRecButton = new Button("Start Recording"); // Create a new button
        startRecButton.setOnAction(e -> {
            this.recipe.startRecording();
            System.out.println("Start Recording on Ingredients scene pressed");
        }); // Add Temporary action
        newRoot.getChildren().add(startRecButton); // Add the new button to the new root

        stopRecButton = new Button("Stop Recording"); // Create a new button
        stopRecButton.setOnAction(e -> {
            //this.recipe.stopRecording();
            //this.ingredients = this.recipe.getUserInput(); // Get the user input
            System.out.println("Stop Recording on Ingredients scene pressed");
            //System.out.println(this.ingredients);
            switchToGeneratedRecipeScreen();
        });
        newRoot.getChildren().add(stopRecButton); // Add the new button to the new root

        goBack = new Button("Go Back Home"); // Create a new button
        goBack.setOnAction(e -> {
            // switchToMealSelect();
            System.out.println("Go Back on Ingredients scene pressed");
        }); // Switch back to meal select screen
        newRoot.getChildren().add(goBack); // Add the new button to the new root

        newRoot.setSpacing(10); // Set the spacing between the children of newRoot
        
        //TODO: change null to Pos.CENTER
        newRoot.setAlignment(null); // Set the alignment of the children of newRoot

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
        mainStage.setScene(this.scene);
    }

    public void switchToGeneratedRecipeScreen() {
        GeneratedRecipeScreen generatedRecipeScreen = new GeneratedRecipeScreen(this.recipe, mealType, ingredients, this.mainStage);
        generatedRecipeScreen.switchToThisScene();
    }
}