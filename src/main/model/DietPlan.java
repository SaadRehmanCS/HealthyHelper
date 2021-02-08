package model;

public class DietPlan {

    //centimetres
    private double height;
    //kilograms
    private double weight;
    private String selectedPlan;

    public DietPlan(double height, double weight) {
        this.height = height;
        this.weight = weight;
    }

    public double calculateBMI() {

        double bmi = weight / Math.pow((height / 100), 2);
        String bmiString = String.format("%.1f", bmi);

        return Double.parseDouble(bmiString);
    }

    public String bmiAssessment() {

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

    public String dietPlanRecommendation() {

        if (bmiAssessment().equals("underweight")) {
            return "bulk";
        } else if (bmiAssessment().equals("normal weight")) {
            return "maintain";
        } else {
            return "cut";
        }
    }

    public void setDietPlanUserSelection(String userInput) {
        selectedPlan = userInput;
    }

    public String getSelectedPlan() {
        return selectedPlan;
    }


}
