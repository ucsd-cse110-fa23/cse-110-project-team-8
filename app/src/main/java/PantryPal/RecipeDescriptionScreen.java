package PantryPal;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.io.IOException;
import java.net.URISyntaxException;


import org.bson.Document;

import com.dropbox.core.DbxException;

import Server.*;
import VoiceInput.DallE;

public class RecipeDescriptionScreen {
    private Scene scene;
    private Stage primaryStage;
    public Stage imageStage;
    private Scene mainScene;
    private Button editButton;
    private Button goBack;
    private Button save;
    private Button deleteButton;
    private Button done;
    private Button cancel;
    // MS 2
    private Button share;

    private Boolean dishImage;

    private String ingredientsOG;
    private String instructionsOG;
    private Boolean savedHit;
    private Button confirmDelete;
    private Button cancelDelete;
    private RecipeTitleButton recipe;
    private String newIngre;
    private String newInstruct;

    // Use for rebuild the recipes when reopen the app
    public RecipeDescriptionScreen(RecipeTitleButton recipe1, String title, String ingredients, String instructions,
            Stage primaryStage, Scene mainScene, RecipeListBody recipeList, Controller controller)
            throws Exception {

        // Change this to TRUE when you want to create images
        // Change this to FALSE when you dont want to create images
        dishImage = true;
        this.newIngre = ingredients;
        this.newInstruct = instructions;

        DropBox dropBox = new DropBox();
        if (recipe1 == null) {
            savedHit = false;
        } else {
            savedHit = true;
        }
        this.primaryStage = primaryStage;
        this.mainScene = mainScene;
        this.recipe = recipe1;

        VBox newRoot = new VBox(); // Create a new root for the new scene
        newRoot.setStyle("-fx-background-color: #BF2C34;");

        Text titleText = new Text(title);// Sets the title of the recipe
        titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 30; -fx-fill: #ecf0f1;");
        VBox titleBox = new VBox(titleText);
        titleBox.setPadding(new Insets(0, 0, 30, 0)); // Add pixels of padding at the bottom
        titleBox.setStyle("-fx-border-color: #FFFFFF; -fx-border-width:  0 0 2 0;");
        titleBox.setAlignment(Pos.CENTER);

        // INGREDIENTS
        Text ingredientsLabel = new Text("Ingredients: "); // ingredients title
        ingredientsLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20; -fx-fill: #ecf0f1;");
        TextArea ingredientsArea = new TextArea(ingredients); // ingredients text area
        ingredientsArea.setStyle("-fx-font-style: italic; -fx-background-color: #FFFFFF; " +
                "-fx-font-weight: bold; -fx-font: 18 Arial; -fx-text-fill: #000000;");
        ingredientsOG = ingredients; // original ingredients
        ingredientsArea.setWrapText(true);
        ingredientsArea.setEditable(false);
        // prepping ingredientsbox to be next to dish image
        VBox ingredientsRoot = new VBox();
        ingredientsRoot.getChildren().add(ingredientsLabel);
        ingredientsRoot.getChildren().add(ingredientsArea);

        // INSTRUCTIONS
        Text instructionsLabel = new Text("Instructions: "); // instructions title
        instructionsLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20; -fx-fill: #ecf0f1;");
        TextArea instructionsArea = new TextArea(instructions); // instructions text area
        instructionsArea.setStyle("-fx-font-style: italic; -fx-background-color: #FFFFFF; " +
                "-fx-font-weight: bold; -fx-font: 18 Arial; -fx-text-fill: #000000;");

        instructionsOG = instructions; // original instructions
        instructionsArea.setWrapText(true);
        instructionsArea.setEditable(false);

        // make a hBox for the image and the recipe Ingredients to be side to side
        HBox imageingredientsRoot = new HBox();
        if (dishImage == true) { // this inserts the image into the recipe description
            DishImage.uploadImage(imageingredientsRoot, imageStage, title + ".png");
        

            imageingredientsRoot.getChildren().add(ingredientsRoot);

        } else { // this else just sets the area were the image would go to be an empty text box
            TextArea imagetestarea = new TextArea("Images Test");
            imageingredientsRoot.getChildren().add(imagetestarea);
            imageingredientsRoot.getChildren().add(ingredientsRoot);

        }

        newRoot.getChildren().add(titleBox);
        newRoot.getChildren().add(imageingredientsRoot);
        newRoot.getChildren().add(instructionsLabel);
        newRoot.getChildren().add(instructionsArea); // add the textbox to the new root

        HBox hRoot = new HBox(); // Create a new root for the new scene
        hRoot.setAlignment(Pos.CENTER);
        hRoot.setPrefSize(500, 70); // Increased height for a larger button
        hRoot.setStyle("-fx-background-color: #BF2C34;");
        hRoot.setSpacing(15);

        String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF; " +
                "-fx-font-weight: bold; -fx-font: 15 Arial; -fx-text-fill: #000000;" +
                " -fx-pref-width: 170; -fx-pref-height: 40;";

        // CANCEL BUTTON
        // --------------------------------------------------------------------------------------------
        cancel = new Button("Cancel");
        cancel.setStyle(defaultButtonStyle);

        cancel.setOnAction(e -> {
            ingredientsArea.setEditable(false);
            instructionsArea.setEditable(false);

            // ADD: GOBACK, EDIT, DELETE
            hRoot.getChildren().add(goBack);
            hRoot.getChildren().add(editButton);

            if (savedHit == false) {
                hRoot.getChildren().add(save);
            }
            if (savedHit == true) {
                hRoot.getChildren().add(deleteButton);
            }

            hRoot.getChildren().add(share);
            // REMOVE: CANCEL, DONE
            hRoot.getChildren().remove(done);
            hRoot.getChildren().remove(cancel);

            // cancels changes
            ingredientsArea.setText(ingredientsOG);
            instructionsArea.setText(instructionsOG);

            System.out.println("Cancel Button Clicked");
        });

        // DONE BUTTON
        // --------------------------------------------------------------------------------------------
        done = new Button("Done");
        done.setStyle(defaultButtonStyle);

        done.setOnAction(e -> {
            ingredientsArea.setEditable(false);
            instructionsArea.setEditable(false);

            // ADD: GOBACK, EDIT, DELETE
            hRoot.getChildren().add(goBack);
            hRoot.getChildren().add(editButton);
            hRoot.getChildren().remove(share);
            // if previously saved or not
            if (savedHit == false) {
                hRoot.getChildren().add(save);
            }
            if (savedHit == true) {
                hRoot.getChildren().add(deleteButton);
                hRoot.getChildren().add(share);
                recipe.getRecipe().setIngredients(ingredientsArea.getText());
                recipe.getRecipe().setInstructions(instructionsArea.getText());
                this.newIngre = ingredientsArea.getText();
                this.newInstruct = instructionsArea.getText();

                recipeList.getArray().toCSV("RecipeList.csv");

                try {
                    controller.handlePut(title, ingredientsArea.getText(), instructionsArea.getText());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            // REMOVE: CANCEL, DONE
            hRoot.getChildren().remove(done);
            hRoot.getChildren().remove(cancel);

            ingredientsOG = ingredientsArea.getText();
            instructionsOG = instructionsArea.getText();

            System.out.println("Done Button Clicked");
        });

        // GO BACK BUTTON
        // --------------------------------------------------------------------------------------------
        goBack = new Button("Go Back");
        goBack.setStyle(defaultButtonStyle);

        goBack.setOnAction(e -> {
            switchToMainScene();
            System.out.println("Go Back on third(recipe) scene pressed");
        });
        hRoot.getChildren().add(goBack);

        // EDIT BUTTON
        // --------------------------------------------------------------------------------------------
        editButton = new Button("Edit");
        editButton.setStyle(defaultButtonStyle);

        editButton.setOnAction(e -> {
            ingredientsArea.setEditable(true);
            instructionsArea.setEditable(true);
            // ADD: DONE, CANCEL
            hRoot.getChildren().add(cancel);
            hRoot.getChildren().add(done);

            // REMOVE: GOBACK, DELETE
            hRoot.getChildren().remove(editButton);
            hRoot.getChildren().remove(goBack);
            hRoot.getChildren().remove(share);

            if (savedHit == false) {
                hRoot.getChildren().remove(save);
            }
            if (savedHit == true) {
                hRoot.getChildren().remove(deleteButton);
            }

            System.out.println("Edit Button Clicked");
        });
        hRoot.getChildren().add(editButton);

        // SAVE BUTTON
        // --------------------------------------------------------------------------------------------
        if (savedHit == false) {
            save = new Button("Save");
            save.setStyle(defaultButtonStyle);

            save.setOnAction(e -> {
                Recipe recipeOB = new Recipe(title, ingredients, instructions);
                recipe = new RecipeTitleButton(recipeOB);
                recipe.getRecipe().setTitle(title);
                recipe.getRecipe().setIngredients(ingredientsArea.getText());
                recipe.getRecipe().setInstructions(instructionsArea.getText());
                recipe.getRecipe().setCreationTime();


                savedHit = true;
                recipe.setDescription(scene);
                recipeList.getChildren().add(0, recipe);
                recipeList.getArray().add(recipeOB);
                recipeList.getArray().toCSV("RecipeList.csv");
                Button titleButton = recipe.getRecipeTitleButton();
                titleButton.setOnAction(e1 -> {
                    primaryStage.setScene(recipe.getDescription());
                });

                hRoot.getChildren().remove(save);
                hRoot.getChildren().remove(share);
                hRoot.getChildren().add(deleteButton);
                hRoot.getChildren().add(share);
                try {
                    controller.handleSave();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                switchToMainScene();
            });
            hRoot.getChildren().add(save);
        }

        // DELETE BUTTON
        // --------------------------------------------------------------------------------------------
        deleteButton = new Button("Delete");
        deleteButton.setStyle(defaultButtonStyle);

        deleteButton.setOnAction(e -> {

            hRoot.getChildren().add(cancelDelete);
            hRoot.getChildren().add(confirmDelete);

            hRoot.getChildren().remove(deleteButton);
            hRoot.getChildren().remove(editButton);
            hRoot.getChildren().remove(goBack);
            hRoot.getChildren().remove(share);

            System.out.println("OG Delete Button Clicked");
        });
        if (savedHit) {
            hRoot.getChildren().add(deleteButton);
        }

        // CONFIRM DELETE BUTTON
        // --------------------------------------------------------------------------------------------
        confirmDelete = new Button("Confirm Delete");
        confirmDelete.setStyle(defaultButtonStyle);

        confirmDelete.setOnAction(e -> {

            recipeList.delete(recipe);
            recipeList.getArray().delete(recipe.getRecipe());
            recipeList.getArray().toCSV("RecipeList.csv");
            try {
                // System.out.println("Server Name: "+server.getMongoDB());
                controller.handleDelete(title);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            switchToMainScene();

            System.out.println("Confirm Delete Button Clicked");
        });

        // CANCEL DELETE BUTTON
        // --------------------------------------------------------------------------------------------
        cancelDelete = new Button("Cancel Delete");
        cancelDelete.setStyle(defaultButtonStyle);

        cancelDelete.setOnAction(e -> {

            hRoot.getChildren().add(goBack);
            hRoot.getChildren().add(editButton);
            hRoot.getChildren().add(deleteButton);
            hRoot.getChildren().add(share);
            hRoot.getChildren().remove(confirmDelete);
            hRoot.getChildren().remove(cancelDelete);

            System.out.println("Cancel Delete Button Clicked");

        });

        // TITLE BUTTON
        // --------------------------------------------------------------------------------------------
        if (savedHit) {
            Button titleButton = recipe.getRecipeTitleButton();
            titleButton.setOnAction(e1 -> {
                primaryStage.setScene(recipe.getDescription());
            });

            newRoot.setSpacing(10); // Set the spacing between the children of newRoot

            newRoot.setAlignment(Pos.CENTER); // Set the alignment of the children of newRoot
            newRoot.getChildren().add(hRoot);
            this.scene = new Scene(newRoot, 800, 800); // Create a new scene
            recipe.setDescription(scene);
        } else {
            newRoot.setSpacing(10); // Set the spacing between the children of newRoot

            newRoot.setAlignment(Pos.CENTER); // Set the alignment of the children of newRoot
            newRoot.getChildren().add(hRoot);
            this.scene = new Scene(newRoot, 800, 800); // Create a new scene
        }

        // MS2 SHARE BUTTON
        share = new Button("Share");
        share.setStyle(defaultButtonStyle);
        hRoot.getChildren().add(share);
        share.setOnAction(e -> {
            String url = " ";
            Stage popupwindow = new Stage();
            popupwindow.initModality(Modality.APPLICATION_MODAL);
            try {
                url = dropBox.DropBox(title, newIngre, newInstruct);
            } catch (DbxException | IOException e1) {
                e1.printStackTrace();
            }

            SharelinkScreen SharelinkScreen = new SharelinkScreen(url, popupwindow);
            popupwindow.showAndWait();
        });
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