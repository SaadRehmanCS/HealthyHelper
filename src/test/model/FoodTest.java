package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FoodTest {

    Food food;

    @BeforeEach
    public void setup() {
        food = new Food("name", 100, MealType.LUNCH, "6:00");
    }

    @Test
    public void testAllGetters() {
        assertEquals("name", food.getFoodName());
        assertEquals(100, food.getCalories());
        assertEquals(MealType.LUNCH, food.getMealType());
        assertEquals("6:00", food.getTimeOfConsumption());
    }
}
