package model;

import model.exceptions.ImpossibleBodyDimensionsException;
import model.exceptions.InvalidDietPlanException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalorieTargetTest {

    CalorieTarget target;
    DietPlan plan;

    @BeforeEach
    public void setup() {

        try {
            plan = new DietPlan(180, 80);
        } catch (ImpossibleBodyDimensionsException e) {
            fail();
        }

        try {
            plan.setDietPlanUserSelection(1);
        } catch (InvalidDietPlanException e) {
            fail();
        }
        target = new CalorieTarget();
        try {
            target.setOriginalTarget(plan);
        } catch (ImpossibleBodyDimensionsException e) {
            fail();
        }

    }

    @Test
    public void testCalorieTargetConstructor() {
        CalorieTarget emptyTarget = new CalorieTarget();
        assertEquals(0, emptyTarget.getCaloriesConsumed());
        assertEquals(0, emptyTarget.getCaloriesRemaining());
        assertEquals(0, emptyTarget.getOriginalCalorieTarget());
    }

    @Test
    public void testCalorieTargetSetters() {
        target.setOriginalTarget(3000);
        assertTrue(target.getOriginalCalorieTarget() == 3000);

        target.setCaloriesConsumed(50);
        assertTrue(target.getCaloriesConsumed() == 50);

        target.setCaloriesRemaining(300);
        assertTrue(target.getCaloriesRemaining() == 300);
    }

    @Test
    public void testCalorieTargetBulk() {
        try {
            plan.setDietPlanUserSelection(1);
            target.setOriginalTarget(plan);
        } catch (InvalidDietPlanException | ImpossibleBodyDimensionsException e) {
            fail();
        }
        try {
            assertEquals(target.getCaloriesRemaining(), (int) plan.calculateBMI() * 125);
        } catch (ImpossibleBodyDimensionsException e) {
            fail();
        }

    }

    @Test
    public void testCalorieTargetCut() {
        try {
            plan.setDietPlanUserSelection(2);
            target.setOriginalTarget(plan);
        } catch (InvalidDietPlanException | ImpossibleBodyDimensionsException e) {
            fail();
        }
        try {
            target.setOriginalTarget(plan);
        } catch (ImpossibleBodyDimensionsException e) {
            fail();
        }
        try {
            assertEquals(target.getCaloriesRemaining(), (int) plan.calculateBMI() * 95);
        } catch (ImpossibleBodyDimensionsException e) {
            fail();
        }

    }

    @Test
    public void testCalorieTargetMaintain() {
        try {
            plan.setDietPlanUserSelection(3);
            target.setOriginalTarget(plan);
        } catch (InvalidDietPlanException | ImpossibleBodyDimensionsException e) {
            fail();
        }
        try {
            target.setOriginalTarget(plan);
        } catch (ImpossibleBodyDimensionsException e) {
            fail();
        }
        try {
            assertEquals(target.getCaloriesRemaining(), (int) plan.calculateBMI() * 110);
        } catch (ImpossibleBodyDimensionsException e) {
            fail();
        }
        //assertEquals(target.getOriginalCalorieTarget(), target.getCalorieTarget());
    }

    @Test
    public void testUpdateCalorieTarget() {
        Food food = new Food("Pasta", 500, MealType.LUNCH, "");

        int previousTarget = target.getCaloriesRemaining();
        int previousConsumed = target.getCaloriesConsumed();

        target.updateCalorieTarget(food);
        assertEquals(target.getCaloriesRemaining(), previousTarget - 500);
        assertEquals(target.getCaloriesConsumed(), previousConsumed + 500);

    }


}
