package model;

import model.exceptions.ImpossibleBodyDimensionsException;
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
    public void testDietPlanConstructorExceptionHeight() {
        try {
            planOver = new DietPlan(5, 20);
            fail();
        } catch (ImpossibleBodyDimensionsException e) {
            //expected
        }

        try {
            planUnder = new DietPlan(300, 50);
            fail();
        } catch (ImpossibleBodyDimensionsException e) {
            //expected
        }
    }

    @Test
    public void testDietPlanConstructorExceptionWeight() {
        try {
            planOver = new DietPlan(20, 5);
            fail();
        } catch (ImpossibleBodyDimensionsException e) {
            //expected
        }

        try {
            planUnder = new DietPlan(30, 300);
            fail();
        } catch (ImpossibleBodyDimensionsException e) {
            //expected
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
    public void testBmiCalculatorExceptionThrownTooHigh() {
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
            //expected
        }
    }

    @Test
    public void testBmiCalculatorExceptionThrownTooLow() {
        DietPlan planException = null;
        try {
            planException = new DietPlan(200, 15);
        } catch (ImpossibleBodyDimensionsException e) {
            fail();
        }
        try {
            planException.calculateBMI();
            fail();
        } catch (ImpossibleBodyDimensionsException e) {
            //expected
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

        planOver.setDietPlanUserSelection(2);

        assertEquals(planOver.getSelectedPlan(), "cut");
    }

}