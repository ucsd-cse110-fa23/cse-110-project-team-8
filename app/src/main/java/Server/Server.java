package Server;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertManyOptions;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.sun.net.httpserver.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class Server {

  // initialize server port and hostname
  private static final int SERVER_PORT = 8100;
  private static final String SERVER_HOSTNAME = "localhost";
  private HttpServer server;
  private ThreadPoolExecutor threadPoolExecutor;
  private MongoClient mongoClient;

  public void activateServer() throws IOException {
    // create a thread pool to handle requests
    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
    this.threadPoolExecutor = threadPoolExecutor;
    // create a mongoDB to store data
    String uri = "mongodb+srv://kaz006:golf1122@cse110lab6.vmgxl2s.mongodb.net/?retryWrites=true&w=majority";

    this.mongoClient = MongoClients.create(uri);

    // create a server
    HttpServer server = HttpServer.create(
        new InetSocketAddress(SERVER_HOSTNAME, SERVER_PORT),
        0);
    this.server = server;

    // set the executor
    server.setExecutor(threadPoolExecutor);

    // start the server
    server.start();
    System.out.println("Server started on port " + SERVER_PORT);
  }

  public void loadAccount(){
    MongoDatabase RecipeListDB = mongoClient.getDatabase("Recipe_List");
    MongoCollection<Document> recipeCollection = RecipeListDB.getCollection("Recipe");
    // MongoCollection<Document> recipeCollection =
    // RecipeListDB.getCollection("Recipe");

    // create the context
    server.createContext("/", new RequestHandler(recipeCollection));
    // server.createContext("/name", new MyHandler(data));

  }

  public void createAccountInDB(String username, String password){
    MongoDatabase usernameDB = mongoClient.getDatabase(username);
    MongoCollection<Document> UserInfoCollection = usernameDB.getCollection("UserInfoCollection");

    Document UserCredentialsDoc = new Document("username", username)
    .append("password", password);

    UserInfoCollection.insertOne(UserCredentialsDoc);

    MongoCollection<Document> recipeCollection = usernameDB.getCollection("Recipe");
    server.createContext("/", new RequestHandler(recipeCollection));
  }

  public void deactivateServer() throws IOException {
      server.stop(0);
      threadPoolExecutor.shutdownNow();
  }
}
