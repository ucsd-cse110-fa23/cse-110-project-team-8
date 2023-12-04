package PantryPal;  

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

public class Recipe {
    private String objectID;
    private String titleString;
    private String ingredient;
    private String instructions;
    private String creationTime;
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public Recipe(String title, String ingredient, String instructions) {
        this.titleString = title;
        this.ingredient = ingredient;
        this.instructions = instructions;
        creationTime = " ";

    }

    public void setObjectID(String objectID){
        this.objectID = objectID;
    }

    public void setTitle(String title) {
        this.titleString = title;
    }

    public void setIngredients(String ingredient) {
        this.ingredient = ingredient;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setCreationTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);  
        LocalDateTime now = LocalDateTime.now();
        System.out.println("creation time:" + dtf.format(now));
        this.creationTime = dtf.format(now);
    }

    public String getObjectID(){
        return this.objectID;
    }

    public String getTitle() {
        return this.titleString;
    }

    public String getIngredients() {
        return this.ingredient;
    }

    public String getInstructions() {
        return this.instructions;
    }

    public String getCreationTime() {
        return this.creationTime;
    }

}
