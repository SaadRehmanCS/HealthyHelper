package gui;

import javax.swing.*;

import model.MealType;
import model.User;

import java.util.Calendar;
import java.util.TimeZone;

public class MainMenuPanel extends JPanel {

    ProgramFrame frame;
    private static User user;
    Integer day;
    JLabel caloriesConsumed;
    JLabel originalCalorieTarget;
    JLabel remainingCalories;
    JButton breakfastBtn;
    JButton lunchBtn;
    JButton dinnerBtn;
    JButton snackBtn;
    JButton waterBtn;
    JLabel waterCupsLabel;

    public MainMenuPanel(ProgramFrame frame) {
        super(null, false);
        day = ProgramFrame.getDay();
        user = ProgramFrame.user;
        this.frame = frame;
        waterCupsLabel = new JLabel();

        printCalorieInformation();
        foodButtons();
        waterButtons();
    }

    public void printCalorieInformation() {
        caloriesConsumed = new JLabel("<html>Consumed<br><br>" + user.getCalorieTarget().getCaloriesConsumed()
                + "</html>");
        caloriesConsumed.setBounds(50, 60, 100, 100);
        add(caloriesConsumed);

        originalCalorieTarget = new JLabel("<html>Original<br>Target<br><br>"
                + user.getCalorieTarget().getOriginalCalorieTarget() + "</html>");
        originalCalorieTarget.setBounds(490, 60, 100, 100);
        add(originalCalorieTarget);

        remainingCalories = new JLabel("<html>Calories<br>Remaining<br><br>"
                + user.getCalorieTarget().getCaloriesRemaining() + "<html>");
        remainingCalories.setBounds(270, 40, 100, 100);
        add(remainingCalories);

    }

    public void foodButtons() {
        breakfastBtn = new JButton("<html>Breakfast<br>"
                + user.getAllCaloriesForMealType(MealType.BREAKFAST) + "</html>");
        add(breakfastBtn);
        breakfastBtn.setBounds(60, 200, 100, 50);

        lunchBtn = new JButton("<html>Lunch<br>"
                + user.getAllCaloriesForMealType(MealType.LUNCH) + "</html>");
        add(lunchBtn);
        lunchBtn.setBounds(180, 200, 100, 50);

        dinnerBtn = new JButton("<html>Dinner<br>"
                + user.getAllCaloriesForMealType(MealType.DINNER) + "</html>");
        add(dinnerBtn);
        dinnerBtn.setBounds(300, 200, 100, 50);

        snackBtn = new JButton("<html>Snack<br>"
                + user.getAllCaloriesForMealType(MealType.SNACK) + "</html>");
        add(snackBtn);
        snackBtn.setBounds(420, 200, 100, 50);
    }

    public void waterButtons() {
        String cupsConsumed = "";
        for (int i = 0; i < user.getWater().getCupsConsumed(); i++) {
            cupsConsumed += "1 ";
        }
        waterCupsLabel.setText(cupsConsumed);
        add(waterCupsLabel);
        waterCupsLabel.setBounds(230, 350, 300, 50);

        waterBtn = new JButton("Drink water");
        add(waterBtn);
        waterBtn.setBounds(60, 350, 120, 50);
        waterBtn.addActionListener(e -> {
            user.getWater().incrementWater();
            waterAnimation();
        });
    }

    public void waterAnimation() {
        waterCupsLabel.setText(waterCupsLabel.getText() + "1 ");

        if (user.getWater().getCupsConsumed() >= 8) {
            waterCupsLabel.setText("");
            user.getWater().setCupsConsumed(0);
        }
    }


    public static User getUser() {
        return user;
    }

    public void beginNewDayProtocol(int declaredDay) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int currentDay = calendar.get(Calendar.DATE);

        int currentTime = calendar.get(Calendar.HOUR_OF_DAY);
        String typeOfTime;
        if (currentTime < 11) {
            typeOfTime = "morning";
        } else if (currentTime < 16) {
            typeOfTime = "afternoon";
        } else if (currentTime < 20) {
            typeOfTime = "evening";
        } else {
            typeOfTime = "night";
        }
        if (currentDay != declaredDay) {
            user.setAllFieldsToZero();
            day = currentDay;
            System.out.println("Good " + typeOfTime + "! Good job on your progress yesterday,\n"
                    + "lets start tracking information for today:");
        }
    }

}
