package model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private List<Food> foodLog;
    private Water waterLog;
    private List<MealType> mealTypeLog;

    public User() {
        foodLog = new ArrayList<>();
        waterLog = new Water();
        mealTypeLog = new ArrayList<>();
    }

    public void addFood(Food food) {
        foodLog.add(food);
        mealTypeLog.add(new MealType(food.getMealType()));
    }

    public void foodRecommendation(){}

    public void drinkWater() {
        waterLog.incrementWater();
    }

    public void drinkWater(int cups) {
        for (int i = 0; i < cups; i++) {
            waterLog.incrementWater();
        }
    }




}
