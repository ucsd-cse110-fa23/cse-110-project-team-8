package PantryPal;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class SavedRecipeScreen {
    private Scene scene;
    private String recipeGenerated;
    private Stage primaryStage;
    private Scene mainScene;
    private Button editButton;
    private Button goBack;
    private Button save;
    private Button deleteButton;
    private Button done;
    private Button cancel;


    // Use for rebuild the recipes when reopen the app
    public SavedRecipeScreen(Recipe recipe, String title, String ingredients, String instructions,
        Stage primaryStage, Scene mainScene) {
        this.primaryStage = primaryStage;
        this.mainScene = mainScene;

        VBox newRoot = new VBox(); // Create a new root for the new scene

        Text text1 = new Text(title);
        TextField text2 = new TextField("\n" + ingredients + "\n" + instructions);
        text2.setPrefSize(500,500);
        text2.setEditable(false);
        TextFlow recipeInstructions = new TextFlow(text1, text2); // adding all lines of text into one big text
        recipeInstructions.setPrefSize(1000, 1000);
        recipeInstructions.setStyle("-fx-font-style: italic; ");
        recipeInstructions.setTextAlignment(TextAlignment.CENTER); // aligning text to the center
        newRoot.getChildren().add(recipeInstructions); // add the textbox to the new root

    // DONE BUTTON --------------------------------------------------------------------------------------------
        done = new Button("Done");

        done.setOnAction(e -> {

            System.out.println("Done Button Clicked");
        });

    // CANCEL BUTTON -------------------------------------------------------------------------------------------- 
        cancel = new Button("Cancel");

        cancel.setOnAction(e -> {
            text2.setEditable(false);

            // ADD: GOBACK, DELETE
            newRoot.getChildren().add(editButton);
            newRoot.getChildren().add(goBack);
            newRoot.getChildren().add(deleteButton);

            // REMOVE: Done
            newRoot.getChildren().remove(done);
            newRoot.getChildren().remove(cancel);

            //cancels changes
            text2.setText("\n" + ingredients + "\n" + instructions);

            System.out.println("Cancel Button Clicked");
        });
        
        
        

    // EDIT BUTTON --------------------------------------------------------------------------------------------
        editButton = new Button("Edit"); // Create a new button

        editButton.setOnAction(e -> {
            text2.setEditable(true);
                
            // ADD: DONE, CANCEL
            newRoot.getChildren().add(done);
            newRoot.getChildren().add(cancel);


            // REMOVE: GOBACK, DELETE
            newRoot.getChildren().remove(editButton);
            newRoot.getChildren().remove(goBack);
            newRoot.getChildren().remove(deleteButton);

            System.out.println("Edit Button Clicked");
        }); 
        
        newRoot.getChildren().add(editButton); // Add the new button to the new root


    // GO BACK BUTTON --------------------------------------------------------------------------------------------
        goBack = new Button("Go Back"); // Create a new button
        goBack.setOnAction(e -> {
            // switchToMainScene();
            switchToMainScene();
            System.out.println("Go Back on third(recipe) scene pressed");
        }); // Switch back to main screen
        newRoot.getChildren().add(goBack); // Add the new button to the new root


    // DELETE BUTTON --------------------------------------------------------------------------------------------
        deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            //recipeList.getChildren().remove(recipe);
            System.out.println("Delete Button Clicked");
            //switchToMainScene();
        });
        newRoot.getChildren().add(deleteButton);
        

    // TITLE BUTTON --------------------------------------------------------------------------------------------
        Button titleButton = recipe.getRecipeTitleButton();
        titleButton.setOnAction(e1 -> {
            primaryStage.setScene(recipe.getDescription());
        });

        newRoot.setSpacing(10); // Set the spacing between the children of newRoot

        newRoot.setAlignment(Pos.CENTER); // Set the alignment of the children of newRoot

        this.scene = new Scene(newRoot, 800, 800); // Create a new scene
        recipe.setDescription(scene);
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

    public Button getEditButton() {
        return this.editButton;
    }

    public Button getGoBackButton() {
        return this.goBack;
    }

    public Button getDeleteButton() {
        return this.deleteButton;
    }

    public void switchToThisScene() {
        primaryStage.setScene(this.scene);
    }

    public void switchToMainScene() {
        primaryStage.setScene(mainScene);
    }

    public Scene getMainScene() {
        return this.mainScene;
    }
}