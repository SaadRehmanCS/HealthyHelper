package model;

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
    public CalorieTarget(DietPlan plan) {
        caloriesConsumed = 0;
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

}
