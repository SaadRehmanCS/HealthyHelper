package ui;

import model.CalorieTarget;
import model.DietPlan;
import model.Food;
import model.User;

import java.util.Scanner;

public class DisplayInfo {

    private DietPlan dietPlan;
    private CalorieTarget calorieTarget;
    private User user;

    Scanner input = new Scanner(System.in);

    public DisplayInfo() {
        beginProgram();
        dietPlanDecider();
    }

    public void beginProgram() {
        user = new User();

        System.out.println("Welcome to HealthyHelper! the application "
                + " that can help you keep your fitness goals on track!\n"
                + "Lets begin by entering your height and weight below.");
        System.out.print("Height(cm): ");
        double height = input.nextDouble();
        System.out.print("Weight(kg): ");
        double weight = input.nextDouble();

        dietPlan = new DietPlan(height, weight);

    }

    public void dietPlanDecider() {
        System.out.println(" Your BMI comes out to: " + dietPlan.calculateBMI()
                + ".\n According to our estimates, you are classified as " + dietPlan.bmiAssessment()
                + ".\n Based on this, we recommend that you choose the \"" + dietPlan.dietPlanRecommendation()
                + "\" strategy to achieve the best results");

        System.out.println("What strategy would you like to pursue?:\n"
                + "1 ---> \"Bulk\"\n2 ---> \"Cut\"\n3 ---> \"Maintain\"");
        int tempInt = input.nextInt();
        String chosenStrategy = (tempInt == 1 ? "bulk" : ((tempInt == 2) ? "cut" : "maintain"));

        dietPlan.setDietPlanUserSelection(chosenStrategy);
        calorieTarget = new CalorieTarget(dietPlan);

    }

    public void calorieTargetDisplay() {
        System.out.println("Your remaining calorie target to hit today is "
                + calorieTarget.getCalorieTarget() + " kcal");
        System.out.println("Calories burned: " + calorieTarget.getCaloriesBurned());
    }

    public void loggingDisplay() {
        System.out.println("1 ---> Add a food item consumed");
        System.out.println("2 ---> Add an amount of water consumed");
        loggingEntry();
    }

    public void loggingEntry() {
        int loggingInt = input.nextInt();

        switch (loggingInt) {
            case 1:
                foodEntry();
                break;
            case 2:
                waterEntry();
                break;
        }
    }

    public void foodEntry() {
        user.foodRecommendation();
        System.out.println("Enter the name of the food:");
        String foodName = input.nextLine().trim().toLowerCase();
        input.nextLine();
        System.out.println("Enter the total calories contained in this item");
        int foodCalories = input.nextInt();
        System.out.println("1 ---> breakfast\n2 ---> lunch\n3 ---> dinner"
                + "\n4 ---> snack");
        int intMealType = input.nextInt();
        String mealType;

        switch (intMealType) {
            case 1: mealType = "breakfast";
            break;
            case 2: mealType = "lunch";
            break;
            case 3: mealType = "dinner";
            break;
            default: mealType = "snack";
        }

        Food food = new Food(foodName, foodCalories, mealType);
        user.addFood(food);
        calorieTarget.updateCalorieTarget(food);
    }

    public void waterEntry() {
        System.out.println("How many cups of water would you like to add?");
        int waterInput = input.nextInt();

        if (waterInput == 1) {
            user.drinkWater();
        } else {
            user.drinkWater(waterInput);
        }

    }


}


