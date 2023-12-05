package PantryPal;

import static com.mongodb.client.model.Filters.in;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class RecipeList {
    private int numRecipe;
    private ArrayList<Recipe> list;
    ArrayList<Recipe> breakfastList;
    ArrayList<Recipe> lunchList;
    ArrayList<Recipe> dinnerList; 

    private int currentIndex;

    public RecipeList() {
        this.numRecipe = 0;
        list = new ArrayList<Recipe>();
        breakfastList = new ArrayList<Recipe>();
        lunchList = new ArrayList<Recipe>();
        dinnerList = new ArrayList<Recipe>();
    }

    public void delete(Recipe recipe) {
        list.remove(recipe);
        this.numRecipe--;
    }

    public int size() {
        return this.list.size();
    }

    public void add(Recipe recipe) {
        // Insert the recipe at the beginning of the list
        list.add(0, recipe);
        this.numRecipe++;
    }

    public Recipe get(int index) {
        return list.get(index);
    }

    // // load the dataBase
    // public void loadDB(RecipeListBody recipeList, Controller controller) throws Exception {
    //     String response = controller.handleGetAll();
    //     System.out.println("response: " + response);
    //     System.out.println("HELLO from LOADDB");
    //     if (response!=null){
            
    //     String[] recipes = response.split(":");
    //     for (String recipe : recipes) {
    //         String[] data = recipe.split(";");
    //         System.out.println("data: " + data[0]);
    //         Recipe newRecipe = new Recipe(data[0], data[1], data[2]);
    //         list.add(newRecipe);
    //         recipeList.load(newRecipe, controller);
    //     }
    // }
    // }

    // load the dataBase
    public void loadDB(RecipeListBody recipeList, Controller controller) throws Exception {
        String response = controller.handleGetAll();
        System.out.println("response: " + response);
        System.out.println("HELLO from LOADDB");
        if (response != null) {
            String[] recipes = response.split(":");
        
            // Start adding recipes from the end of the array to the beginning
            for (int i = recipes.length - 1; i >= 0; i--) {
                String[] data = recipes[i].split(";");
                System.out.println("data: " + data[0]);
                Recipe newRecipe = new Recipe(data[0], data[1], data[2] ,null);
                list.add(newRecipe);
                recipeList.load(newRecipe, controller);
            }
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
                Recipe recipe = new Recipe(row[0], row[1], row[2], null);
                list.add(recipe);

                recipeList.load(recipe, controller);
            }

            // Close the CSV reader
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // // reload the CSV and regenerate the recipe to recipelist
    // public void loadCSV(RecipeListBody recipeList, Controller controller) throws Exception {
    //     try {
    //         // Create a CSV reader and specify the file to read
    //         CSVReader reader = new CSVReader(new FileReader("RecipeList.csv"));
    //         reader.readNext(); // Skip the header row

    //         // Read the CSV file and process each row in reverse order
    //         int index = this.size(); // Start with the last index in the list
    //         while (reader.peek() != null) {
    //             String[] row = reader.readNext();
    //             Recipe recipe = new Recipe(row[0], row[1], row[2]);
    //             list.add(index, recipe);

    //             recipeList.load(recipe, controller);
    //         }

    //         // Close the CSV reader
    //         reader.close();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    // reload the CSV and regenerate the recipe to recipelist FOR TESTING
    public void loadCSV(String filename) throws CsvValidationException {
        try {
            // Create a CSV reader and specify the file to read
            CSVReader reader = new CSVReader(new FileReader(filename));
            reader.readNext();
            // Read the CSV file and process each row
            while (reader.peek() != null) {
                String[] row = reader.readNext();
                Recipe recipe = new Recipe(row[0], row[1], row[2], null);

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
            String[] header = { "Title", "Ingredients", "Instructions", "creationTime", "mealType" };
            writer.writeNext(header);
            for (int i = 0; i < this.list.size(); i++) {
                Recipe recipe = (Recipe) this.list.get(i);
                String[] data = { recipe.getTitle(), recipe.getIngredients(), recipe.getInstructions(), recipe.getCreationTime(), recipe.getMealType() };
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

    private static class AtoZComparator implements Comparator<Recipe> {
        @Override
        public int compare(Recipe o1, Recipe o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    }

    private static class ZtoAComparator implements Comparator<Recipe> {
        @Override
        public int compare(Recipe o1, Recipe o2) {
            return o2.getTitle().compareTo(o1.getTitle());
        }
    }

    private static class NewtoOldComparator implements Comparator<Recipe> {
        @Override
        public int compare(Recipe o1, Recipe o2) {
            return o2.getCreationTime().compareTo(o1.getCreationTime());
        }
    }

    private static class OldtoNewComparator implements Comparator<Recipe> {
        @Override
        public int compare(Recipe o1, Recipe o2) {
            return o1.getCreationTime().compareTo(o2.getCreationTime());
        }
    }

    public void sortAtoZ() {
        Collections.sort(this.list, new AtoZComparator());
    }

    public void sortZtoA() {
        Collections.sort(this.list, new ZtoAComparator());
    }

    public void sortNewtoOld() {
        Collections.sort(this.list, new NewtoOldComparator());
    }

    public void sortOldtoNew() {
        Collections.sort(this.list, new OldtoNewComparator());
    }

    public void filterMealType(String mealType) {
        final   ArrayList<Recipe> untouchedList = this.list;
        

        ArrayList<Recipe> newList = new ArrayList<>();
        // if (mealType.equals("All")){
        //     this.list =  untouchedList;
        // }
        for (Recipe recipe: this.list){
            if(recipe.getMealType().toLowerCase().contains("breakfast")){
                this.breakfastList.add(recipe);
            }
            if(recipe.getMealType().toLowerCase().contains("lunch")){
                this.lunchList.add(recipe);
            }
            if(recipe.getMealType().toLowerCase().contains("dinner")){
                this.dinnerList.add(recipe);
            }
        }
        if(mealType.equals("all")){
            for (Recipe breakfast: breakfastList){
                newList.add(breakfast);
            }
            for (Recipe lunch: lunchList){
                newList.add(lunch);
            }
            for (Recipe dinner: dinnerList){
                newList.add(dinner);
            }
        }
        if (mealType.equals("breakfast")){
            newList = breakfastList;
        }
        if (mealType.equals("lunch")){
            newList = lunchList;
        }
        if (mealType.equals("dinner")){
            newList = dinnerList;
        }

        this.list = newList;
    }

    public ArrayList<Recipe> getList(){
        return this.list;
    }

    public ArrayList<Recipe> getBreakfastList(){
        return this.breakfastList;
    }

    public ArrayList<Recipe> getLunchList(){
        return this.lunchList;
    }
    public ArrayList<Recipe> getDinnerList(){
        return this.dinnerList;
    }

}
