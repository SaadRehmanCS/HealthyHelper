package gui;

import model.User;
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

    private static final String JSON_STORE = "./data/user.json";
    public static final Color buttonBackgroundColor = Color.CYAN;
    public static final Color buttonForegroundColor = Color.CYAN;
    JPanel startPanel;
    JLabel startPrompt;
    JButton startBtn;
    JLabel returnPrompt;
    JButton returnBtn;
    static JsonWriter jsonWriter;
    JsonReader jsonReader;
    static Integer day;
    protected static final Calendar CALENDER = Calendar.getInstance(TimeZone.getDefault());
    NewUserPanel newUserPanel;
    MainMenuPanel mainMenuPanel;
    User user;


    public ProgramFrame() {
        super("Healthy Helper");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(600, 600));
        setLocationRelativeTo(null);
        setImageIcon();
        day = 0;
        mainMenuPanel = new MainMenuPanel(this);
        newUserPanel = new NewUserPanel(this);
        user = MainMenuPanel.getUser();

        add(createStartPanel());
        setVisible(true);
        setResizable(false);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    public void setImageIcon() {
        ImageIcon icon;
        URL imgURL = ProgramFrame.class.getResource("tobs.jpg");
        if (imgURL != null) {
            icon = new ImageIcon(imgURL);
            setIconImage(icon.getImage());
        } else {
            JOptionPane.showMessageDialog(this, "Icon image not found.");
        }
    }

    public JPanel createStartPanel() {
        startPanel = new JPanel();
        startPanel.setLayout(null);

        constructJLabel(startPrompt, "Click start to begin tracking!", 50);

        constructJButton(1, "Start", 80);
        startBtn.setBackground(buttonBackgroundColor);
        startBtn.setForeground(buttonForegroundColor);
        startBtn.addActionListener(e -> {
            clearUserData();
            day = CALENDER.get(Calendar.DATE);
            switchPanelFromStartPanel(startPanel, newUserPanel);
        });

        constructJLabel(returnPrompt, "Otherwise, click here to continue your journey", 180);

        constructJButton(2, "Continue", 220);
        returnBtn.setBackground(buttonBackgroundColor);
        returnBtn.addActionListener(e -> {
            loadUser();
            switchPanelFromStartPanel(startPanel, mainMenuPanel);
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

    public void switchNewUserToMainMenuPanel() {
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

    private void loadUser() {
        try {
            user = jsonReader.read();
        } catch (IOException e) {
            System.out.println("unable to load data from " + JSON_STORE);
        } catch (JSONException e) {
            day = CALENDER.get(Calendar.DATE);
            System.out.println("File is empty");

        }
    }

    public static Integer getDay() {
        return day;
    }

}
