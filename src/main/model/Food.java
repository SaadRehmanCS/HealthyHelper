package model;

public class Food {

    private String name;
    private int totalCalories;
    private String mealType;

    public Food(String name, int totalCalories, String mealType) {
        this.name = name;
        this.totalCalories = totalCalories;
        this.mealType = mealType;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

}
