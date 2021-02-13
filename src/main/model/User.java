package model;

import java.util.ArrayList;
import java.util.List;

//when the program has enough information from the
//user, an object of this type is instantiated and creates
//lists that store any information that the user logs for the rest of the day,
//including food, water, and meal type of food
public class User {

    private List<Food> foodLog;
    private Water waterLog;
    private List<MealType> mealTypeLog;

    //MODIFIES: this
    //EFFECTS: constructs lists for food logging, and a water object
    public User() {
        foodLog = new ArrayList<>();
        waterLog = new Water();
        mealTypeLog = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds food to the food log list, and the
    //      meal type to the meal type list
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

    //MODIFIES: this
    //EFFECTS: increments water log by 1
    public void drinkWater() {
        waterLog.incrementWater();
    }

    //MODIFIES: this
    //EFFECTS: increments water log by the amount specified in the parameter
    public void drinkWater(int cups) {
        for (int i = 0; i < cups; i++) {
            waterLog.incrementWater();
        }
    }

    //EFFECTS: returns a string meal type from a
    //     parameter that contains the int form of the meal type
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
            print += (++i) + ") " + String.format("%-16s%-12d%-10s\n",
                    food.getFoodName(), food.getTotalCalories(), food.getMealType());
        }

        return printIntro + print + "\n";
    }


}
