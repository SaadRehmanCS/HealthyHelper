package ui;

import model.DietPlan;
import model.Food;
import model.User;
import model.exceptions.ImpossibleBodyDimensionsException;
import model.exceptions.InvalidDietPlanException;
import persistence.JsonReader;
import persistence.JsonWriter;
import org.json.JSONException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

//Contains almost all the console print statements and user interaction.
//Contains methods to save and/or load JSON data
public class DisplayInfo {

    protected static final Calendar CALENDER = Calendar.getInstance(TimeZone.getDefault());
    private static final String JSON_STORE = "./data/user.json";
    protected static int RUN_PROGRAM = 1;
    private static int sleepEntryCounter = 0;
    protected static int day;

    private DietPlan dietPlan;
    protected User user;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Scanner input;

    //MODIFIES: this
    //EFFECTS: instantiates User object and JSON reader and writer. Asks the user if they are
    //        starting a new program or simply returning
    public DisplayInfo() {
        user = new User();
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        boolean proceed = false;
        while (!proceed) {
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

    public static int getDay() {
        return day;
    }

    public static void setDay(int day) {
        DisplayInfo.day = day;
    }

    //MODIFIES: this
    //EFFECTS: If user is returning, it loads stored JSON data from file and continues program from main menu
    //        If user is new, it clears all old JSON data, sets day to the current day, and begins program from start
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
            day = CALENDER.get(Calendar.DATE);
            beginProgram();
            dietPlanDecider();
        }

    }

    //MODIFIES: this
    //EFFECTS: for new users, this is the first method to be displayed to them. It asks the user for height
    // and weight information, and then instantiates DietPlan
    public void beginProgram() {

        boolean proceed = false;
        while (!proceed) {
            try {
                System.out.println("Welcome to HealthyHelper! the application "
                        + " that can help you keep your fitness goals on track!\n"
                        + "Lets begin by entering your height and weight below.");
                System.out.print("Height(cm): ");
                double height = input.nextDouble();
                System.out.print("Weight(kg): ");
                double weight = input.nextDouble();
                dietPlan = new DietPlan(height, weight);
                proceed = true;

            } catch (InputMismatchException | ImpossibleBodyDimensionsException e) {
                System.out.println(e);
                input.nextLine();
            }
        }

    }

    //EFFECTS: prints BMI calculations for user to see.
    public void printBmiInfo() throws ImpossibleBodyDimensionsException {
        System.out.println(" Your BMI comes out to: " + dietPlan.calculateBMI()
                + ".\n According to our estimates, you are classified as " + dietPlan.bmiAssessment()
                + ".\n Based on this, we recommend that you choose the \"" + dietPlan.dietPlanRecommendation()
                + "\" strategy to achieve the best results");
    }

    //MODIFIES: this
    //EFFECTS: used to help user decide their diet plan
    public void dietPlanDecider() {

        boolean proceed = false;
        while (!proceed) {
            try {
                printBmiInfo();
                proceed = true;

            } catch (ImpossibleBodyDimensionsException e) {
                System.out.println(e);
                beginProgram();
            }
        }

        System.out.println("What strategy do you choose?:\n1 ---> \"Bulk\"\n2 ---> \"Cut\"\n3 ---> \"Maintain\"");
        proceed = false;
        while (!proceed) {
            try {
                dietPlan.setDietPlanUserSelection(input.nextInt());
                user.getCalorieTarget().setOriginalTarget(dietPlan);
                proceed = true;
            } catch (ImpossibleBodyDimensionsException | InputMismatchException e) {
                System.out.println(e);
                input.nextLine();
            }
        }
    }

    //EFFECTS: prints CalorieTarget information for the user to see
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

    //EFFECTS: prints Water information for the user to see
    public void mainMenuWaterDisplay() {

        if (user.getWater().getCupsConsumed() < user.getWater().getDailyRequirement()) {
            int remainingRequirement = user.getWater().getDailyRequirement() - user.getWater().getCupsConsumed();
            System.out.println("You have consumed " + user.getWater().getCupsConsumed() + " cups of water"
                    + " today. You are " + remainingRequirement + " cups away from consuming a healthy amount"
                    + " of water today.");
        } else {
            System.out.println("Hooray! you have consumed a healthy amount of water for today. You may"
                    + " continue to drink more as you like!");
        }
        System.out.println("\n");
    }

    //EFFECTS: prints the logging menu options
    public void loggingDisplay() {
        System.out.println("1 ---> Add a food item consumed");
        System.out.println("2 ---> Add an amount of water consumed");
        System.out.println("3 ---> Track your sleep habits");
        System.out.println("\n0 ---> Quit the program! :(");
        loggingEntry();
    }

    //MODIFIES: this
    //EFFECTS: runs the specific menu logging options based on user input
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

    //EFFECTS: handles the user input to ensure it is correct
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

    //EFFECTS: allows user to enter new food items, and see history of previously entered food
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

    //EFFECTS: allows the user to enter water logging information
    public void waterEntry() {
        System.out.println("How many cups of water would you like to add?");
        int waterInput = input.nextInt();

        if (waterInput == 1) {
            user.drinkWater();
        } else {
            user.drinkWater(waterInput);
        }

    }

    //EFFECTS: allows the user to enter sleep information
    public void sleepEntry() {
        if (sleepEntryCounter == 1) {
            System.out.print("Enter the number of hours you were able to sleep last night: ");
            double sleepInput = input.nextDouble();
            user.getSleep().addSleepTime(sleepInput);
        }

        System.out.println(user.getSleep().sleepAssessment() + "\n");
    }

    //MODIFIES: this
    //EFFECTS: saves the data to file
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

    //REQUIRES: only run this when a new user runs the program
    //MODIFIES: this
    //EFFECTS: clears all user data
    private void clearUserData() {
        try {
            jsonWriter.open();
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        }
    }

    //MODIFIES: this
    //EFFECTS: loads returning user data from file
    private void loadUser() {
        try {
            user = jsonReader.read();
        } catch (IOException e) {
            System.out.println("unable to load data from " + JSON_STORE);
        } catch (JSONException e) {
            day = CALENDER.get(Calendar.DATE);
            System.out.println("File is empty");

        }
    }
}


