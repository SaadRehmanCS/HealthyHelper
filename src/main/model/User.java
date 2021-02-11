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

    public List<Food> getFoodLog() {
        return foodLog;
    }

    public int getWaterLogSize() {
        return waterLog.getAmountConsumed();
    }

    public int getFoodSize() {
        return foodLog.size();
    }

    //public void foodRecommendation() {}

    public void drinkWater() {
        waterLog.incrementWater();
    }

    public void drinkWater(int cups) {
        for (int i = 0; i < cups; i++) {
            waterLog.incrementWater();
        }
    }


    public String getMealTypeFromNums(int intMealType) {

        switch (intMealType) {
            case 1:
                return "breakfast";
            case 2:
                return "lunch";
            case 3:
                return "dinner";
            default:
                return "snack";
        }
    }

    public String foodDisplay() {
        int i = 0;
        String printIntro = "";
        if (getFoodSize() > 0) {
            printIntro = "Food items consumed today: \n   Name            Calories    Meal\n";
        }
        String print = "";
        for (Food food : getFoodLog()) {
            print += (++i) + ") " + String.format("%-16s%-12d%-10s\n",
                    food.getFoodName(), food.getTotalCalories(), food.getMealType());
        }

        return printIntro + print + "\n";
    }


}
