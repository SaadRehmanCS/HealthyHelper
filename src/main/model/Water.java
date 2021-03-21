package model;


//this class constructs a water object, to which more water can
//be added, as long as it is below the daily requirement
public class Water {

    private static final int DAILY_REQUIREMENT = 8;
    private int cupsConsumed;

    //MODIFIES: this
    //EFFECTS: initializes the amount consumed
    public Water() {
        cupsConsumed = 0;
    }

    //MODIFIES: this
    //EFFECTS: increments the amount consumed as long as it is less
    //      than the daily requirement limit
    public void incrementWater() {
        if (getCupsConsumed() <= DAILY_REQUIREMENT) {
            cupsConsumed++;
        }
    }

    public int getDailyRequirement() {
        return DAILY_REQUIREMENT;
    }

    public int getCupsConsumed() {
        return cupsConsumed;
    }

    //MODIFIES: this
    //EFFECTS: increments the amount consumed by the number of cups in
    //      the parameter
    public void setCupsConsumed(int cups) {
        cupsConsumed = cups;
    }

}
