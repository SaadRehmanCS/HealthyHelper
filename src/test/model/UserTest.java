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
        Food food = new Food("Food", 500, "lunch");

        user.addFood(food);
        assertEquals(user.getFoodSize(), 1);
    }

    @Test
    public void testDrinkWaterOnce() {
        user.drinkWater();
        assertEquals(user.getWaterLogSize(), 1);
    }

    @Test
    public void testDrinkWaterMultiple() {
        user.drinkWater(5);
        assertEquals(user.getWaterLogSize(), 5);
    }

    @Test
    public void testGetMealTypeFromNums() {
        assertEquals(user.getMealTypeFromNums(1), "breakfast");
        assertEquals(user.getMealTypeFromNums(2), "lunch");
        assertEquals(user.getMealTypeFromNums(3), "dinner");
        assertEquals(user.getMealTypeFromNums(4), "snack");
    }

    @Test
    public void testFoodDisplayEmpty() {
        assertEquals(user.foodDisplay(), "\n");
    }

    @Test
    public void testFoodDisplay() {
        Food food = new Food("Food", 500, "lunch");
        user.addFood(food);

        String printIntro = "Food items consumed today: \n   Name            Calories    Meal\n";
        String foodDisplay = "1) " + String.format("%-16s%-12d%-10s\n",
                food.getFoodName(), food.getTotalCalories(), food.getMealType());

        assertEquals(user.foodDisplay(), printIntro + foodDisplay + "\n");


    }
}
