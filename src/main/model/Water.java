package model;


public class Water {

    private static final int DAILY_REQUIREMENT = 8;
    private int amountConsumed;

    public Water() {
        amountConsumed = 0;
    }

    public void incrementWater() {
        if (getAmountConsumed() < 20) {
            amountConsumed++;
        }
    }

    public int getAmountConsumed() {
        return amountConsumed;
    }

    public int getCurrentRequirement() {
        return 0;
    }

}
