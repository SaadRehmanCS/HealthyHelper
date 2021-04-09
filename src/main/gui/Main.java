package gui;

public class Main extends ProgramFrame {

    //EFFECTS: starts the program
    public static void main(String[] args) {
        setLookAndFeel();
        new ProgramFrame();
        saveDataOnClose();

    }
}
