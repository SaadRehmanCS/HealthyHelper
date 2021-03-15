package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserTest {

    User user;

    @BeforeEach
    public void setup() {
        user = new User();
    }

    @Test
    public void testAddFood() {
        Food food = new Food("Food", 500, MealType.LUNCH, "");

        user.addFood(food);
        assertEquals(user.getFoodSize(), 1);
    }

    @Test
    public void testDrinkWaterOnce() {
        user.drinkWater();
        assertEquals(user.getWater().getCupsConsumed(), 1);
    }

    @Test
    public void testDrinkWaterMultiple() {
        user.drinkWater(5);
        assertEquals(user.getWater().getCupsConsumed(), 5);
    }

    @Test
    public void testGetMealTypeFromNums() {
        assertEquals(user.getMealTypeFromNums(1), MealType.BREAKFAST);
        assertEquals(user.getMealTypeFromNums(2), MealType.LUNCH);
        assertEquals(user.getMealTypeFromNums(3), MealType.DINNER);
        assertEquals(user.getMealTypeFromNums(4), MealType.SNACK);
    }

    @Test
    public void testFoodDisplayEmpty() {
        assertEquals(user.foodDisplay(), "\n");
    }

    @Test
    public void testFoodDisplay() {
        Food food = new Food("Food", 500, MealType.LUNCH, "");
        user.addFood(food);

        String printIntro = "Food items consumed today: \n   Name            Calories    Meal\n";
        String foodDisplay = "1) " + String.format("%-16s%-12d%-14s%-16s\n",
                food.getFoodName(), food.getTotalCalories(), food.getMealType(), food.getTimeOfConsumption());

        assertEquals(user.foodDisplay(), printIntro + foodDisplay + "\n");
    }

    @Test
    public void testAddSleep() {
        user.addSleep(4);
        assertEquals(4, user.getSleepTime());
    }

    @Test
    public void testSetAllFieldsToZero() {
        user.drinkWater(3);
        user.addSleep(4);
        user.addFood(new Food("food", 342, MealType.LUNCH, "8:00"));
        user.setAllFieldsToZero();
        assertEquals(0, user.getWater().getCupsConsumed());
        assertEquals(0, user.getFoodSize());
        assertEquals(user.getCalorieTarget().getOriginalCalorieTarget(), user.getCalorieTarget().getCaloriesRemaining());
        assertEquals(0, user.getCalorieTarget().getCaloriesConsumed());
        assertEquals(0, user.getSleep().getSleepTime());
    }
}
