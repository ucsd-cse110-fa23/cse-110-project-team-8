package PantryPal;

// GeneratedRecipe.java
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class GeneratedRecipeScreen {
    private Scene scene;
    private String recipeGenerated;
    private Stage primaryStage;
    private Scene mainScene;
    private Button editButton;
    private Button goBack;
    private Button save;

    public GeneratedRecipeScreen(RecipeList recipeList, String mealType, String ingredients, Stage primaryStage,
            Scene mainScene, Generate generate) {
        this.primaryStage = primaryStage;
        this.mainScene = mainScene;

        VBox newRoot = new VBox(); // Create a new root for the new scene

        recipeGenerated = generate.processUserInput(mealType, ingredients);

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
            switchToMainScene();
            System.out.println("Go Back on third(recipe) scene pressed");
        }); // Switch back to main screen
        newRoot.getChildren().add(goBack); // Add the new button to the new root

        save = new Button("Save");
        save.setOnAction(e -> {
            Recipe recipe = new Recipe();
            recipe.setTitle(this.getTitle(recipeGenerated));
            recipe.setIngredients(this.getIngredient(recipeGenerated));
            recipe.setInstructions(this.getInstructions(recipeGenerated));
            recipe.setTime(this.getTime(recipeGenerated));
            recipe.setButtonTitle();

            recipe.getRecipeTitle().setText(recipeGenerated);
            recipe.getRecipeBody().setText(recipeGenerated);
            recipe.setDescription(scene);
            recipeList.getChildren().add(recipe);

            Button titleButton = recipe.getRecipeTitleButton();
            titleButton.setOnAction(e1 -> {
                primaryStage.setScene(recipe.getDescription());
            });

            switchToMainScene();
        });
        newRoot.getChildren().add(save);

        newRoot.setSpacing(10); // Set the spacing between the children of newRoot

        newRoot.setAlignment(Pos.CENTER); // Set the alignment of the children of newRoot

        this.scene = new Scene(newRoot, 800, 800); // Create a new scene
    }

    public GeneratedRecipeScreen(Recipe recipe, String title, String ingredients, String instructions,
            String time, Stage primaryStage, Scene mainScene) {
        this.primaryStage = primaryStage;
        this.mainScene = mainScene;

        VBox newRoot = new VBox(); // Create a new root for the new scene

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
            switchToMainScene();
            System.out.println("Go Back on third(recipe) scene pressed");
        }); // Switch back to main screen
        newRoot.getChildren().add(goBack); // Add the new button to the new root

        Button titleButton = recipe.getRecipeTitleButton();
        titleButton.setOnAction(e1 -> {
            primaryStage.setScene(recipe.getDescription());
        });
        switchToMainScene();

        newRoot.setSpacing(10); // Set the spacing between the children of newRoot

        newRoot.setAlignment(Pos.CENTER); // Set the alignment of the children of newRoot

        this.scene = new Scene(newRoot, 800, 800); // Create a new scene
        recipe.setDescription(scene);
    }

    public String getTitle(String recipeGenerated) {
        String output = "";
        String[] words = recipeGenerated.split("\\s+");
        int index = 0;
        while (index < words.length) {
            index++;
            if (words[index].equals("Title:")) {
                break;
            }
        }
        while (index < words.length && !words[index].equals("Ingredients:")) {
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
            index++;
            if (words[index].equals("Ingredients:")) {
                break;
            }
        }
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
            index++;
            if (words[index].equals("Instructions:")) {
                break;
            }
        }
        while (index < words.length && !words[index].equals("Time:")) {
            output += words[index] + " ";
            index++;
        }
        return output;
    }

    public String getTime(String recipeGenerated) {
        String output = "";
        String[] words = recipeGenerated.split("\\s+");
        int index = 0;
        while (index < words.length) {
            if (words[index].equals("Time:")) {
                break;
            }
            index++;
        }
        while (index < words.length) {
            output += words[index] + " ";
            index++;
        }
        return output;
    }

    public Scene getScene() {
        return this.scene;
    }

    public Button getEditButton() {
        return this.editButton;
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