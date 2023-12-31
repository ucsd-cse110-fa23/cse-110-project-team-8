package PantryPalTest;

import org.junit.jupiter.api.Test;
import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import PantryPal.*;
import Server.*;
import java.util.ArrayList;

public class CreateAccountTests {
    private Server server;

    private Controller controller;
    private Model model;
    private RequestHandler requestHandler;

    @BeforeEach
    void setUp() throws Exception {
        server = new Server();
        server.activateServer();
        requestHandler = new RequestHandler(server.getMongoClient());
        model = new Model();
        controller = new Controller(model);
    }

    @AfterEach
    void closeDown() throws IOException {
        server.deactivateServer();
    }

    // US1 BDD1
    // Test creating an account of new username
    @Test
    void createNewAccount() {
        controller.createAccount("CreateAccountTest", "1234");
        requestHandler.loadAccount("CreateAccountTest", "1234", "Login");
        MongoDatabase user = requestHandler.getDatabase();
        var databaseNames = server.getMongoClient().listDatabaseNames().into(new ArrayList<>());
        MongoDatabase database = server.getMongoClient().getDatabase("CreateAccountTest");

        System.out.println(database.getName());
        assertEquals(databaseNames.contains("CreateAccountTest"), true);

        user.drop();
    }

    // US1 BDD2
    // Test creating an account of already existing username
    @Test
    void createExistingAccount() {
        controller.createAccount("CreateAccountTest", "1234");
        requestHandler.loadAccount("CreateAccountTest", "1234", "Login");
        boolean createdAgain;
        MongoDatabase user = requestHandler.getDatabase();

        if (requestHandler.databaseFound(server.getMongoClient(), "CreateAccountTest")) {
            createdAgain = false;
        } else {
            createdAgain = true;
        }
        assertEquals(createdAgain, false);

        user.drop();
    }
}