package Server;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertManyOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class RequestHandler implements HttpHandler {
    private MongoClient mongoClient;
    private MongoDatabase userDB;
    private MongoCollection<Document> recipeCollection;
    private static final String CREATE_ACCOUNT = "createAccount";
    private static final String LOGIN = "Login";

    public RequestHandler(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "Request Received";
        String method = httpExchange.getRequestMethod();
        System.out.println("method returned from httpExchange: " + method);
        try {
            if (method.equals("GET")) {
                response = handleGet(httpExchange);
            } else if (method.equals("POST")) {
                response = handlePost(httpExchange);
            } else if (method.equals("PUT")) {
                response = handlePut(httpExchange);
            } else if (method.equals("DELETE")) {
                response = handleDelete(httpExchange);
            } else {
                throw new Exception("Not Valid Request Method");
            }
        } catch (Exception e) {
            System.out.println("An erroneous request");
            response = e.toString();
            e.printStackTrace();
        }
        // Sending back response to the client
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream outStream = httpExchange.getResponseBody();
        System.out.println("response in handler: " + response);
        outStream.write(response.getBytes());
        outStream.close();
    }

    private String handleGet(HttpExchange httpExchange) throws IOException {
        String response = readAllRecipe((userDB.getCollection("Recipe")));
        return response;
    }

    private String handlePost(HttpExchange httpExchange) throws IOException {
        String response = "Invalid POST request";
        InputStream inStream = httpExchange.getRequestBody();
        Scanner scanner = new Scanner(inStream);
        String postData = scanner.nextLine();
        List<String> recipe = Arrays.asList(postData.split(";"));
        String username = recipe.get(0);
        String password = recipe.get(1);
        String recipeTitle = recipe.get(2);
        String ingredients = recipe.get(3);
        String instructions = recipe.get(4);
        String action = recipe.get(5);

        System.out.println(recipeTitle);
        System.out.println(ingredients);
        System.out.println(instructions);

        if (recipeTitle.equals(" ") && ingredients.equals(" ") && instructions.equals(" ")) { // the POST request is a
                                                                                              // login/create
            response = this.loadAccount(username, password, action); // acoount reques
            System.out.println("if statement");
        } else { // the POST request is a create recipe request
            insertOneRecipe(recipeCollection, recipeTitle, ingredients, instructions);
            response = "Posted recipe {" + recipeTitle + "}";
        }
        System.out.println(response);
        scanner.close();
        return response;
    }

    private String handlePut(HttpExchange httpExchange) throws IOException {
        InputStream inStream = httpExchange.getRequestBody();
        Scanner scanner = new Scanner(inStream);
        String putData = scanner.nextLine();
        List<String> recipe = Arrays.asList(putData.split(";"));
        String recipeTitle = recipe.get(2);
        String ingredients = recipe.get(3);
        String instructions = recipe.get(4);

        updateOneRecipe(recipeCollection, recipeTitle, ingredients, instructions);

        String response = new String("Updated recipe {" + recipeTitle + ", " + ingredients + ", " + instructions + "}");
        // System.out.println(response);
        scanner.close();

        return response;
    }

    private String handleDelete(HttpExchange httpExchange) throws IOException {
        String response = "";
        InputStream inStream = httpExchange.getRequestBody();
        Scanner scanner = new Scanner(inStream);
        String recipeTitle = scanner.nextLine();
        response = deleteOneRecipe(recipeCollection, recipeTitle);
        scanner.close();

        return response;
    }

    private static void insertOneRecipe(MongoCollection<Document> recipeCollection, String recipeTitle,
            String ingredients, String instructions) {
        recipeCollection.insertOne(generateNewRecipe(recipeTitle, ingredients, instructions));
        System.out.println(recipeTitle + " inserted.");
    }

    private static Document generateNewRecipe(String recipeTitle, String ingredients, String instructions) {

        return new Document("Title", recipeTitle).append("Ingredients", ingredients)
                .append("Instructions", instructions);
    }

    private static void updateOneRecipe(MongoCollection<Document> recipeCollection, String recipeTitle,
            String ingredients, String instructions) {
        JsonWriterSettings prettyPrint = JsonWriterSettings.builder().indent(true).build();
        Bson filter = eq("Title", recipeTitle);
        Bson updateOperation1 = set("Ingredients", ingredients);
        Bson updateOperation2 = set("Instructions", instructions);
        UpdateResult updateResult1 = recipeCollection.updateOne(filter, updateOperation1);
        UpdateResult updateResult2 = recipeCollection.updateOne(filter, updateOperation2);
        System.out.println(
                "=> Updating the doc with {Ingredients: " + ingredients + ", Instructions: " + instructions + "}.");
        System.out.println(recipeCollection.find(filter).first().toJson(prettyPrint));
        System.out.println(updateResult1);
        System.out.println(updateResult2);
    }

    private static String deleteOneRecipe(MongoCollection<Document> recipeCollection, String recipeTitle) {
        Bson filter = eq("Title", recipeTitle);
        DeleteResult result = recipeCollection.deleteOne(filter);
        System.out.println(result);
        System.out.println(recipeCollection.countDocuments() + " after deletion");
        return result.toString();
    }

    private static String readOneRecipe(MongoCollection<Document> recipeCollection, String recipeTitle) {
        Document recipe = recipeCollection.find(eq("Title", recipeTitle)).first();
        String recipe_details = recipe.get("Title") + ";" + recipe.get("Instructions") + ";"
                + recipe.get("Ingredients");

        System.out.println(recipe_details);
        return recipe_details;
    }

    private static String readAllRecipe(MongoCollection<Document> recipeCollection) {
        String recipe_details = "";
        int cnt = 0;
        // TODO why is line 164-165 here?
        List<Document> studentList = recipeCollection.find().into(new ArrayList<>());
        // System.out.println("length of list" + studentList.size());

        for (Document recipe : recipeCollection.find()) {
            recipe_details += recipe.get("Title") + ";" + recipe.get("Ingredients") + ";"
                    + recipe.get("Instructions")
                    + ":";
            cnt++;
            if (cnt == 16) {
                break;
            }
        }

        System.out.println("Recipe Details from readAllRecipe(): \n" + recipe_details);
        return recipe_details;
    }

    // handles username and password
    private String loadAccount(String username, String password, String action) {
        String response = "";
        // account exists
        if (action.equals(LOGIN)) {
            if (databaseFound(mongoClient, username) == true) {
                this.userDB = mongoClient.getDatabase(username);
                MongoCollection<Document> UserInfoCollection = userDB.getCollection("UserInfoCollection");
                Document theUser = UserInfoCollection.find(new Document("username", username)).first();
                if (password.equals(theUser.get("password"))) {
                    response = "Login Successfully";
                    this.recipeCollection = userDB.getCollection("Recipe");
                } else {
                    response = "Incorrect Password";
                }
            } else {
                response = "Username not found";
            }
        }

        // account does not exist: create a new DB
        else if (action.equals(CREATE_ACCOUNT)) {
            if (databaseFound(mongoClient, username) == true) {
                System.out.println(username);
                System.out.println(mongoClient.getDatabase(username));
                response = "Username already exists";
            } else {
                MongoDatabase database = mongoClient.getDatabase(username);
                this.userDB = database;
                MongoCollection<Document> UserInfoCollection = userDB.getCollection("UserInfoCollection");
                Document UserCredentialsDoc = new Document("username", username)
                        .append("password", password);
                UserInfoCollection.insertOne(UserCredentialsDoc);
                userDB.createCollection("Recipe");
                this.recipeCollection = userDB.getCollection("Recipe");
                response = "Account Creation Successful";
            }

        }
        return response;
    }

    public static Boolean databaseFound(MongoClient mongoClient, String databaseName) {
        MongoCursor<String> dbsCursor = mongoClient.listDatabaseNames().iterator();
        while (dbsCursor.hasNext()) {
            if (dbsCursor.next().equals(databaseName)) {
                return true;
            }
        }
        return false;
    }

}
