package model;

public class Sleep {

    private static final double DAILY_HOURS = 8;
    private double sleepTime;

    public Sleep() {
        sleepTime = 0;
    }

    public double getSleepTime() {
        return sleepTime;
    }

    //MODIFIES: this
    //EFFECTS: increments sleep time by the additional amount
    public void addSleepTime(double sleepTime) {
        this.sleepTime += sleepTime;
    }

    //REQUIRES: addSleepTime() is called before it
    //EFFECTS: returns String advice for user based on how long they slept
    public String sleepAssessment() {
        if (sleepTime < DAILY_HOURS) {
            return "You were not able to get enough sleep. Try to go to bed a little early tonight";
        } else if (sleepTime > DAILY_HOURS) {
            return "You slept longer than necessary. This is great, but be more resourceful with time!";
        } else {
            return "Great! You slept just the right amount";
        }

    }
}
