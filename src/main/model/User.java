package model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private List<Food> foodLog;
    private double calorieTarget;
    private String selectedDietPlan;
    private List<Water> waterLog;
    private List<MealType> mealTypeLog;


    public User() {
        foodLog = new ArrayList<>();
        waterLog = new ArrayList<>();
        calorieTarget = getCalorieTarget(selectedDietPlan);
        mealTypeLog = new ArrayList<>();
    }

    public void addFood(String name, double calories, String mealType) {}

    public double getCalorieTarget(String dietPlan) {
        return 0;
    }

    public void updateCalorieTarget(){}

    public void foodRecommendation(String dietPlan){}

    public void drinkWater(){}

    public void drinkWater(int cups){}


}
