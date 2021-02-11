package model;

public class MealType {


    private String mealType;

    public MealType(String mealType) {

        switch (mealType) {
            case "lunch":
                addLunch();
                break;
            case "breakfast":
                addBreakfast();
                break;
            case "dinner":
                addDinner();
                break;
            default:
                addSnack();
                break;
        }

    }

    public void addLunch() {
        mealType = "lunch";
    }

    public void addBreakfast() {
        mealType = "breakfast";
    }

    public void addDinner() {
        mealType = "dinner";
    }

    public void addSnack() {
        mealType = "snack";
    }

    public String getMealType() {
        return mealType;
    }



}
