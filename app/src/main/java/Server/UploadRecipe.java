package Server;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Random;

import static java.util.Arrays.asList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UploadRecipe {

    public static void main(String[] args) {
        String filePath = "recipes.csv";
        String uri = "mongodb+srv://henryfzh:Henry%40heng2002@myfirstcluster.pwk6osr.mongodb.net/?retryWrites=true&w=majority";
        BufferedReader reader = null;
        try {
            // Create a BufferedReader to read the file
            reader = new BufferedReader(new FileReader(filePath));
            reader.readLine();
            // Read the CSV file line by line
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into columns using a delimiter (e.g., ",")
                String[] columns = line.split(";");

                try (MongoClient mongoClient = MongoClients.create(uri)) {

                    MongoDatabase sampleTrainingDB = mongoClient.getDatabase("RecipeList");
                    MongoCollection<Document> gradesCollection = sampleTrainingDB.getCollection("Recipe");

                    Document recipe = new Document("_id", new ObjectId());
                    recipe.append("Title", columns[0])
                            .append("Description", columns[1])
                            .append("Hours", columns[2]);

                    gradesCollection.insertOne(recipe);
                }
            }

        } catch (IOException e) {
            // Handle the exception
            e.printStackTrace();
        } finally {
            // Close the BufferedReader
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
