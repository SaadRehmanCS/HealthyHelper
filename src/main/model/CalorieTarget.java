package model;

public class CalorieTarget {

    private int calorieTarget;
    private int caloriesBurned;

    public CalorieTarget(DietPlan plan) {
        caloriesBurned = 0;
        if (plan.getSelectedPlan().equals("bulk")) {
            calorieTarget = (int)plan.calculateBMI() * 125;
        } else if (plan.getSelectedPlan().equals("maintain")) {
            calorieTarget = (int)plan.calculateBMI() * 110;
        } else {
            calorieTarget = (int)plan.calculateBMI() * 95;
        }
    }

    public void updateCalorieTarget(Food food) {
        calorieTarget -= food.getTotalCalories();
        caloriesBurned += food.getTotalCalories();
    }

    public int getCalorieTarget() {
        return calorieTarget;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

}
