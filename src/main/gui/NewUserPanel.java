package gui;

import model.DietPlan;
import model.exceptions.ImpossibleBodyDimensionsException;

import javax.swing.*;
import java.util.InputMismatchException;

public class NewUserPanel extends JPanel {

    JLabel welcomeMsg;
    JLabel heightLabel;
    JTextField heightText;
    JLabel weightLabel;
    JTextField weightText;
    JButton enterButton;
    private DietPlan dietPlan;
    JLabel exceptionMsg;
    JLabel bmiInfo;
    JButton cutBtn;
    JButton bulkBtn;
    JButton maintainBtn;


    public NewUserPanel() {

        new JPanel();
        setLayout(null);
        promptUserInformation();

    }

    private void promptUserInformation() {
        welcomeMsg = new JLabel("<html><body><b>Welcome to HealthyHelper!</b> the application "
                + " that can help you keep your fitness<br> goals on track! "
                + "Lets begin by entering your height and weight below.<body></html>");
        add(welcomeMsg);
        welcomeMsg.setBounds(50, 20, 1000, 50);

        heightLabel = new JLabel("Height(cm): ");
        add(heightLabel);
        heightLabel.setBounds(10, 100, 120, 25);

        heightText = new JTextField();
        add(heightText);
        heightText.setBounds(80, 100, 100, 25);

        weightLabel = new JLabel("Weight(kg): ");
        add(weightLabel);
        weightLabel.setBounds(10, 150, 120, 25);

        weightText = new JTextField();
        add(weightText);
        weightText.setBounds(80, 150, 100, 25);

        exceptionMsg = new JLabel("");
        add(exceptionMsg);
        exceptionMsg.setBounds(80, 180, 300, 25);
        handleUserInput();
    }

    public void handleUserInput() {
        enterButton = new JButton("Calculate");
        add(enterButton);
        enterButton.setBounds(40, 210, 100, 60);
        enterButton.addActionListener(e -> {
            try {
                double height = Double.parseDouble(heightText.getText());
                double weight = Double.parseDouble(weightText.getText());
                dietPlan = new DietPlan(height, weight);
                exceptionMsg.setText("");
                bmiInfo = new JLabel();
                bmiInfo.setText("<html><body>Your BMI comes out to: " + dietPlan.calculateBMI()
                        + ".<br>According to our estimates, you are classified as " + dietPlan.bmiAssessment()
                        + ".<br> Based on this, we recommend that you choose the <b>" + dietPlan.dietPlanRecommendation()
                        + "</b> strategy<br> to achieve the best results</body></html>");
                bmiPlanPicker();
            } catch (NumberFormatException exception) {
                exceptionMsg.setText("Please enter numerical values");
            } catch (ImpossibleBodyDimensionsException exception) {
                exceptionMsg.setText("These values are invalid");
            }

        });

    }

    private void bmiPlanPicker() {
        add(bmiInfo);
        bmiInfo.setBounds(80, 290, 1000, 100);
        cutBtn = new JButton("Cut");
        bulkBtn = new JButton("Bulk");
        maintainBtn = new JButton("Maintain");

    }
}
