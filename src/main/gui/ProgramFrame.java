package gui;

import model.DietPlan;
import model.Food;
import model.MealType;
import model.User;
import model.exceptions.ImpossibleBodyDimensionsException;
import org.json.JSONException;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.TimeZone;

public class ProgramFrame extends JFrame {

    public static final String JSON_STORE = "./data/user.json";
    public static final Color buttonBackgroundColor = Color.CYAN;
    public static final Color buttonForegroundColor = Color.CYAN;
    JPanel startPanel;
    JLabel startPrompt;
    JButton startBtn;
    JLabel returnPrompt;
    JButton returnBtn;
    static JsonWriter jsonWriter;
    static JsonReader jsonReader;
    static Integer day;
    static final Calendar CALENDER = Calendar.getInstance(TimeZone.getDefault());
    NewUserPanel newUserPanel;
    MainMenuPanel mainMenuPanel;
    static User user;


    public ProgramFrame() {
        super("Healthy Helper");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(600, 600));
        setLocationRelativeTo(null);
        day = 0;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        user = new User();
        //mainMenuPanel = new MainMenuPanel(this);
        newUserPanel = new NewUserPanel(this);

        add(createStartPanel());
        setVisible(true);
        setResizable(false);

    }

    public JPanel createStartPanel() {
        startPanel = new JPanel();
        startPanel.setLayout(null);

        constructJLabel(startPrompt, "Click start to begin tracking!", 50);

        constructJButton(1, "Start", 80);
        startBtn.setBackground(buttonBackgroundColor);
        startBtn.setForeground(buttonForegroundColor);
        startBtn.addActionListener(e -> {
            //user = new User();
            //user.setAllFieldsToZero();
//            clearUserData();
//           MainMenuPanel.loadUser();
            day = CALENDER.get(Calendar.DATE);
            switchStartPanelToNewUserPanel();
            //mainMenuPanel = new MainMenuPanel(this);

        });

        constructJLabel(returnPrompt, "Otherwise, click here to continue your journey", 180);

        constructJButton(2, "Continue", 220);
        returnBtn.setBackground(buttonBackgroundColor);
        returnBtn.addActionListener(e -> {
            loadUser();
            switchStartPanelToMainMenuPanel();
        });

        return startPanel;
    }

    public void constructJButton(int type, String text, int height) {

        if (type == 1) {
            startBtn = new JButton(text);
            startPanel.add(startBtn);
            startBtn.setBounds(40, height, 100, 80);
        } else {
            returnBtn = new JButton(text);
            startPanel.add(returnBtn);
            returnBtn.setBounds(40, height, 100, 80);
        }

    }

    public void constructJLabel(JLabel label, String text, int height) {
        label = new JLabel(text);
        startPanel.add(label);
        label.setBounds(40, height, 700, 20);
    }

    public void switchPanelFromStartPanel(JPanel oldPanel, JPanel newPanel) {
        remove(oldPanel);
        add(newPanel);
        setVisible(true);
    }

    public void switchStartPanelToNewUserPanel() {
        remove(startPanel);
        add(newUserPanel);
        setVisible(true);
    }

    public void switchStartPanelToMainMenuPanel() {
        mainMenuPanel = new MainMenuPanel(this);
        remove(startPanel);
        add(mainMenuPanel);
        setVisible(true);
    }

    public void switchNewUserToMainMenuPanel() {
        mainMenuPanel = new MainMenuPanel(this);
        remove(newUserPanel);
        add(mainMenuPanel);
        setVisible(true);
    }

    public void switchMainMenuToNewUserPanel() {
        remove(mainMenuPanel);
        add(newUserPanel);
        setVisible(true);
    }

    private void clearUserData() {
        try {
            jsonWriter.open();
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        }
    }

    public static void main(String[] args) {
        try {
            // Set System L&F
            UIManager.setLookAndFeel(new com.bulenkov.darcula.DarculaLaf());
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        }
        new ProgramFrame();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                saveUser();
            }

            protected void saveUser() {
                try {
                    //MainMenuPanel.getUser().addFood(new Food("rice", 232, MealType.LUNCH, ""));
                    //MainMenuPanel.getUser().addSleep(5);
                    //MainMenuPanel.getUser().drinkWater(4);
                    jsonWriter.open();
                    jsonWriter.write(MainMenuPanel.getUser());
                    jsonWriter.close();
                    System.out.println("File was saved to " + JSON_STORE);

                } catch (FileNotFoundException e) {
                    System.out.println("Unable to save file");
                }
            }
        }, "Shutdown-thread"));
    }

    public static void loadUser() {
        try {
            user = jsonReader.read();
        } catch (IOException e) {
            System.out.println("unable to load data from " + JSON_STORE);
        } catch (JSONException e) {
            ProgramFrame.day = CALENDER.get(Calendar.DATE);
            System.out.println("File is empty");

        }
    }


    public static Integer getDay() {
        return day;
    }

    public static void setDay(int day) {
        ProgramFrame.day = day;
    }

}
