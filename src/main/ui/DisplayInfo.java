package ui;

import model.CalorieTarget;
import model.DietPlan;
import model.Food;
import model.User;

import java.util.Scanner;

public class DisplayInfo {

    protected static int RUN_PROGRAM = 1;
    private static int sleepEntryCounter = 0;

    private DietPlan dietPlan;
    private CalorieTarget calorieTarget;
    private User user;

    Scanner input = new Scanner(System.in);

    public DisplayInfo() {
        user = new User();
        beginProgram();
        dietPlanDecider();
    }

    public void beginProgram() {

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

    public void mainMenuCalorieDisplay() {
        if (calorieTarget.getCalorieTarget() > 0) {
            System.out.println("Your remaining calorie target to hit today is "
                    + calorieTarget.getCalorieTarget() + " kcal");
        } else {
            System.out.println("You have hit your calorie target for today!\nAdditional "
                    + "calories consumed today: "
                    + (calorieTarget.getCaloriesConsumed() - calorieTarget.getOriginalCalorieTarget()));

        }
        System.out.println("Calories consumed: " + calorieTarget.getCaloriesConsumed());
    }

    public void mainMenuWaterDisplay() {

        if (user.getWaterSize() < user.getWater().getDailyRequirement()) {
            int remainingRequirement = user.getWater().getDailyRequirement() - user.getWaterSize();
            System.out.println("You have consumed " + user.getWaterSize() + " cups of water"
                    + " today. You are " + remainingRequirement + " cups away from consuming a healthy amount"
                    + " of water today.");
        } else {
            System.out.println("Hooray! you have consumed a healthy amount of water for today. You may"
                    + " continue to drink more as you like!");
        }
        System.out.println("\n");
    }

    public void loggingDisplay() {
        System.out.println("1 ---> Add a food item consumed");
        System.out.println("2 ---> Add an amount of water consumed");
        System.out.println("3 ---> Track your sleep habits");
        System.out.println("\n0 ---> Quit the program! :(");
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
            case 3:
                sleepEntryCounter++;
                sleepEntry();
                break;
            case 0:
                RUN_PROGRAM = 0;
        }
    }

    public void foodEntry() {
        //user.foodRecommendation();
        System.out.println(user.foodDisplay());
        System.out.println("Enter the name of the food:");
        input.nextLine();
        String foodName = input.nextLine().trim().toLowerCase();
        System.out.println("Enter the total calories contained in this item");
        int foodCalories = input.nextInt();
        System.out.println("1 ---> breakfast\n2 ---> lunch\n3 ---> dinner"
                + "\n4 ---> snack");
        int intMealType = input.nextInt();

        Food food = new Food(foodName, foodCalories, user.getMealTypeFromNums(intMealType));
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

    public void sleepEntry() {
        if (sleepEntryCounter == 1) {
            System.out.print("Enter the number of hours you were able to sleep last night: ");
            double sleepInput = input.nextDouble();
            user.getSleep().addSleepTime(sleepInput);
        }

        System.out.println(user.getSleep().sleepAssessment() + "\n");
    }


}


