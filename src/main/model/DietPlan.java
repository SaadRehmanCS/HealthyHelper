package model;

import model.exceptions.ImpossibleBodyDimensionsException;
import model.exceptions.InvalidDietPlanException;

//this class is used to provide personalized information
//to the user, having been provided their current body type, and
// finally determine an appropriate diet plan for them to take on.
public class DietPlan {

    private double height;
    private double weight;
    private String selectedPlan;

    //REQUIRES: height must be in cm, weight must be in kg
    //MODIFIES: this
    //EFFECTS: sets the users height and weight
    public DietPlan(double height, double weight) throws ImpossibleBodyDimensionsException {

        if (height < 10 || height > 250 || weight < 10 || weight > 200) {
            throw new ImpossibleBodyDimensionsException();
        }
        this.height = height;
        this.weight = weight;
    }

    //EFFECTS: calculates the BMI, then formats it
    //     so there are only 3 significant figures,
    //     and returns the double value
    public double calculateBMI() throws ImpossibleBodyDimensionsException {

        double bmi = weight / Math.pow((height / 100), 2);

        if (bmi > 40 || bmi < 5) {
            throw new ImpossibleBodyDimensionsException();
        }

        String bmiString = String.format("%.1f", bmi);

        return Double.parseDouble(bmiString);
    }

    //EFFECTS: based on the calculated BMI, it
    //     determines the body type classification
    //     given a set of ranges the BMI can fall under
    public String bmiAssessment() throws ImpossibleBodyDimensionsException {

        if (calculateBMI() < 18.5) {
            return "underweight";
        } else if (calculateBMI() < 24.9) {
            return "normal weight";
        } else if (calculateBMI() < 30) {
            return "overweight";
        } else {
            return "obese";
        }
    }

    //EFFECTS: generates a diet plan recommendation
    //     based on the BMI assessment
    public String dietPlanRecommendation() throws ImpossibleBodyDimensionsException {

        if (bmiAssessment().equals("underweight")) {
            return "bulk";
        } else if (bmiAssessment().equals("normal weight")) {
            return "maintain";
        } else {
            return "cut";
        }
    }

    //MODIFIES: this
    //EFFECTS: sets this to the user-selected diet plan
    public void setDietPlanUserSelection(int userInput) throws InvalidDietPlanException {
        if (userInput < 1 || userInput > 3) {
            throw new InvalidDietPlanException();
        }

        if (userInput == 1) {
            selectedPlan = "bulk";
        } else if (userInput == 2) {
            selectedPlan = "cut";
        } else {
            selectedPlan = "maintain";
        }

    }

    public String getSelectedPlan() {
        return selectedPlan;
    }


}
