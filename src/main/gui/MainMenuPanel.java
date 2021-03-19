package gui;

import javax.swing.*;

import model.User;
import org.json.JSONException;

import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;

import static gui.ProgramFrame.*;

public class MainMenuPanel extends JPanel {

    ProgramFrame frame;
    private static User user;
    Integer day;
    JLabel caloriesConsumed;
    JLabel originalCalorieTarget;

    public MainMenuPanel(ProgramFrame frame) {
        super(null, false);
        day = ProgramFrame.getDay();
        user = ProgramFrame.user;
        //loadUser();
        //beginNewDayProtocol(day);
        this.frame = frame;

//        System.out.println("consumed " + user.getCalorieTarget().getCaloriesConsumed());
//        System.out.println("remaining " + user.getCalorieTarget().getCaloriesRemaining());
//        System.out.println("original " + user.getCalorieTarget().getOriginalCalorieTarget());
//        System.out.println("water consumed " + user.getWater().getCupsConsumed());
//        System.out.println("food size " + user.getFoodSize());
//        System.out.println("day " + day);
//        System.out.println("sleep time " + user.getSleep().getSleepTime());
        addCalorieInformation();
    }

    public void addCalorieInformation() {
        caloriesConsumed = new JLabel("<html>Consumed<br>" + user.getCalorieTarget().getCaloriesConsumed() + "</html>");
        caloriesConsumed.setBounds(50, 60, 100, 100);
        add(caloriesConsumed);

        originalCalorieTarget = new JLabel("<html>Original<br>Target<br>"
                + user.getCalorieTarget().getOriginalCalorieTarget() + "</html>");
        originalCalorieTarget.setBounds(300, 40, 100, 100);
        add(originalCalorieTarget);
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
