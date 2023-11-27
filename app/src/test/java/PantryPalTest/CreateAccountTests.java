package PantryPalTest;

import org.junit.jupiter.api.Test;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.opencsv.CSVReader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import PantryPal.*;
import Server.*;
import java.util.ArrayList;


import java.io.FileReader;
import com.opencsv.exceptions.CsvValidationException;

public class CreateAccountTests {
    private Server server;
    private Controller controller;
    private Model model;

    @BeforeEach
    void setUp() throws Exception {
        server = new Server();
        server.activateServer();
        model = new Model();
        controller = new Controller(model);
    }

    @AfterEach
    void closeDown() throws IOException{
        server.deactivateServer();
    }

    // US1 BDD1
    // Test creating an account of new username
    @Test
    void createNewAccount() {
        // controller.createAccount("CreateAccountTest", "1234");
        // MongoDatabase user = controller.loadAccount();

        // var databaseNames = server.getMongo().listDatabaseNames().into(new ArrayList<>());
        // MongoDatabase database = server.getMongo().getDatabase("CreateAccountTest");
        // System.out.println(database.getName());
        // assertEquals(databaseNames.contains("CreateAccountTest"), true);

        // user.drop();

    }

    // US1 BDD2
    // Test creating an account of already existing username
    @Test
    void createExistingAccount() {
        // server.createAccountInDB("CreateAccountTest", "1234");
        // boolean createdAgain;
        // MongoDatabase user = server.getMongoDB();

        // if(server.acountExist("CreateAccountTest")) {
        //     createdAgain = false;
        // } else {
        //     createdAgain = true;
        // }

        // assertEquals(createdAgain, false);

        // user.drop();
    }
}