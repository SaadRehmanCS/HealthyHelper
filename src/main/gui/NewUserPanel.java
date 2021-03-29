package gui;

import model.DietPlan;
import model.User;
import model.exceptions.ImpossibleBodyDimensionsException;

import javax.swing.*;

public class NewUserPanel extends JPanel {

    JLabel welcomeMsg;
    JLabel heightLabel;
    JTextField heightText;
    JLabel weightLabel;
    JTextField weightText;
    JButton enterButton;
    DietPlan dietPlan;
    JLabel exceptionMsg;
    JLabel bmiInfo;
    JButton cutBtn;
    JButton bulkBtn;
    JButton maintainBtn;
    ProgramFrame frame;
    User user;


    public NewUserPanel(ProgramFrame frame) {
        super(null, false);
        this.frame = frame;
        user = ProgramFrame.user;

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
                ProgramFrame.playSound("data/button_click.wav");
                double height = Double.parseDouble(heightText.getText());
                double weight = Double.parseDouble(weightText.getText());
                dietPlan = new DietPlan(height, weight);
                exceptionMsg.setText("");
                bmiInfo = new JLabel();
                bmiInfo.setText("<html><body>Your BMI comes out to: " + dietPlan.calculateBMI()
                        + ".<br>According to our estimates, you are classified as " + dietPlan.bmiAssessment()
                        + ".<br> Based on this we recommend that you choose the <b>" + dietPlan.dietPlanRecommendation()
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
        dietPlan.setDietPlanUserSelection(2);
        bmiInfo.setBounds(80, 290, 1000, 100);

        handlePlanButtonClick();

        try {
            user.getCalorieTarget().setOriginalTarget(dietPlan);
        } catch (ImpossibleBodyDimensionsException exception) {
            System.out.println("exception");
        }
    }

    public void handlePlanButtonClick() {
        cutBtn = new JButton("Cut");
        add(cutBtn);
        cutBtn.setBounds(80, 390, 80, 50);
        cutBtn.addActionListener(e -> {
            ProgramFrame.playSound("data/button_click.wav");
            dietPlan.setDietPlanUserSelection(2);
            frame.switchNewUserToMainMenuPanel();
        });

        bulkBtn = new JButton("Bulk");
        add(bulkBtn);
        bulkBtn.setBounds(170, 390, 80, 50);
        bulkBtn.addActionListener(e -> {
            ProgramFrame.playSound("data/button_click.wav");
            dietPlan.setDietPlanUserSelection(1);
            frame.switchNewUserToMainMenuPanel();
        });
        helperToSetMaintain();

        setRecommendedButtonColor();
    }

    public void helperToSetMaintain() {
        maintainBtn = new JButton("Maintain");
        add(maintainBtn);
        maintainBtn.setBounds(260, 390, 100, 50);
        maintainBtn.addActionListener(e -> {
            ProgramFrame.playSound("data/button_click.wav");
            dietPlan.setDietPlanUserSelection(3);
            frame.switchNewUserToMainMenuPanel();
        });
    }

    private void setRecommendedButtonColor() {
        try {
            if (dietPlan.dietPlanRecommendation().equals("cut")) {
                cutBtn.setBackground(ProgramFrame.buttonBackgroundColor);
                bulkBtn.setBackground(ProgramFrame.buttonBackgroundColorUnfavourable);
                maintainBtn.setBackground(ProgramFrame.buttonBackgroundColorUnfavourable);
            } else if (dietPlan.dietPlanRecommendation().equals("bulk")) {
                bulkBtn.setBackground(ProgramFrame.buttonBackgroundColor);
                maintainBtn.setBackground(ProgramFrame.buttonBackgroundColorUnfavourable);
                cutBtn.setBackground(ProgramFrame.buttonBackgroundColorUnfavourable);

            } else {
                maintainBtn.setBackground(ProgramFrame.buttonBackgroundColor);
                cutBtn.setBackground(ProgramFrame.buttonBackgroundColorUnfavourable);
                bulkBtn.setBackground(ProgramFrame.buttonBackgroundColorUnfavourable);
            }
        } catch (ImpossibleBodyDimensionsException e) {
            e.printStackTrace();
        }
    }
}
