package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RunProgram extends DisplayInfo {

    public RunProgram() {
        super();

        boolean userQuit = false;

        while (!userQuit) {
            System.out.println(displayRandomFacts());
            mainMenuCalorieDisplay();
            mainMenuWaterDisplay();
            loggingDisplay();
            if (RUN_PROGRAM == 0) {
                userQuit = true;
            }
        }

        saveUser();
        System.out.println("Thank you!");
        System.out.println("Come back tomorrow to keep tracking fitness goals and more!");
    }

    public String displayRandomFacts() {

        Scanner scanner = null;
        try {
            File file = new File("facts.txt");
            scanner = new Scanner(file);
            ArrayList<String> facts = new ArrayList<>();
            while (scanner.hasNextLine()) {
                facts.add(scanner.nextLine());
            }
            return "Random fact of the day: " + facts.get((int)(facts.size() * Math.random())) + "\n";

        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        }
        return null;
    }
}
