package model;

//this object type stores a food item with three attributes,
//into a list for logging purposes. The list can be created in any other class
public class Food {

    private String name;
    private int totalCalories;
    private String mealType;

    //MODIFIES: this
    //EFFECTS: sets this to the values given in the parameter
    public Food(String name, int totalCalories, String mealType) {
        this.name = name;
        this.totalCalories = totalCalories;
        this.mealType = mealType;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public String getMealType() {
        return mealType;
    }

    public String getFoodName() {
        return name;
    }

}
