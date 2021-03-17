package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class ProgramFrame extends JFrame {

    JPanel startPanel;
    JLabel startPrompt;
    JButton startBtn;
    JLabel returnPrompt;
    JButton returnBtn;

    public ProgramFrame() {
        super("Healthy Helper");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(600, 600));
        setLocationRelativeTo(null);
        setImageIcon();

        add(createStartPanel());
        setVisible(true);
        setResizable(false);
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
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                replacePanel(startPanel, new NewUserPanel());
            }
        });

        constructJLabel(returnPrompt, "Otherwise, click here to continue your journey", 180);

        constructJButton(2, "Continue", 220);
        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //replacePanel(startPanel, new NewUserPanel());
            }
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

    public static void main(String[] args) {
        new ProgramFrame();
    }

}
