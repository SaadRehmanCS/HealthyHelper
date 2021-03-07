package model;

import model.exceptions.ImpossibleBodyDimensionsException;
import model.exceptions.InvalidDietPlanException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DietPlanTest {

    DietPlan planOver;
    DietPlan planUnder;
    DietPlan planNormal;

    @BeforeEach
    public void setup() {
        try {
            planOver = new DietPlan(175, 85);
            planUnder = new DietPlan(200, 50);
            planNormal = new DietPlan(190, 85);
        } catch (ImpossibleBodyDimensionsException e) {
            fail();
        }

    }

    @Test
    public void testDietPlanConstructorException() {
        try {
            planOver = new DietPlan(5, 5);
            fail();
        } catch (ImpossibleBodyDimensionsException e) {
            //stub
        }
    }

    @Test
    public void testBmiCalculator() {
        try {
            assertEquals(27.8, planOver.calculateBMI());
        } catch (ImpossibleBodyDimensionsException e) {
            fail();
        }
    }

    @Test
    public void testBmiCalculatorExceptionThrown() {
        DietPlan planException = null;
        try {
            planException = new DietPlan(150, 150);
        } catch (ImpossibleBodyDimensionsException e) {
            fail();
        }
        try {
            planException.calculateBMI();
            fail();
        } catch (ImpossibleBodyDimensionsException e) {
            //stub
        }
    }

    @Test
    public void testBmiAssessment() {
        try {
            assertEquals("overweight", planOver.bmiAssessment());
            assertEquals("underweight", planUnder.bmiAssessment());
            assertEquals("normal weight", planNormal.bmiAssessment());
        } catch (ImpossibleBodyDimensionsException e) {
            fail();
        }

        DietPlan planObese = null;
        try {
            planObese = new DietPlan(170, 100);
        } catch (ImpossibleBodyDimensionsException e) {
            fail();
        }
        try {
            assertEquals(planObese.bmiAssessment(), "obese");
        } catch (ImpossibleBodyDimensionsException e) {
            fail();
        }
    }

    @Test
    public void testDietPlanRecommendation() {
        try {
            assertEquals(planOver.dietPlanRecommendation(), "cut");
            assertEquals(planUnder.dietPlanRecommendation(), "bulk");
            assertEquals(planNormal.dietPlanRecommendation(), "maintain");
        } catch (ImpossibleBodyDimensionsException e) {
            fail();
        }

    }

    @Test
    public void testGetSelectedPlan() {
        try {
            planOver.setDietPlanUserSelection(2);
        } catch (InvalidDietPlanException e) {
            fail();
        }
        assertEquals(planOver.getSelectedPlan(), "cut");
    }

    @Test
    public void testSetDietPlanUserSelectionException() {
        try {
            planOver.setDietPlanUserSelection(4);
            fail();
        } catch (InvalidDietPlanException e) {
            //stub
        }
    }
}