package PantryPal;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Server.*;


public class LoginUI extends BorderPane{
    private TextField username;
    private TextField password;
    private Button loginButton;
    private Button createAccount;
    private CheckBox autoLogin;
    private Stage primaryStage;
    private Controller controller;
    private Scene mainScene;
    private Scene scene;
    private Server server;


    public LoginUI(Stage primaryStage , Controller controller, Server server) throws Exception{   
        this.primaryStage = primaryStage;     
        this.controller = controller;
        this.mainScene = this.getScene();
        this.server = server;
        VBox mainVBox = new VBox();

        //Add the Username field
        HBox usernameHbox = new HBox();
        Label usernameLabel = new Label("Username: ");
        username = new TextField();
        usernameHbox.getChildren().add(usernameLabel);
        usernameHbox.getChildren().add(username);
        usernameHbox.setAlignment(Pos.CENTER);

        //Add the Password field
        HBox passwordHbox = new HBox(); 
        Label passwordLabel = new Label("Password: ");
        password = new TextField();
        passwordHbox.getChildren().add(passwordLabel);
        passwordHbox.getChildren().add(password);
        passwordHbox.setAlignment(Pos.CENTER);
        
        //Hbox for create account and login buttons
        HBox loginCreateButtonHbox = new HBox();
        loginButton = new Button("Login");
        createAccount = new Button("Create Account");
        loginCreateButtonHbox.getChildren().add(loginButton);
        loginCreateButtonHbox.getChildren().add(createAccount);
        loginCreateButtonHbox.setAlignment(Pos.CENTER);
        
        autoLogin = new CheckBox("AutoLogin");
        autoLogin.setAlignment(Pos.CENTER);
        //Sets the scene    
       
        mainVBox.getChildren().addAll(
            usernameHbox, 
            passwordHbox,
            loginCreateButtonHbox,
            autoLogin
        );
        mainVBox.setAlignment(Pos.CENTER);

        loginButton.setOnAction(e -> {
            try {
                server.loadAccount();
                RecipeListScreen homeScreen = new RecipeListScreen(primaryStage, controller);
                
                homeScreen.setMainScene(homeScreen.getRecipeListScene()); //Saves the main screen of RLS to save when "go back" is pressed
                homeScreen.rebuild(); 
                homeScreen.switchToThisScene(); //Switches to the main screen of RLS
                primaryStage.setScene(homeScreen.getRecipeListScene());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            System.out.println("Login pressed on initial screen");
        });

        createAccount.setOnAction(e -> {
            try {

                server.createAccountInDB(username.getText(),password.getText());

                // User actions: they input their username and password and press create account
                // check if account exists in data base
                // case 1 is does not exist in db
                    // we want to upload username as name of collection
                // case 2 is does exist in db
                    // we tell them account already exists
                


                    
                RecipeListScreen homeScreen = new RecipeListScreen(primaryStage, controller);
                homeScreen.setMainScene(homeScreen.getRecipeListScene()); //Saves the main screen of RLS to save when "go back" is pressed
                // homeScreen.rebuild(); 
                homeScreen.switchToThisScene(); //Switches to the main screen of RLS
                primaryStage.setScene(homeScreen.getRecipeListScene());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            System.out.println("Create Account pressed on initial screen");
        });

        autoLogin.setOnAction(e -> {

            System.out.println("AutoLogin pressed on initial screen");
        });

        scene = new Scene(mainVBox, 800, 800);
    
    }

    public TextField getUsername(){
        return this.username;
    }

    public TextField getPassword(){
        return this.password;
    }

    public Scene getLoginScene(){
        return this.scene;
    }

}
