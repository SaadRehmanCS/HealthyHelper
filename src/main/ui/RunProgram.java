package ui;

public class RunProgram extends DisplayInfo {

    public RunProgram() {
        super();

        boolean userQuit = false;

        while (!userQuit) {
            mainMenuCalorieDisplay();
            mainMenuWaterDisplay();
            loggingDisplay();
            if (RUN_PROGRAM == 0) {
                userQuit = true;
            }
        }
        System.out.println("Thank you!");
        System.out.println("Come back tomorrow to keep tracking fitness goals and more!");
    }
}
