package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MealTypeTest {

    MealType mealType;


    @Test
    public void testBreakfast() {
        mealType = new MealType("breakfast");
        assertEquals(mealType.getMealType(), "breakfast");
    }

    @Test
    public void testLunch() {
        mealType = new MealType("lunch");
        assertEquals(mealType.getMealType(), "lunch");
    }

    @Test
    public void testDinner() {
        mealType = new MealType("dinner");
        assertEquals(mealType.getMealType(), "dinner");
    }

    @Test
    public void testSnack() {
        mealType = new MealType("snack");
        assertEquals(mealType.getMealType(), "snack");
    }

}
