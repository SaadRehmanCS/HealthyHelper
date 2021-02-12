package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalorietargetTest {

    CalorieTarget target;
    DietPlan plan;

    @BeforeEach
    public void setup() {

        plan = new DietPlan(20, 80);
        plan.setDietPlanUserSelection("bulk");
        target = new CalorieTarget(plan);

    }

    @Test
    public void testCalorieTarget() {
        plan.setDietPlanUserSelection("bulk");
        assertEquals(target.getCalorieTarget(), (int) plan.calculateBMI() * 125);


        plan.setDietPlanUserSelection("cut");
        target = new CalorieTarget(plan);
        assertEquals(target.getCalorieTarget(), (int) plan.calculateBMI() * 95);


        plan.setDietPlanUserSelection("maintain");
        target = new CalorieTarget(plan);
        assertEquals(target.getCalorieTarget(), (int) plan.calculateBMI() * 110);
    }

    @Test
    public void testUpdateCalorieTarget() {
        Food food = new Food("Pasta", 500, "lunch");

        int previousTarget = target.getCalorieTarget();
        int previousConsumed = target.getCaloriesConsumed();

        target.updateCalorieTarget(food);
        assertEquals(target.getCalorieTarget(), previousTarget -500);
        assertEquals(target.getCaloriesConsumed(), previousConsumed + 500);

    }
}
