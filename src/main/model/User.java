package model;

import model.json.Writable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//when the program has enough information from the
//user, an object of this type is instantiated and creates
//lists that store any information that the user logs for the rest of the day,
//including food, water, and meal type of food
public class User implements Writable {

    private List<Food> foodLog;
    private Water waterLog;
    private List<MealType> mealTypeLog;
    private Sleep sleep;

    //MODIFIES: this
    //EFFECTS: constructs lists for food logging, and a water object
    public User() {
        foodLog = new LinkedList<>();
        waterLog = new Water();
        mealTypeLog = new LinkedList<>();
        sleep = new Sleep();
    }

    //MODIFIES: this
    //EFFECTS: adds food to the food log list, and the
    //      meal type to the meal type list
    public void addFood(Food food) {
        foodLog.add(food);
        mealTypeLog.add(food.getMealType());
    }

    public List<Food> getFoodLog() {
        return foodLog;
    }

    public Water getWater() {
        return waterLog;
    }

    public int getWaterSize() {
        return waterLog.getAmountConsumed();
    }

    public int getFoodSize() {
        return foodLog.size();
    }

    //public void foodRecommendation() {}

    //MODIFIES: this
    //EFFECTS: increments water log by 1
    public void drinkWater() {
        waterLog.incrementWater();
    }

    //REQUIRES: parameter must be cups > 0
    //MODIFIES: this
    //EFFECTS: increments water log by the amount specified in the parameter
    public void drinkWater(int cups) {
        for (int i = 0; i < cups; i++) {
            waterLog.incrementWater();
        }
    }

    //EFFECTS: returns a string meal type from a
    //     parameter that contains the int form of the meal type
    public MealType getMealTypeFromNums(int intMealType) {

        switch (intMealType) {
            case 1:
                return MealType.BREAKFAST;
            case 2:
                return MealType.LUNCH;
            case 3:
                return MealType.DINNER;
            default:
                return MealType.SNACK;
        }
    }

    //EFFECTS: returns a string that will be displayed on the console,
    //      it is the list of food items consumed throughout the day
    public String foodDisplay() {
        int i = 0;
        String printIntro = "";
        if (getFoodSize() > 0) {
            printIntro = "Food items consumed today: \n   Name            Calories    Meal\n";
        }
        String print = "";
        for (Food food : getFoodLog()) {
            print += (++i) + ") " + String.format("%-16s%-12d%-14s%-16s\n",
                    food.getFoodName(), food.getTotalCalories(), food.getMealType(), food.getTimeOfConsumption());
        }

        return printIntro + print + "\n";
    }

    public Sleep getSleep() {
        return sleep;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("food", foodLogToJson());
        return json;

    }

    private JSONArray foodLogToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Food food: foodLog) {
            jsonArray.put(food.toJson());
        }

        return jsonArray;
    }

}
