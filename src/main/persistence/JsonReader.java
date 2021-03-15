package persistence;

import model.Food;
import model.MealType;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import ui.DisplayInfo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//CITATION: Some methods in this class have been taken directly from the github
//          repo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
//          while others may have been slightly modified
// credits: Paul Carter

// Represents a reader that reads User from JSON data stored in file
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads User from file and returns it;
    // throws IOException if an error occurs reading data from file
    public User read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUser(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses User from JSON object and returns it
    private User parseUser(JSONObject jsonObject) {
        User user = new User();
        addFoodLog(user, jsonObject);
        addWater(user, jsonObject);
        addSleepAndTime(user, jsonObject);
        addCalorieTarget(user, jsonObject);
        return user;

    }

    // MODIFIES: User
    // EFFECTS: parses CalorieTarget from JSON object and adds to User
    private void addCalorieTarget(User user, JSONObject jsonObject) {
        JSONObject json = jsonObject.getJSONObject("calorie target");
        int remaining = json.getInt("remaining target");
        int consumed = json.getInt("calories consumed");
        int original = json.getInt("original target");
        user.getCalorieTarget().setOriginalTarget(original);
        user.getCalorieTarget().setCaloriesConsumed(consumed);
        user.getCalorieTarget().setCaloriesRemaining(remaining);
    }

    // MODIFIES: User
    // EFFECTS: parses Sleep and time from JSON object and adds to User
    private void addSleepAndTime(User user, JSONObject jsonObject) {
        double sleepTime = jsonObject.getDouble("sleep time");
        int day = jsonObject.getInt("day of the month");
        user.addSleep(sleepTime);
        DisplayInfo.setDay(day);
    }

    // MODIFIES: User
    // EFFECTS: parses Water from JSON object and adds to User
    private void addWater(User user, JSONObject jsonObject) {
        int amountConsumed = jsonObject.getInt("water consumed");
        user.drinkWater(amountConsumed);
    }

    // MODIFIES: User
    // EFFECTS: parses foodLog from JSON object and adds to User
    private void addFoodLog(User user, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("food");
        for (Object json : jsonArray) {
            JSONObject nextFood = (JSONObject) json;
            addFood(user, nextFood);
        }
    }

    // MODIFIES: User
    // EFFECTS: parses food from JSON object and adds to User
    private void addFood(User user, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int totalCalories = jsonObject.getInt("total calories");
        MealType mealType = MealType.valueOf(jsonObject.getString("meal type"));
        String timeOfConsumption = jsonObject.getString("time of consumption");
        Food food = new Food(name, totalCalories, mealType, timeOfConsumption);
        user.addFood(food);
    }

}
