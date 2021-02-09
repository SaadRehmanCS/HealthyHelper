package ui;

import java.util.Scanner;

public class RunProgram extends DisplayInfo {

    public RunProgram() {
        super();

        boolean userQuit = false;

        Scanner input = new Scanner(System.in);

        while (!userQuit) {
            calorieTargetDisplay();
            loggingDisplay();
            System.out.println("Would you like to continue? 1/0");
            int finalInput = input.nextInt();
            if (finalInput == 0) {
                userQuit = true;
            }
        }
    }
}
