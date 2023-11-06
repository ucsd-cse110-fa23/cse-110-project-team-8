package PantryPal;

// GeneratedRecipe.java
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class GeneratedRecipeScreen {
    private Scene scene;
    private Recipe recipe;
    private String recipeGenerated;
    private Stage mainStage;

    private Button editButton;
    private Button goBack;
    

    public GeneratedRecipeScreen(Recipe recipe, String mealType, String ingredients, Stage mainStage) {
        this.recipe = recipe;
        this.mainStage = mainStage;

        VBox newRoot = new VBox(); // Create a new root for the new scene

        recipeGenerated = recipe.processUserInput(mealType, ingredients);

        Text text1 = new Text("This is where the created recipe will be displayed");
        Text text2 = new Text(recipeGenerated);
        TextFlow recipeInstructions = new TextFlow(text1, text2); // adding all lines of text into one big text
        recipeInstructions.setPrefSize(200, 200);
        recipeInstructions.setStyle("-fx-font-style: italic; ");
        recipeInstructions.setTextAlignment(TextAlignment.CENTER); // aligning text to the center
        newRoot.getChildren().add(recipeInstructions); // add the textbox to the new root

        editButton = new Button("Edit"); // Create a new button
        editButton.setOnAction(e -> System.out.println("Edit Button Clicked")); // Add Temporary action to the button
        newRoot.getChildren().add(editButton); // Add the new button to the new root

        goBack = new Button("Go Back"); // Create a new button
        goBack.setOnAction(e -> {
            // switchToMainScene();
            switchToThisScene();
            System.out.println("Go Back on third(recipe) scene pressed");
        }); // Switch back to main screen
        newRoot.getChildren().add(goBack); // Add the new button to the new root

        newRoot.setSpacing(10); // Set the spacing between the children of newRoot
        
        //TODO: change null to Pos.CENTER
        newRoot.setAlignment(null); // Set the alignment of the children of newRoot

        this.scene = new Scene(newRoot, 800, 800); // Create a new scene
    }

    public Scene getScene() {
        return this.scene;
    }

    public Button getEdiButton() {
        return this.editButton;
    }
    
    public Button getGoBackButton() {
        return this.goBack;
    }

    public void switchToThisScene() {
        mainStage.setScene(mainStage.getScene());
    }
}