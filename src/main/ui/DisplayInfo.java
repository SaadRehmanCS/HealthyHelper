package ui;

import model.DietPlan;

import java.util.Scanner;

public class DisplayInfo {


    private DietPlan dietPlan;

    public DisplayInfo() {
    }

    public void beginProgram() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to HealthyHelper! the application "
                + " that can help you keep your fitness goals on track!\n"
                + "Lets begin by entering your height and weight below.");
        System.out.print("Height(cm): ");
        double height = input.nextDouble();
        System.out.print("Weight(kg): ");
        double weight = input.nextDouble();

        dietPlan = new DietPlan(height, weight);
        dietPlanDecider();

    }

    public void dietPlanDecider() {
        System.out.println(" Your BMI comes out to: " + dietPlan.calculateBMI()
                + ".\n According to our estimates, you are classified as " + dietPlan.bmiAssessment()
                + ".\n Based on this, we recommend that you choose the \"" + dietPlan.dietPlanRecommendation()
                + "\" strategy to achieve the best results");

    }


}


