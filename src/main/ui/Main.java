package ui;


public class Main {

    //REQUIRES: before running the console ui,
    // remember to comment out the calls to toJson.ProgramFrame.getDay() in the User class,
    // and the addSleepAndTime.ProgramFrame.getDay() in the JsonReader class. Replace them with calls
    // to DisplayInfo.setDay(day)
    public static void main(String[] args) {
        new RunProgram();
    }
}