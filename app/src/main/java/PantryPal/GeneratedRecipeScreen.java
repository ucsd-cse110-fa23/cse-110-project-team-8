package PantryPal;

// GeneratedRecipe.java
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

public class GeneratedRecipeScreen {
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
    private Boolean savedHit;
    private Button confirmDelete;
    private Button cancelDelete;
    private Recipe recipe;

    private String ingredientsOG;
    private String instructionsOG;


    public GeneratedRecipeScreen(RecipeList recipeList, String userMealType, String userIngredients, Stage primaryStage,
            Scene mainScene, Generate generate) {
        this.primaryStage = primaryStage;
        this.mainScene = mainScene;

        VBox vRoot = new VBox(); // Create a new root for the new scene

        recipeGenerated = generate.processUserInput(userMealType, userIngredients);

        Text title = new Text(this.getTitle(recipeGenerated));

        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Text ingredientsLabel = new Text("Ingredients: ");
        TextArea ingredients = new TextArea(this.getIngredient(recipeGenerated));
        ingredientsOG = this.getIngredient(recipeGenerated);
        ingredients.setWrapText(true);
        ingredients.setEditable(false);
        
        Text instructionsLabel = new Text("Instructions: ");
        TextArea instructions = new TextArea(this.getInstructions(recipeGenerated));
        instructionsOG = this.getInstructions(recipeGenerated);
        instructions.setWrapText(true);
        instructions.setEditable(false);

        vRoot.getChildren().add(title);
        vRoot.getChildren().add(ingredientsLabel);
        vRoot.getChildren().add(ingredients); // add the textbox to the new root
        vRoot.getChildren().add(instructionsLabel);
        vRoot.getChildren().add(instructions); // add the textbox to the new root

        HBox hRoot = new HBox(); // Create a new root for the new scene

        savedHit = false;


        // GOBACK BUTTON --------------------------------------------------------------------------------------------
        goBack = new Button("Go Back"); // Create a new button
        goBack.setOnAction(e -> {
            // switchToMainScene();
            switchToMainScene();
            System.out.println("Go Back on third(recipe) scene pressed");
        }); // Switch back to main screen
        hRoot.getChildren().add(goBack); // Add the new button to the new root

        
    // EDIT BUTTON --------------------------------------------------------------------------------------------
        editButton = new Button("Edit"); // Create a new button

        editButton.setOnAction(e -> {
            ingredients.setEditable(true);
            instructions.setEditable(true);
                
            // ADD: DONE, CANCEL
            hRoot.getChildren().add(cancel);
            hRoot.getChildren().add(done);


            // REMOVE: GOBACK, DELETE
            hRoot.getChildren().remove(editButton);
            hRoot.getChildren().remove(goBack);

            if (savedHit == false){
                hRoot.getChildren().remove(save);
            }
            
            
            if (savedHit == true){
                hRoot.getChildren().remove(deleteButton);
            }
    

            System.out.println("Edit Button Clicked");
        }); 
        hRoot.getChildren().add(editButton); // Add the new button to the new root


        // CANCEL BUTTON -------------------------------------------------------------------------------------------- 
        cancel = new Button("Cancel");

        cancel.setOnAction(e -> {
            ingredients.setEditable(false);
            instructions.setEditable(false);

            // ADD: GOBACK, EDIT, DELETE
            hRoot.getChildren().add(goBack);
            hRoot.getChildren().add(editButton);;

            if (savedHit == false){
                hRoot.getChildren().add(save);
            }

   
            if (savedHit == true){
                hRoot.getChildren().add(deleteButton);
            }
            


            // REMOVE: CANCEL, DONE
            hRoot.getChildren().remove(done);
            hRoot.getChildren().remove(cancel);

            //cancels changes
            ingredients.setText(ingredientsOG);
            instructions.setText(instructionsOG);


            System.out.println("Cancel Button Clicked");
        });

        // DONE BUTTON --------------------------------------------------------------------------------------------
        done = new Button("Done");

        done.setOnAction(e -> {

            ingredients.setEditable(false);
            instructions.setEditable(false);

            // ADD: GOBACK, EDIT, DELETE
            hRoot.getChildren().add(goBack);
            hRoot.getChildren().add(editButton);;

            if (savedHit == false){
                hRoot.getChildren().add(save);
            }

   
            if (savedHit == true){
                hRoot.getChildren().add(deleteButton);
            }

            // REMOVE: CANCEL, DONE
            hRoot.getChildren().remove(done);
            hRoot.getChildren().remove(cancel);


            ingredientsOG = ingredients.getText();
            instructionsOG = instructions.getText();

            System.out.println("Done Button Clicked");
        });

        // SAVE BUTTON --------------------------------------------------------------------------------------------
        save = new Button("Save");
        save.setOnAction(e -> {
            recipe = new Recipe();
            recipe.setTitle(title.getText());
            recipe.setIngredients(ingredients.getText());
            recipe.setInstructions(instructions.getText());
            recipe.setButtonTitle();

            savedHit = true;
            // recipe.getRecipeTitle().setText(recipeGenerated);
            // recipe.getRecipeBody().setText(recipeGenerated);
            recipe.setDescription(scene);
            recipeList.getChildren().add(recipe);

            Button titleButton = recipe.getRecipeTitleButton();
            titleButton.setOnAction(e1 -> {
                primaryStage.setScene(recipe.getDescription());
            });

            recipeList.toCSV(null);

            hRoot.getChildren().remove(save);
            hRoot.getChildren().add(deleteButton);

            switchToMainScene();
        });
        hRoot.getChildren().add(save);

  
        // DELETE BUTTON --------------------------------------------------------------------------------------------
        deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {

            hRoot.getChildren().add(cancelDelete);
            hRoot.getChildren().add(confirmDelete);

            hRoot.getChildren().remove(deleteButton);
            hRoot.getChildren().remove(editButton);
            hRoot.getChildren().remove(goBack);

            //recipeList.getChildren().remove(recipe);
            System.out.println("OG Delete Button Clicked");
        });

        // CONFIRM DELETE BUTTON --------------------------------------------------------------------------------------------
        confirmDelete = new Button("Confirm Delete");
        confirmDelete.setOnAction(e -> {

            recipeList.toCSV(recipe);
            recipeList.delete(recipe);
            switchToMainScene();

            System.out.println( "Confirm Delete Button Clicked");
        });

        // CANCEL DELETE BUTTON --------------------------------------------------------------------------------------------
        cancelDelete = new Button("Cancel Delete");
        cancelDelete.setOnAction(e -> {
            
            hRoot.getChildren().add(goBack);
            hRoot.getChildren().add(editButton);
            hRoot.getChildren().add(deleteButton);

            hRoot.getChildren().remove(confirmDelete);
            hRoot.getChildren().remove(cancelDelete);
            


            System.out.println("Cancel Delete Button Clicked");

        });

        


        // newRoot.setSpacing(10); // Set the spacing between the children of newRoot

        // newRoot.setAlignment(Pos.CENTER); // Set the alignment of the children of newRoot

        vRoot.getChildren().add(hRoot);
        this.scene = new Scene(vRoot, 800, 800); // Create a new scene
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

    // public String getTime(String recipeGenerated) {
    //     String output = "";
    //     String[] words = recipeGenerated.split("\\s+");
    //     int index = 0;
    //     while (index < words.length) {
    //         if (words[index].equals("Time:")) {
    //             break;
    //         }
    //         index++;
    //     }
    //     while (index < words.length) {
    //         output += words[index] + " ";
    //         index++;
    //     }
    //     return output;
    // }

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