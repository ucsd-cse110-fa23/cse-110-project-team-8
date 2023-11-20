package PantryPal;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class RecipeList {
    private int numRecipe;
    private ArrayList<Recipe> list;

    public RecipeList() {
        this.numRecipe = 0;
        list = new ArrayList<Recipe>();
    }

    public void delete(Recipe recipe) {
        list.remove(recipe);
        this.numRecipe--;
    }

    public int size() {
        return this.list.size();
    }

    public void add(Recipe recipe) {
        list.add(recipe);
        this.numRecipe++;
    }

    public Recipe get(int index) {
        return list.get(index);
    }

    // load the dataBase
    public void loadDB(RecipeListBody recipeList, Controller controller) throws Exception {
        String response = controller.handleGetAll();
        System.out.println("response: " + response);
        String[] recipes = response.split(":");
        for (String recipe : recipes) {
            String[] data = recipe.split(";");
            System.out.println("data: " + data[0]);
            Recipe newRecipe = new Recipe(data[0], data[1], data[2]);
            list.add(newRecipe);
            recipeList.load(newRecipe, controller);
        }
    }

    // reload the CSV and regenerate the recipe to recipelist
    public void loadCSV(RecipeListBody recipeList, Controller controller) throws Exception {
        try {
            // Create a CSV reader and specify the file to read
            CSVReader reader = new CSVReader(new FileReader("RecipeList.csv"));
            reader.readNext();
            // Read the CSV file and process each row
            while (reader.peek() != null) {
                String[] row = reader.readNext();
                Recipe recipe = new Recipe(row[0], row[1], row[2]);
                list.add(recipe);

                recipeList.load(recipe, controller);
            }

            // Close the CSV reader
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // reload the CSV and regenerate the recipe to recipelist FOR TESTING
    public void loadCSV(String filename) throws CsvValidationException {
        try {
            // Create a CSV reader and specify the file to read
            CSVReader reader = new CSVReader(new FileReader(filename));
            reader.readNext();
            // Read the CSV file and process each row
            while (reader.peek() != null) {
                String[] row = reader.readNext();
                Recipe recipe = new Recipe(row[0], row[1], row[2]);

                list.add(recipe);
            }

            // Close the CSV reader
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save to a CSV file whenever "close" is clicked
    public void toCSV(String filename) {
        try {
            // Create a CSV writer and specify the file to write to
            CSVWriter writer = new CSVWriter(new FileWriter(filename));

            // Write data to the CSV file
            String[] header = { "Title", "Ingredients", "Instructions" };
            writer.writeNext(header);
            for (int i = 0; i < this.list.size(); i++) {
                Recipe recipe = (Recipe) this.list.get(i);
                String[] data = { recipe.getTitle(), recipe.getIngredients(), recipe.getInstructions() };
                writer.writeNext(data);
            }
            // Close the CSV writer
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void incNum() {
        this.numRecipe++;
    }

    public void decNum() {
        if (this.getNum() == 0) {
            throw new IllegalArgumentException("No Recipe in the list.");
        }
        this.numRecipe--;
    }

    public int getNum() {
        return this.numRecipe;
    }
}
