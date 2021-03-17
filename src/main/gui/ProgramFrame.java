package gui;

import model.DietPlan;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Calendar;
import java.util.TimeZone;

public class ProgramFrame extends JFrame {

    private static final String JSON_STORE = "./data/user.json";
    JPanel startPanel;
    JLabel startPrompt;
    JButton startBtn;
    JLabel returnPrompt;
    JButton returnBtn;
    JsonWriter jsonWriter;
    JsonReader jsonReader;
    protected static int day;
    protected static final Calendar CALENDER = Calendar.getInstance(TimeZone.getDefault());




    public ProgramFrame() {
        super("Healthy Helper");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(600, 600));
        setLocationRelativeTo(null);
        setImageIcon();

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
        startBtn.addActionListener(e -> {
            clearUserData();
            day = CALENDER.get(Calendar.DATE);
            NewUserPanel newUserPanel = new NewUserPanel();
            replacePanel(startPanel, newUserPanel);
        });

        constructJLabel(returnPrompt, "Otherwise, click here to continue your journey", 180);

        constructJButton(2, "Continue", 220);
        returnBtn.addActionListener(e -> {
            //replacePanel(startPanel, new NewUserPanel());
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

    public void replacePanel(JPanel oldPanel, JPanel newPanel) {
        remove(oldPanel);
        add(newPanel);
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
        new ProgramFrame();
    }

}
