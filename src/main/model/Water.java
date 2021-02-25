package model;

//this class constructs a water object, to which more water can
//be added, as long as it is below the daily requirement
public class Water {

    private static final int DAILY_REQUIREMENT = 8;
    private int amountConsumed;

    //MODIFIES: this
    //EFFECTS: initializes the amount consumed
    public Water() {
        amountConsumed = 0;
    }

    //MODIFIES: this
    //EFFECTS: increments the amount consumed as long as it is less
    //      than the daily requirement limit
    public void incrementWater() {
        if (getAmountConsumed() < DAILY_REQUIREMENT) {
            amountConsumed++;
        }
    }

    public int getDailyRequirement() {
        return DAILY_REQUIREMENT;
    }

    public int getAmountConsumed() {
        return amountConsumed;
    }

    //MODIFIES: this
    //EFFECTS: increments the amount consumed by the number of cups in
    //      the parameter
    public void setAmountConsumed(int cups) {
        amountConsumed += cups;
    }

}
