package model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private List<Food> foodLog;
    private double calorieTarget;
    private String selectedDietPlan;
    private Water waterLog;
    private List<MealType> mealTypeLog;
    private CalorieTarget target;


    public User() {
        foodLog = new ArrayList<>();
        waterLog = new Water();
        calorieTarget = target.getCalorieTarget();
        mealTypeLog = new ArrayList<>();
    }

    //adds mealType list inside the method
    public void addFood(String name, double calories, String mealType) {}

    public void foodRecommendation(String dietPlan){}

    public void drinkWater() {
        waterLog.incrementWater();
    }

    public void drinkWater(int cups) {
        for (int i = 0; i < cups; i++) {
            waterLog.incrementWater();
        }
    }




}
