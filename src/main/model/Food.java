package model;

import model.json.Writable;
import org.json.JSONObject;

//this object type stores a food item with three attributes,
//into a list for logging purposes. The list can be created in any other class
public class Food implements Writable {

    private String foodName;
    private int totalCalories;
    private MealType mealType;
    private String timeOfConsumption;

    //MODIFIES: this
    //EFFECTS: sets this to the values given in the parameter
    public Food(String foodName, int totalCalories, MealType mealType, String timeOfConsumption) {
        this.foodName = foodName;
        this.totalCalories = totalCalories;
        this.mealType = mealType;
        this.timeOfConsumption = timeOfConsumption;

    }

    public String getTimeOfConsumption() {
        return timeOfConsumption;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public MealType getMealType() {
        return mealType;
    }

    public String getFoodName() {
        return foodName;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", foodName);
        json.put("total calories", totalCalories);
        json.put("meal type", mealType);
        json.put("time of consumption", timeOfConsumption);
        return json;
    }
}
