package model;

import model.json.Writable;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

//this object type stores a food item with three attributes,
//into a list for logging purposes. The list can be created in any other class
public class Food implements Writable {

    private String name;
    private int totalCalories;
    private MealType mealType;
    private String timeOfConsumption;

    //MODIFIES: this
    //EFFECTS: sets this to the values given in the parameter
    public Food(String name, int totalCalories, MealType mealType) {
        this.name = name;
        this.totalCalories = totalCalories;
        this.mealType = mealType;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a  EEE, MMM d");
        timeOfConsumption = formatter.format(date);

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
        return name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("total calories", totalCalories);
        json.put("meal type", mealType);
        json.put("time of consumption", timeOfConsumption);
        return json;
    }
}
