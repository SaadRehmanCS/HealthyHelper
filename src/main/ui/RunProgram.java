package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

//Represents the flow of method calls while the program is running
public class RunProgram extends DisplayInfo {

    //EFFECTS: keeps the program running in a while loop
    public RunProgram() {
        super();

        boolean userQuit = false;

        while (!userQuit) {
            beginNewDayProtocol(day);
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
        System.out.println("Come back soon to keep tracking fitness goals and more!");
    }

    //MODIFIES: this, User
    //EFFECTS: at the start of the next day, it sets all User fields to 0 or empty
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

    //EFFECTS: displays random fun facts from facts.txt file
    public String displayRandomFacts() {

        Scanner scanner;
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
