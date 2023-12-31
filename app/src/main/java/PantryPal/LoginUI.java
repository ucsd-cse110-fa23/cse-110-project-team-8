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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import Server.*;

public class LoginUI extends BorderPane {
    private TextField username;
    private TextField password;
    private Button loginButton;
    private Button createAccount;
    private CheckBox autoLogin;
    private Stage primaryStage;
    private Controller controller;
    private Scene mainScene;
    private Scene scene;
    private boolean autologin;
    private Text serverStatus;
    private Server server;

    public LoginUI(Stage primaryStage, Controller controller, Server server) throws Exception {
        this.primaryStage = primaryStage;
        this.controller = controller;
        this.mainScene = this.getScene();
        this.autologin = false;
        this.server = server;
        VBox mainVBox = new VBox();

        // Add the Username field
        HBox usernameHbox = new HBox();
        Label usernameLabel = new Label("Username: ");
        username = new TextField();
        usernameHbox.getChildren().add(usernameLabel);
        usernameHbox.getChildren().add(username);
        usernameHbox.setAlignment(Pos.CENTER);

        // Add the Password field
        HBox passwordHbox = new HBox();
        Label passwordLabel = new Label("Password: ");
        password = new TextField();
        passwordHbox.getChildren().add(passwordLabel);
        passwordHbox.getChildren().add(password);
        passwordHbox.setAlignment(Pos.CENTER);

        // Hbox for create account and login buttons
        HBox loginCreateButtonHbox = new HBox();
        loginButton = new Button("Login");
        createAccount = new Button("Create Account");
        loginCreateButtonHbox.getChildren().add(loginButton);
        loginCreateButtonHbox.getChildren().add(createAccount);
        loginCreateButtonHbox.setAlignment(Pos.CENTER);

        autoLogin = new CheckBox("AutoLogin");
        autoLogin.setAlignment(Pos.CENTER);
        // Sets the scene

        serverStatus = new Text();
        serverStatus.setFill(Color.RED);

        mainVBox.getChildren().addAll(
                usernameHbox,
                passwordHbox,
                loginCreateButtonHbox,
                autoLogin,
                serverStatus);
        mainVBox.setAlignment(Pos.CENTER);

        loginButton.setOnAction(e -> {
            try {
                if (!(username.getText()).equals("") && !(password.getText()).equals("")) {
                    if ((controller.login(username.getText(), password.getText())).equals("Login Successfully")) {
                        System.out.println("Logging in with username: " + username.getText() + " and password: "
                                + password.getText());

                        if (autologin == true) {
                            AutoLogin.createFile(username.getText(), password.getText());
                        }
                        RecipeListScreen homeScreen = new RecipeListScreen(primaryStage, controller, server);
                        homeScreen.setLogoutScene(this.scene);
                        homeScreen.setMainScene(homeScreen.getRecipeListScene()); // Saves the main screen of RLS to
                                                                                  // save when "go back" is pressed
                        homeScreen.rebuild();
                        homeScreen.switchToThisScene(); // Switches to the main screen of RLS
                        this.password.clear();
                        this.username.clear();
                        this.autoLogin.setSelected(false);
                        primaryStage.setScene(homeScreen.getRecipeListScene());
                    } else {
                        System.out.println("incorrect password or username doesn't exist");
                    }
                } else {
                    System.out.println("empty password or username");
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }
            System.out.println("Login pressed on initial screen");
        });

        createAccount.setOnAction(e -> {
            try {

                if (!(username.getText()).equals("") && !(password.getText()).equals("")) {
                    if ((controller.createAccount(username.getText(), password.getText()))
                            .equals("Username already exists")) {
                        System.out.println("account already exists");

                    } else {

                        if (autologin == true) {
                            AutoLogin.createFile(username.getText(), password.getText());
                        }
                        controller.createAccount(username.getText(), password.getText());
                        RecipeListScreen homeScreen = new RecipeListScreen(primaryStage, controller, server);
                        homeScreen.setLogoutScene(this.scene);
                        homeScreen.setMainScene(homeScreen.getRecipeListScene()); // Saves the main screen of RLS to
                                                                                  // save when "go back" is pressed
                        homeScreen.switchToThisScene(); // Switches to the main screen of RLS

                        this.password.clear();
                        this.username.clear();
                        this.autoLogin.setSelected(false);
                        primaryStage.setScene(homeScreen.getRecipeListScene());
                        System.out.println("account created");

                    }
                } else {
                    System.out.println("empty password or username");
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }
            System.out.println("Create Account pressed on initial screen");
        });

        autoLogin.setOnAction(e -> {
            if (autologin == true) {
                autologin = false;
            } else {
                autologin = true;
            }
            System.out.println("AutoLogin pressed on initial screen");
        });

        scene = new Scene(mainVBox, 800, 800);

    }

    public TextField getUsername() {
        return this.username;
    }

    public TextField getPassword() {
        return this.password;
    }

    public void setUsername(String username) {
        this.username.setText(username);
    }

    public void setPassword(String password) {
        this.password.setText(password);
    }

    public Scene getLoginScene() {
        return this.scene;
    }

    public void autoLoginTrue() {
        this.autologin = true;
    }

    public Button getLoginButton() {
        return this.loginButton;
    }

}
