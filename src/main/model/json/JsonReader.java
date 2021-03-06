package model.json;

import model.Food;
import model.MealType;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//citation: The majority of this class uses code taken from the CPSC 210 JsonSerializationDemo project
public class JsonReader {

    private String source;

    public JsonReader(String source) {
        this.source = source;
    }

    public User read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUser(jsonObject);
    }

    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    private User parseUser(JSONObject jsonObject) {
        User user = new User();
        addFoodLog(user, jsonObject);
        addWater(user, jsonObject);
        addSleep(user, jsonObject);
        addCalorieTarget(user, jsonObject);
        return user;

    }

    private void addCalorieTarget(User user, JSONObject jsonObject) {
        JSONObject json = jsonObject.getJSONObject("calorie target");
        int remaining = json.getInt("remaining target");
        int consumed = json.getInt("calories consumed");
        int original = json.getInt("original target");
        user.getCalorieTarget().setOriginalTarget(original);
        user.getCalorieTarget().setCaloriesConsumed(consumed);
        user.getCalorieTarget().setCaloriesRemaining(remaining);
    }

    private void addSleep(User user, JSONObject jsonObject) {
        double sleepTime = jsonObject.getDouble("sleep time");
        user.addSleep(sleepTime);
    }

    private void addWater(User user, JSONObject jsonObject) {
        int amountConsumed = jsonObject.getInt("water consumed");
        user.drinkWater(amountConsumed);
    }

    private void addFoodLog(User user, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("food");
        for (Object json: jsonArray) {
            JSONObject nextFood = (JSONObject) json;
            addFood(user, nextFood);
        }
    }

    private void addFood(User user, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int totalCalories = jsonObject.getInt("total calories");
        MealType mealType = MealType.valueOf(jsonObject.getString("meal type"));
        String timeOfConsumption = jsonObject.getString("time of consumption");
        Food food = new Food(name, totalCalories, mealType, timeOfConsumption);
        user.addFood(food);
    }

}
