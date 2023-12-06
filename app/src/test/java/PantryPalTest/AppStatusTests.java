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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.ArrayList;

public class AppStatusTests {
    private Server server;
    private Controller controller;
    private Model model;
    private RequestHandler requestHandler;

    @BeforeEach
    void setUp() throws Exception {
        server = new Server();
        requestHandler = new RequestHandler(server.getMongoClient());
        model = new Model();
        controller = new Controller(model);
    }

    // US6 BDD1
    @Test
    void mongodbFail() {
        server.setURI("");
        String response = "";
        try {
            server.activateServer();
        } catch (Exception e) {
            response = e.getMessage();
        }
        assertEquals(response, "Fail to connect MongoDB");
    }

    // US6 BDD2
    @Test
    void localServerFail() {
        server.setPort(0);
        ;
        String response = "";
        try {
            server.activateServer();
        } catch (Exception e) {
            response = e.getMessage();
        }
        assertEquals(response, "Server port is already in use");
    }

}
