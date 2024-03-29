package gui;

import model.Food;
import model.User;

import javax.swing.*;

//Class displays the food logging info as a JPanel component
public class FoodHistoryPanel extends JPanel {

    private final ProgramFrame frame;
    private final User user;
    JButton btn;
    JLabel historyText;

    //MODIFIES: this
    //EFFECTS: creates a new panel
    public FoodHistoryPanel(ProgramFrame frame) {
        super(null, false);
        this.frame = frame;
        user = ProgramFrame.user;
        btn = new JButton("Back");
        historyText = new JLabel("<html><div style=\"padding-left:100px;font-size:20;height: 250px;\">"
                + "Today's log</div></html>");

        buttonForBackToMainMenu();
        printHistoryTextPane();

    }

    //MODIFIES: this
    //EFFECTS: creates the button to go back to main menu
    public void buttonForBackToMainMenu() {
        add(btn);
        btn.setBounds(10, 20, 100, 50);
        btn.addActionListener(e -> {
            frame.switchFoodHistoryToMainMenuPanel();
            ProgramFrame.playSound("data/button_click.wav");
        });

    }

    //MODIFIES: this
    //EFFECTS: prints out all the food eaten onto the JScrollPane component
    public void printHistoryTextPane() {
        String text = "<div style=\"font-size:13;\">";
        for (Food food : user.getFoodLog()) {
            text += "<br>" + food.getFoodName() + "` ` `" + food.getCalories() + "` ` `" + food.getMealType() + "` ` `"
                    + food.getTimeOfConsumption() + "<br>";
        }
        text += "</div>";
        historyText = new JLabel("<html><div style=\"height: 250px;\"><div style=\"padding-left:100px;font-size:20;\">"
                + "Today's log</div>" + text + "</div></html>");
        JScrollPane scrollPane = new JScrollPane(historyText);
        scrollPane.setBounds(60, 90, 500, 450);

        add(scrollPane);

    }
}
