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

    //REQUIRES: sleep time is nonnegative and less than 12
    public void addSleepTime(double sleepTime) {
        this.sleepTime += sleepTime;
    }

    //REQUIRES: addSleepTime() is called before it
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
