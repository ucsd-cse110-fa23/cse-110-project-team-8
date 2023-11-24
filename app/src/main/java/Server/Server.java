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
  private MongoDatabase usernameDB;
  // private boolean serverCreated;

  public void activateServer() throws IOException {
    // create a thread pool to handle requests
    // this.serverCreated = false;
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

  public boolean loadAccount(String username, String password){
    usernameDB = mongoClient.getDatabase(username);

    MongoCollection<Document> UserInfoCollection = usernameDB.getCollection("UserInfoCollection");

    Document theUser = UserInfoCollection.find(new Document("username", username)).first();
    System.out.println("theUser: " + theUser);
    if(password.equals(theUser.get("password"))){ //password is the entered password and theUser.get("password") is the password in the database
      System.out.println("theUser password: " + theUser.get("password"));

      MongoCollection<Document> recipeCollection = usernameDB.getCollection("Recipe");

      // if (serverCreated == false) {
        // create the context
        server.createContext("/", new RequestHandler(recipeCollection));
      //   serverCreated = true;
      //   // server.createContext("/name", new MyHandler(data));
      // } else {
      //   // create the context
      //   server.createContext("/", new RequestHandler(recipeCollection));
      //   serverCreated = true;
      //   // server.createContext("/name", new MyHandler(data));
      // }
      
      return true;  
    }
    else {
      System.out.println("Wrong password, try again");
      return false;
    }
  }

  public void createAccountInDB(String username, String password){
    usernameDB = mongoClient.getDatabase(username);
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

  public MongoClient getMongo(){
    return this.mongoClient;
  }

    public MongoDatabase getMongoDB(){
    return this.usernameDB;
  }
}
