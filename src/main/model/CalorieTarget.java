package model;

import model.exceptions.ImpossibleBodyDimensionsException;
import org.json.JSONObject;

//instantiating an object of this class type
//will set a calorie target for the user based on their diet plan,
//that must then be tracked constantly to update
//anytime the user consumes food
public class CalorieTarget {

    private int calorieTarget;
    private int caloriesConsumed;
    private static int ORIGINAL_CALORIE_TARGET;

    //MODIFIES: this
    //EFFECTS: based on the selected diet plan, it
    //     sets a calculated target to achieve, and
    //     this target is assigned to the original target.
    public CalorieTarget() {
        caloriesConsumed = 0;
        calorieTarget = 0;
        ORIGINAL_CALORIE_TARGET = 0;
    }

    public void setCaloriesConsumed(int consumed) {
        caloriesConsumed = consumed;
    }

    public void setCalorieTarget(int target) {
        calorieTarget = target;
    }

    public void setOriginalTarget(int target) {
        ORIGINAL_CALORIE_TARGET = target;
    }

    //REQUIRES: should only be called once, when deciding meal plan
    public void setOriginalTarget(DietPlan plan) throws ImpossibleBodyDimensionsException {
        if (plan.getSelectedPlan().equals("bulk")) {
            calorieTarget = (int) plan.calculateBMI() * 125;
        } else if (plan.getSelectedPlan().equals("maintain")) {
            calorieTarget = (int) plan.calculateBMI() * 110;
        } else {
            calorieTarget = (int) plan.calculateBMI() * 95;
        }

        ORIGINAL_CALORIE_TARGET = calorieTarget;
    }

    //MODIFIES: this
    //EFFECTS: when user consumes a food item,
    //     the calorie target is decremented while the
    //     calories consumed is incremented
    //     accordingly
    public void updateCalorieTarget(Food food) {
        calorieTarget -= food.getTotalCalories();
        caloriesConsumed += food.getTotalCalories();
    }

    public int getCalorieTarget() {
        return calorieTarget;
    }

    public int getCaloriesConsumed() {
        return caloriesConsumed;
    }

    public int getOriginalCalorieTarget() {
        return ORIGINAL_CALORIE_TARGET;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("remaining target", calorieTarget);
        json.put("calories consumed", caloriesConsumed);
        json.put("original target", ORIGINAL_CALORIE_TARGET);
        return json;
    }

}
