package gui;


import model.User;
import org.json.JSONException;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;

//this class is the frame behind which all the panels will go
public class ProgramFrame extends JFrame {

    public static final String JSON_STORE = "./data/user.json";
    public static final Color buttonBackgroundColor = Color.CYAN;
    public static final Color buttonForegroundColor = Color.CYAN;
    public static final Color buttonBackgroundColorUnfavourable = Color.lightGray;
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
    private JPanel foodHistoryPanel;


    //MODIFIES: this
    //EFFECTS: creates a new frame, sets default settings, and adds new panel
    public ProgramFrame() {
        super("Healthy Helper");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(600, 600));
        setLocationRelativeTo(null);
        day = 0;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        user = new User();
        newUserPanel = new NewUserPanel(this);
        foodHistoryPanel = new FoodHistoryPanel(this);

        add(createStartPanel());
        setVisible(true);
        setResizable(false);

    }

    //MODIFIES: this
    //EFFECTS: creates a new start panel
    public JPanel createStartPanel() {
        startPanel = new JPanel();
        startPanel.setLayout(null);

        constructJLabel(startPrompt, "Click start to begin tracking!", 200, 80);

        constructJButton(1, "Start", 130);
        startBtn.setBackground(buttonBackgroundColor);
        startBtn.setForeground(buttonForegroundColor);
        startBtn.addActionListener(e -> {
            playSound("data/button_click.wav");
            day = CALENDER.get(Calendar.DATE);
            switchStartPanelToNewUserPanel();
        });

        if (!jsonFileIsEmpty()) {
            constructJLabel(returnPrompt, "You have previously saved data. Continue?", 180, 350);

            constructJButton(2, "Continue", 400);
            returnBtn.setBackground(buttonBackgroundColor);
            returnBtn.addActionListener(e -> {
                playSound("data/button_click.wav");
                loadUser();
                switchStartPanelToMainMenuPanel();
            });
        }
        return startPanel;
    }

    //MODIFIES: this
    //EFFECTS: creates the buttons for start or continue program
    public void constructJButton(int type, String text, int height) {

        if (type == 1) {
            startBtn = new JButton(text);
            startPanel.add(startBtn);
            startBtn.setBounds(240, height, 100, 80);
        } else {
            returnBtn = new JButton(text);
            startPanel.add(returnBtn);
            returnBtn.setBounds(240, height, 100, 80);
        }

    }

    //MODIFIES: this
    //EFFECTS: creates new labels
    public void constructJLabel(JLabel label, String text, int x, int y) {
        label = new JLabel(text);
        startPanel.add(label);
        label.setBounds(x, y, 700, 20);
    }

    //EFFECTS: switch panels from the frame
    public void switchPanelFromStartPanel(JPanel oldPanel, JPanel newPanel) {
        remove(oldPanel);
        add(newPanel);
        setVisible(true);
    }

    //EFFECTS: switch panels from the frame
    public void switchStartPanelToNewUserPanel() {
        remove(startPanel);
        add(newUserPanel);
        setVisible(true);
    }

    //EFFECTS: switch panels from the frame
    public void switchStartPanelToMainMenuPanel() {
        mainMenuPanel = new MainMenuPanel(this);
        remove(startPanel);
        add(mainMenuPanel);
        setVisible(true);
    }

    //EFFECTS: switch panels from the frame
    public void switchNewUserToMainMenuPanel() {
        mainMenuPanel = new MainMenuPanel(this);
        remove(newUserPanel);
        add(mainMenuPanel);
        setVisible(true);
    }

    //EFFECTS: switch panels from the frame
    public void switchMainMenuToFoodHistoryPanel() {
        remove(mainMenuPanel);
        foodHistoryPanel = new FoodHistoryPanel(this);
        add(foodHistoryPanel);
        setVisible(true);
    }

    //EFFECTS: switch panels from the frame
    public void switchFoodHistoryToMainMenuPanel() {
        remove(foodHistoryPanel);
        mainMenuPanel = new MainMenuPanel(this);
        add(mainMenuPanel);
        setVisible(true);
    }

    //EFFECTS: switch panels from the frame
    public void switchMainMenuToNewUserPanel() {
        remove(mainMenuPanel);
        add(newUserPanel);
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: clears all user data
    private void clearUserData() {
        try {
            jsonWriter.open();
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        }
    }

    //MODIFIES: this
    //EFFECTS: when the app is closed, it will save user data to file
    public static void saveDataOnClose() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                saveUser();
            }

            protected void saveUser() {
                try {
                    //MainMenuPanel.getUser().addFood(new Food("rice", 232, MealType.LUNCH, ""));
                    //MainMenuPanel.getUser().addSleep(5);
                    //MainMenuPanel.getUser().drinkWater(4);
                    if (MainMenuPanel.getUser() == null) {
                        System.out.println("User closed program too early");
                    } else {
                        jsonWriter.open();
                        jsonWriter.write(MainMenuPanel.getUser());
                        jsonWriter.close();
                        System.out.println("File was saved to " + JSON_STORE);
                    }

                } catch (FileNotFoundException e) {
                    System.out.println("Unable to save file");
                }
            }
        }, "Shutdown-thread"));
    }

    //EFFECTS: sets the look and feel for Java swing
    public static void setLookAndFeel() {
        try {
            // Set System L&F
            UIManager.setLookAndFeel(new com.bulenkov.darcula.DarculaLaf());
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        }
    }

    //EFFECTS: checks to see if the file is empty
    public boolean jsonFileIsEmpty() {
        try {
            jsonReader.read();
        } catch (IOException e) {
            //
        } catch (JSONException e) {
            return true;
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: loads in the old user
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

    //EFFECTS: plays a sound from a given sound file
    public static void playSound(String soundFile) {
        File f = new File("./" + soundFile);
        AudioInputStream audioIn = null;
        try {
            audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public static Integer getDay() {
        return day;
    }

    public static void setDay(int day) {
        ProgramFrame.day = day;
    }

}
