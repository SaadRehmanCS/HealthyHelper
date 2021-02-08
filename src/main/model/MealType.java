package model;

public class MealType {


    private String mealType;

    public MealType(String mealType) {

        switch (mealType) {
            case "lunch": addLunch();
            break;
            case "breakfast": addBreakfast();
            break;
            case "dinner": addDinner();
            break;
            case "snack": addSnack();
            break;
        }

    }

    public void addLunch(){}

    public void addBreakfast(){}

    public void addDinner(){}

    public void addSnack(){}

    public void getMealType() {}



}
