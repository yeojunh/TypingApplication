package ui.screens;

import ui.TypingApplication;

import java.awt.event.ActionEvent;

public class TypingScreen extends Screen {
    private TypingApplication typingApplication;

    public TypingScreen(TypingApplication typingApplication) {
        this.typingApplication = typingApplication;
    }

    @Override
    public void initialize() {
//        System.out.println("insert typing setup here...");
    }

    @Override
    public void load() {
        System.out.println("pretend that the typing setup is visible now");
    }

    public void loadRegularTyping() {
        System.out.println("pretend that the regular typing screen loaded");
    }

    public void loadShortTyping() {
        System.out.println("pretend that the short typing screen loaded");
    }

    public void loadPunctuationTyping() {
        System.out.println("pretend that the punctuation typing screen loaded");
    }

    public void loadNumberTyping() {
        System.out.println("pretend that the number typing screen loaded");
    }

    public void loadTypingHistory() {
        System.out.println("pretend that the app successfully loaded local typing history");
    }

    public void saveData() {
        System.out.println("pretend that the app successfully saved the file");
    }

    public void loadData() {
        System.out.println("pretend that the app successfully loaded the file");
    }

    public void clearData() {
        System.out.println("pretend that the app successfully cleared the data");
    }
}
