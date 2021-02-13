package model;

//this class constructs a water object
public class Water {

    private static final int DAILY_REQUIREMENT = 8;
    private int amountConsumed;

    public Water() {
        amountConsumed = 0;
    }

    public void incrementWater() {
        if (getAmountConsumed() < DAILY_REQUIREMENT) {
            amountConsumed++;
        }
    }

    public int getAmountConsumed() {
        return amountConsumed;
    }

    public void setAmountConsumed(int cups) {
        amountConsumed += cups;
    }

}
