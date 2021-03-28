package gui;

import javax.swing.*;

import model.Food;
import model.MealType;
import model.User;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    JButton printHistory;
    JLabel foodNameLabel;
    JTextField foodNameText;
    JLabel foodCaloriesLabel;
    JTextField foodCaloriesText;
    JButton foodSubmitBtn;
    JLabel exceptionMsg;

    public MainMenuPanel(ProgramFrame frame) {
        super(null, false);
        day = ProgramFrame.getDay();
        user = ProgramFrame.user;
        this.frame = frame;
        waterCupsLabel = new JLabel();

        printCalorieInformation();
        foodButtons();
        waterButtons();
        showFoodHistory();
    }

    public void printCalorieInformation() {
        if (caloriesConsumed != null) {
            caloriesConsumed.setVisible(false);
            originalCalorieTarget.setVisible(false);
            remainingCalories.setVisible(false);
        }
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
        if (breakfastBtn != null) {
            setFoodButtonVisibility(false);
        }
        breakfastBtn = new JButton("<html>Breakfast<br>"
                + user.getAllCaloriesForMealType(MealType.BREAKFAST) + "</html>");
        add(breakfastBtn);
        breakfastBtn.setBounds(60, 200, 100, 50);
        breakfastBtn.addActionListener(e -> promptFoodFromUser(MealType.BREAKFAST));

        lunchBtn = new JButton("<html>Lunch<br>"
                + user.getAllCaloriesForMealType(MealType.LUNCH) + "</html>");
        add(lunchBtn);
        lunchBtn.setBounds(180, 200, 100, 50);
        lunchBtn.addActionListener(e -> promptFoodFromUser(MealType.LUNCH));


        dinnerBtn = new JButton("<html>Dinner<br>"
                + user.getAllCaloriesForMealType(MealType.DINNER) + "</html>");
        add(dinnerBtn);
        dinnerBtn.setBounds(300, 200, 100, 50);
        dinnerBtn.addActionListener(e -> promptFoodFromUser(MealType.DINNER));


        snackBtn = new JButton("<html>Snack<br>"
                + user.getAllCaloriesForMealType(MealType.SNACK) + "</html>");
        add(snackBtn);
        snackBtn.setBounds(420, 200, 100, 50);
        snackBtn.addActionListener(e -> promptFoodFromUser(MealType.SNACK));

    }

    private void promptFoodFromUser(MealType mealType) {
        setAllFoodButtonsVisibility(false);
        foodNameLabel = new JLabel("Food: ");
        add(foodNameLabel);
        foodNameLabel.setBounds(80, 200, 50, 25);

        foodNameText = new JTextField();
        add(foodNameText);
        foodNameText.setBounds(125, 200, 100, 25);

        foodCaloriesLabel = new JLabel("Calories: ");
        add(foodCaloriesLabel);
        foodCaloriesLabel.setBounds(280, 200, 60, 25);

        foodCaloriesText = new JTextField();
        add(foodCaloriesText);
        foodCaloriesText.setBounds(345, 200, 100, 25);

        foodSubmitBtn = new JButton("Submit");
        add(foodSubmitBtn);
        foodSubmitBtn.setBounds(240, 240, 100, 50);
        foodSubmitBtn.addActionListener(e -> enterFoodAndRestartPrompt(mealType));

        exceptionMsg = new JLabel("");
        add(exceptionMsg);
        exceptionMsg.setBounds(270, 250, 300, 25);

    }

    private void enterFoodAndRestartPrompt(MealType mealType) {
        String foodName = "";
        int foodCalories = 0;
        try {
            exceptionMsg.setText("");
            foodName = foodNameText.getText();
            foodCalories = Integer.parseInt(foodCaloriesText.getText());

        } catch (NumberFormatException exception) {
            exceptionMsg.setText("");
        }

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a  EEE, MMM d");
        String timeOfConsumption = formatter.format(date);

        Food food = new Food(foodName, foodCalories, mealType, timeOfConsumption);
        user.addFood(food);

        setAllFoodButtonsVisibility(true);
        setFoodEntryVisibility(false);
        printCalorieInformation();
        foodButtons();
    }

    public void setFoodButtonVisibility(boolean state) {
        breakfastBtn.setVisible(state);
        lunchBtn.setVisible(state);
        dinnerBtn.setVisible(state);
        snackBtn.setVisible(state);
    }

    public void setFoodEntryVisibility(boolean state) {
        foodNameText.setVisible(state);
        foodNameLabel.setVisible(state);
        foodCaloriesText.setVisible(state);
        foodCaloriesLabel.setVisible(state);
        foodSubmitBtn.setVisible(state);
    }

    public void setAllFoodButtonsVisibility(boolean state) {
        breakfastBtn.setVisible(state);
        lunchBtn.setVisible(state);
        dinnerBtn.setVisible(state);
        snackBtn.setVisible(state);
    }

    public void waterButtons() {
        String cupsConsumed = "";
        for (int i = 0; i < user.getWater().getCupsConsumed(); i++) {
            cupsConsumed += " ";
        }
        waterCupsLabel.setText(cupsConsumed);
        add(waterCupsLabel);
        waterCupsLabel.setBounds(195, 320, 350, 80);

        waterBtn = new JButton("Drink water");
        add(waterBtn);
        waterBtn.setBounds(40, 325, 110, 50);
        waterBtn.addActionListener(e -> {
            user.getWater().incrementWater();
            waterAnimation();
        });
    }

    public void waterAnimation() {
        waterCupsLabel.setText(waterCupsLabel.getText() + " ");

        if (user.getWater().getCupsConsumed() > 8) {
            waterCupsLabel.setText("");
            user.getWater().setCupsConsumed(0);
        }
    }

    public void showFoodHistory() {
        printHistory = new JButton("Show my history");
        add(printHistory);
        printHistory.setBounds(200, 450, 200, 50);
        printHistory.addActionListener(e -> {
            displayFoodHistoryFrame();
        });
    }

    private void displayFoodHistoryFrame() {
        frame.switchMainMenuToFoodHistoryPanel();
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < user.getWater().getCupsConsumed(); i++) {

            g.setColor(Color.BLUE);
            //water
            g.fillRect(210 + (i * 45), 323, 20, 39);
            g.setColor(Color.LIGHT_GRAY);
            //Left side glass
            g.fillRect(206 + (i * 45), 320, 4, 42);
            //Bottom glass
            g.fillRect(206 + (i * 45), 362, 24, 4);
            //Right side glass
            g.fillRect(226 + (i * 45), 320, 4, 42);
            //bubble1
            int randYBubble1 = (int) (Math.random() * 35);
            g.drawOval(213 + (i * 45), 323 + randYBubble1, 5, 5);
            //bubble2
            int randYBubble2 = (int) (Math.random() * 35);
            g.drawOval(216 + (i * 45), 323 + randYBubble2, 5, 5);
        }

    }
}
