package Server;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
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
    private MongoCollection<Document> recipeCollection;

    public RequestHandler(MongoCollection<Document> recipeCollection) {
        this.recipeCollection = recipeCollection;
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
        String response = readAllRecipe(recipeCollection);
        return response;
    }

    private String handlePost(HttpExchange httpExchange) throws IOException {
        String response = "Invalid POST request";
        InputStream inStream = httpExchange.getRequestBody();
        Scanner scanner = new Scanner(inStream);
        String postData = scanner.nextLine();
        List<String> recipe = Arrays.asList(postData.split(";"));
        String recipeTitle = recipe.get(0);
        String ingredients = recipe.get(1);
        String instructions = recipe.get(2);

        insertOneRecipe(recipeCollection, recipeTitle, ingredients, instructions);

        response = "Posted recipe {" + recipeTitle + "}";
        System.out.println(response);

        scanner.close();

        return response;
    }

    private String handlePut(HttpExchange httpExchange) throws IOException {
        InputStream inStream = httpExchange.getRequestBody();
        Scanner scanner = new Scanner(inStream);
        String putData = scanner.nextLine();
        List<String> recipe = Arrays.asList(putData.split(";"));
        String recipeTitle = recipe.get(0);
        String ingredients = recipe.get(1);
        String instructions = recipe.get(2);

        updateOneRecipe(recipeCollection, recipeTitle, ingredients, instructions);

        String response = new String("Updated recipe {" + recipeTitle + ", " + ingredients + ", " + instructions + "}");
        // System.out.println(response);
        scanner.close();

        return response;
    }

    private String handleDelete(HttpExchange httpExchange) throws IOException {
        String response = "Invalid DELETE request";
        URI uri = httpExchange.getRequestURI();
        String query = uri.getRawQuery();
        if (query != null) {
            String recipeTitle = query.substring(query.indexOf("=") + 1);
            deleteOneRecipe(recipeCollection, recipeTitle);
            response = "Deleted entry {" + recipeTitle + "}";
            System.out.println(response);
        }
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

    private static void deleteOneRecipe(MongoCollection<Document> recipeCollection, String recipeTitle) {
        Bson filter = eq("Title", recipeTitle);
        DeleteResult result = recipeCollection.deleteOne(filter);
        System.out.println(result);
        System.out.println(recipeCollection.countDocuments() + " after deletion");
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
        List<Document> studentList = recipeCollection.find().into(new ArrayList<>());
        System.out.println("length of list" + studentList.size());
        for (Document recipe : recipeCollection.find()) {
            recipe_details += recipe.get("Title") + ";" + recipe.get("Ingredients") + ";"
                    + recipe.get("Instructions")
                    + ":";
            cnt++;
            if (cnt == 16) {
                break;
            }
        }

        System.out.println(recipe_details);
        return recipe_details;
    }
}
