package ui.screens;

import ui.TypingApplication;

public class HistoryScreen extends MainScreen {
    private TypingApplication typingApplication;

    public HistoryScreen(TypingApplication typingApplication) {
        super(typingApplication);
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
