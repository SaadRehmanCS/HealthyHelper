package gui;

import javax.swing.*;

import model.User;

import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.TimeZone;

public class MainMenuPanel extends JPanel {

    ProgramFrame frame;
    static User user;
    Integer day;

    public MainMenuPanel(ProgramFrame frame) {
        day = ProgramFrame.getDay();
        user = new User();
        beginNewDayProtocol(day);
        this.frame = frame;
        new JPanel();
        //setLayout(null);
        JTabbedPane pane = new JTabbedPane();
        JComponent button = new JButton("Hello");
        pane.addTab("Tab 1", button);
        JComponent button2 = new JButton("Second");
        pane.addTab("Tab 2", button2);
        add(pane);
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
