package ui.screens;

import ui.TypingApplication;

public class MainScreen extends Screen {
    private TypingApplication typingApplication;

    public MainScreen(TypingApplication typingApplication) {
        this.typingApplication = typingApplication;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void load() {
        System.out.println("insert main screen here >:)");
    }
}
