//package model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class DietPlanTest {
//
//    DietPlan planOver;
//    DietPlan planUnder;
//    DietPlan planNormal;
//
//    @BeforeEach
//    public void setup() {
//        planOver = new DietPlan(175, 85);
//        planUnder = new DietPlan(200, 50);
//        planNormal = new DietPlan(190, 85);
//
//    }
//
//    @Test
//    public void testBmiCalulator() {
//        assertEquals(planOver.calculateBMI(), 27.8);
//    }
//
//    @Test
//    public void testBmiAssessment() {
//        assertEquals(planOver.bmiAssessment(), "overweight");
//        assertEquals(planUnder.bmiAssessment(), "underweight");
//        assertEquals(planNormal.bmiAssessment(), "normal weight");
//
//        DietPlan planObese = new DietPlan(150, 150);
//        assertEquals(planObese.bmiAssessment(), "obese");
//    }
//
//    @Test
//    public void testDietPlanRecommendation() {
//        assertEquals(planOver.dietPlanRecommendation(), "cut");
//        assertEquals(planUnder.dietPlanRecommendation(), "bulk");
//        assertEquals(planNormal.dietPlanRecommendation(), "maintain");
//    }
//
//    @Test
//    public void testGetSelectedPlan() {
//        planOver.setDietPlanUserSelection("cut");
//        assertEquals(planOver.getSelectedPlan(), "cut");
//    }
//}