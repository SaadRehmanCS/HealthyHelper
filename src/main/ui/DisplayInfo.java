package ui;

import model.DietPlan;
import model.Food;
import model.User;
import model.exceptions.ImpossibleBodyDimensionsException;
import model.exceptions.InvalidDietPlanException;
import model.json.JsonReader;
import model.json.JsonWriter;
import org.json.JSONException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DisplayInfo {

    private static final String JSON_STORE = "./data/user.json";
    protected static int RUN_PROGRAM = 1;
    private static int sleepEntryCounter = 0;

    private DietPlan dietPlan;
    private User user;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Scanner input;

    public DisplayInfo() {
        user = new User();
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        boolean proceed = false;
        while (proceed == false) {
            try {
                userSelection();
                proceed = true;
            } catch (ArithmeticException e) {
                System.out.println(e);
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(e);
                input.nextLine();
            }
        }
    }

    public void userSelection() {
        System.out.println("Are you a new or returning user?");
        System.out.println("Returning --> 1\nNew --> 0");


        int in = input.nextInt();

        if (in != 0 && in != 1) {
            throw new ArithmeticException("Enter either 1 or 0");
        }

        if (in == 1) {
            loadUser();
        } else {
            clearUserData();
            beginProgram();
            dietPlanDecider();
        }

    }

    public void beginProgram() {

        System.out.println("Welcome to HealthyHelper! the application "
                + " that can help you keep your fitness goals on track!\n"
                + "Lets begin by entering your height and weight below.");

        boolean proceed = false;
        while (proceed == false) {
            try {
                System.out.print("Height(cm): ");
                double height = input.nextDouble();
                System.out.print("Weight(kg): ");
                double weight = input.nextDouble();
                dietPlan = new DietPlan(height, weight);
                proceed = true;

            } catch (ImpossibleBodyDimensionsException e) {
                System.out.println(e);
                input.nextLine();
            }
        }

    }


    public void dietPlanDecider() {
        try {
            System.out.println(" Your BMI comes out to: " + dietPlan.calculateBMI()
                    + ".\n According to our estimates, you are classified as " + dietPlan.bmiAssessment()
                    + ".\n Based on this, we recommend that you choose the \"" + dietPlan.dietPlanRecommendation()
                    + "\" strategy to achieve the best results");
        } catch (ImpossibleBodyDimensionsException e) {
            input.nextLine();
            beginProgram();
        }
        System.out.println("What strategy would you like to pursue?:\n"
                + "1 ---> \"Bulk\"\n2 ---> \"Cut\"\n3 ---> \"Maintain\"");
        boolean proceed = false;
        while (proceed == false) {
            try {
                int chosenStrategy = input.nextInt();
                dietPlan.setDietPlanUserSelection(chosenStrategy);
                user.getCalorieTarget().setOriginalTarget(dietPlan);
                proceed = true;
            } catch (InvalidDietPlanException | ImpossibleBodyDimensionsException | InputMismatchException e) {
                System.out.println(e);
                input.nextLine();
            }
        }
    }

    public void mainMenuCalorieDisplay() {
        if (user.getCalorieTarget().getCaloriesRemaining() > 0) {
            System.out.println("Your remaining calorie target to hit today is "
                    + user.getCalorieTarget().getCaloriesRemaining() + " kcal");
        } else {
            System.out.println("You have hit your calorie target for today!\nAdditional "
                    + "calories consumed today: "
                    + (user.getCalorieTarget().getCaloriesConsumed()
                     - user.getCalorieTarget().getOriginalCalorieTarget()));

        }
        System.out.println("Calories consumed: " + user.getCalorieTarget().getCaloriesConsumed());
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

        switch (singleInputExceptionHandler()) {
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
                break;
            default:
                loggingDisplay();
        }
    }

    public int singleInputExceptionHandler() {
        int in = 0;
        boolean proceed = false;
        while (!proceed) {
            try {
                in = input.nextInt();
                proceed = true;
            } catch (InputMismatchException e) {
                System.out.println(e);
                input.nextLine();
            }
        }

        return in;
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
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a  EEE, MMM d");
        String timeOfConsumption = formatter.format(date);
        Food food = new Food(foodName, foodCalories, user.getMealTypeFromNums(intMealType), timeOfConsumption);
        user.addFood(food);
        user.getCalorieTarget().updateCalorieTarget(food);
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

    protected void saveUser() {
        try {
            jsonWriter.open();
            jsonWriter.write(user);
            jsonWriter.close();
            System.out.println("File was saved to " + JSON_STORE);

        } catch (FileNotFoundException e) {
            System.out.println("Unable to save file");
        }
    }

    private void clearUserData() {
        try {
            jsonWriter.open();
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        }
    }

    private void loadUser() {
        try {
            user = jsonReader.read();
        } catch (IOException e) {
            System.out.println("unable to load data from " + JSON_STORE);
        } catch (JSONException e) {
            System.out.println("File is empty");

        }
    }

}


