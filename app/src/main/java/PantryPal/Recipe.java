package PantryPal;

public class Recipe {
    private String titleString;
    private String ingredient;
    private String instructions;

    public Recipe(String title, String ingredient, String instructions){
        this.titleString = title;
        this.ingredient = ingredient;
        this.instructions = instructions;
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

    public String getTitle() {
        return this.titleString;
    }

    public String getIngredients() {
        return this.ingredient;
    }

    public String getInstructions() {
        return this.instructions;
    }
}
