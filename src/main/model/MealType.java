package model;

//an object of this type simply specifies
//the meal type possessed by a food item.
public class MealType {

    private String mealType;

    //EFFECTS: depending on the user-selected meal type,
    //     calls the appropriate setter. If no string matches
    //     the cases, default setter for snack is called
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

    //MODIFIES: this
    //EFFECTS: adds lunch
    public void addLunch() {
        mealType = "lunch";
    }

    //MODIFIES: this
    //EFFECTS: adds breakfast
    public void addBreakfast() {
        mealType = "breakfast";
    }

    //MODIFIES: this
    //EFFECTS: adds dinner
    public void addDinner() {
        mealType = "dinner";
    }

    //MODIFIES: this
    //EFFECTS: adds snack
    public void addSnack() {
        mealType = "snack";
    }

    public String getMealType() {
        return mealType;
    }



}
