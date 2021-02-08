package model;

public class CalorieTarget {

    private int calorieTarget;

    public CalorieTarget(DietPlan plan) {
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
    }

    public int getCalorieTarget() {
        return calorieTarget;
    }

}
