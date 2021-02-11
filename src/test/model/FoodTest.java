package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FoodTest {

    Food food;

    @BeforeEach
    public void setup() {
        food = new Food("name" , 100, "lunch");
    }

    @Test
    public void testAllGetters() {
        assertEquals(food.getFoodName(), "name");
        assertEquals(food.getTotalCalories(), 100);
        assertEquals(food.getMealType(), "lunch");
    }
}
