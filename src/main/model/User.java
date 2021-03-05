package model;

import model.json.Writable;
import org.json.JSONArray;
import org.json.JSONObject;

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
    private CalorieTarget calorieTarget;

    //MODIFIES: this
    //EFFECTS: constructs lists for food logging, and a water object
    public User() {
        foodLog = new LinkedList<>();
        waterLog = new Water();
        mealTypeLog = new LinkedList<>();
        sleep = new Sleep();
        calorieTarget = new CalorieTarget();
    }

    public void setOriginalTarget(DietPlan plan) {
        calorieTarget.setOriginalTarget(plan);
    }

    public void setOriginalTarget(int target) {
        calorieTarget.setOriginalTarget(target);
    }

    public void setCalorieTarget(int remaining) {
        calorieTarget.setCalorieTarget(remaining);
    }

    public void setCaloriesConsumed(int consumed) {
        calorieTarget.setCaloriesConsumed(consumed);
    }

    public void updateCalorieTarget(Food food) {
        calorieTarget.updateCalorieTarget(food);
    }

    public int getCalorieTarget() {
        return calorieTarget.getCalorieTarget();
    }

    public int getCaloriesConsumed() {
        return calorieTarget.getCaloriesConsumed();
    }

    public int getOriginalCalorieTarget() {
        return calorieTarget.getOriginalCalorieTarget();
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
        return waterLog.getCupsConsumed();
    }

    public int getFoodSize() {
        return foodLog.size();
    }

    //public void foodRecommendation() {}

    public void addSleep(double hours) {
        sleep.addSleepTime(hours);
    }

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

    public double getSleepTime() {
        return sleep.getSleepTime();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("food", foodLogToJson());
        json.put("water consumed", waterLog.getCupsConsumed());
        json.put("sleep time", getSleepTime());
        json.put("calorie target", calorieTarget.toJson());
        return json;

    }

    public JSONArray foodLogToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Food food : foodLog) {
            jsonArray.put(food.toJson());
        }

        return jsonArray;
    }

//scaffolding
    //private JSONObject userToJson() {
    //         JSONObject json = new JSONObject();
    //      for (User user: Users) {
    //        json.put("food", foodLogToJson());
    //        json.put("water consumed", waterLog.getAmountConsumed());
    //        json.put("sleep time", getSleepTime());
    //      }
    //  return json
    // }

    //scaffolding
    //@Override
    //    public JSONObject toJson() {
    //        JSONObject json = new JSONObject();
    //        json.put("user", user.userToJson());
    //        return json;
    //
    //    }

}
