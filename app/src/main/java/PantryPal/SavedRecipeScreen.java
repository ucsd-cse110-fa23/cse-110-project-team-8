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
    //private Button save;
    private Button deleteButton;
    private Button done;
    private Button cancel;

    private String ingredientsOG;
    private String instructionsOG;
    //private Boolean savedHit;
    private Button confirmDelete;
    private Button cancelDelete;
    private Recipe recipe;


    // Use for rebuild the recipes when reopen the app
    public SavedRecipeScreen(Recipe recipe, String title, String ingredients, String instructions,
        Stage primaryStage, Scene mainScene, RecipeList recipeList) {
            
        this.primaryStage = primaryStage;
        this.mainScene = mainScene;

        VBox newRoot = new VBox(); // Create a new root for the new scene

        Text titleText = new Text(title);//Sets the title of the recipe
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Text ingredientsLabel = new Text("Ingredients: ");  //ingredients title
        TextArea ingredientsArea = new TextArea(ingredients);  //ingredients text area
        ingredientsOG = ingredients; //original ingredients
        ingredientsArea.setWrapText(true);
        ingredientsArea.setEditable(false);

        Text instructionsLabel = new Text("Instructions: "); //instructions title
        TextArea instructionsArea = new TextArea(instructions); //instructions text area
        instructionsOG = instructions; //original instructions
        instructionsArea.setWrapText(true);
        instructionsArea.setEditable(false);

        newRoot.getChildren().add(titleText);
        newRoot.getChildren().add(ingredientsLabel);
        newRoot.getChildren().add(ingredientsArea); // add the textbox to the new root
        newRoot.getChildren().add(instructionsLabel);
        newRoot.getChildren().add(instructionsArea); // add the textbox to the new root

        HBox hRoot = new HBox(); // Create a new root for the new scene

       // savedHit = false;
       

    // CANCEL BUTTON -------------------------------------------------------------------------------------------- 
        cancel = new Button("Cancel");

        cancel.setOnAction(e -> {
            ingredientsArea.setEditable(false);
            instructionsArea.setEditable(false);

            // ADD: GOBACK, EDIT, DELETE
            hRoot.getChildren().add(goBack);
            hRoot.getChildren().add(editButton);;
            hRoot.getChildren().add(deleteButton);
            
            // REMOVE: CANCEL, DONE
            hRoot.getChildren().remove(done);
            hRoot.getChildren().remove(cancel);

            //cancels changes
            ingredientsArea.setText(ingredientsOG);
            instructionsArea.setText(instructionsOG);


            System.out.println("Cancel Button Clicked");
        });
        
    // DONE BUTTON --------------------------------------------------------------------------------------------
        done = new Button("Done");

        done.setOnAction(e -> {
            ingredientsArea.setEditable(false);
            instructionsArea.setEditable(false);

            // ADD: GOBACK, EDIT, DELETE
            hRoot.getChildren().add(goBack);
            hRoot.getChildren().add(editButton);
            hRoot.getChildren().add(deleteButton);
            

            // REMOVE: CANCEL, DONE
            hRoot.getChildren().remove(done);
            hRoot.getChildren().remove(cancel);

            recipe.setIngredients(ingredientsArea.getText());
            recipe.setInstructions(instructionsArea.getText());


            //TODO update the CSV
            recipeList.toCSV(null);

            
            ingredientsOG = ingredientsArea.getText();
            instructionsOG = instructionsArea.getText();

            System.out.println("Done Button Clicked");
        });
        
        
    // GO BACK BUTTON --------------------------------------------------------------------------------------------
        goBack = new Button("Go Back"); // Create a new button
        goBack.setOnAction(e -> {
            switchToMainScene();
            System.out.println("Go Back on third(recipe) scene pressed");
        }); // Switch back to main screen
        hRoot.getChildren().add(goBack); // Add the new button to the new root


    // EDIT BUTTON --------------------------------------------------------------------------------------------
        editButton = new Button("Edit"); // Create a new button

        editButton.setOnAction(e -> {
            ingredientsArea.setEditable(true);
            instructionsArea.setEditable(true);
            // ADD: DONE, CANCEL
            hRoot.getChildren().add(cancel);  //Add done and cancel buttons when edit is pressed
            hRoot.getChildren().add(done);


            // REMOVE: GOBACK, DELETE
            hRoot.getChildren().remove(editButton);
            hRoot.getChildren().remove(goBack);
            hRoot.getChildren().remove(deleteButton);
            
            System.out.println("Edit Button Clicked");
        }); 
        
        hRoot.getChildren().add(editButton); // Add the new button to the new root


    
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
        hRoot.getChildren().add(deleteButton);

        // CONFIRM DELETE BUTTON --------------------------------------------------------------------------------------------
        confirmDelete = new Button("Confirm Delete");
        confirmDelete.setOnAction(e -> {

            //TODO: delete from csv
            recipeList.toCSV(recipe);
            recipeList.delete(recipe);
            //recipeList.toCSV(recipe);
            //recipeList.delete(recipe);
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

    // TITLE BUTTON --------------------------------------------------------------------------------------------
        Button titleButton = recipe.getRecipeTitleButton();
        titleButton.setOnAction(e1 -> {
            primaryStage.setScene(recipe.getDescription());
        });

        newRoot.setSpacing(10); // Set the spacing between the children of newRoot

        newRoot.setAlignment(Pos.CENTER); // Set the alignment of the children of newRoot
        newRoot.getChildren().add(hRoot);
        this.scene = new Scene(newRoot, 800, 800); // Create a new scene
        recipe.setDescription(scene);
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