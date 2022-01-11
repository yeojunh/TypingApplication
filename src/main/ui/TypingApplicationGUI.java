package ui;

import ui.screens.HistoryScreen;
import ui.screens.MainScreen;
import ui.screens.TitleScreen;
import ui.screens.TypingScreen;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CountDownLatch;

// Typing practice application with Graphical User Interface using Java Swing
public class TypingApplicationGUI extends JFrame {
    private static final int MIN_WIDTH = 1250;
    private static final int MIN_HEIGHT = 850;
    private TitleScreen titleScreen;
    private MainScreen mainScreen;
    private ui.screens.TypingScreen typingScreen;
    private HistoryScreen historyScreen;

    // "type anything to continue" in titleScreen
    // https://stackoverflow.com/questions/19025366/wait-until-boolean-value-changes-it-state
    public final CountDownLatch latch = new CountDownLatch(1);

    // EFFECTS: initializes the components in typing application
    //          throws InterruptedException if interrupted at title screen's "wait for key press"
    public TypingApplicationGUI() throws InterruptedException {
        initializeTypingApplication();
    }

    // MODIFIES: this
    // EFFECTS: initializes necessary typing application components and JFrame, and starts the application
    public void initializeTypingApplication() throws InterruptedException {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Typing Application 2021 :)");
        setResizable(false);
        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        setLocationRelativeTo(null);

        initializeWelcome();
        titleScreen.load();
        latch.await();
        initializeMain();
        initializeTyping();
        initializeHistory();
        pack();
    }

    // MODIFIES: this
    // EFFECTS: initializes welcome and main screens, sets up title screen
    private void initializeWelcome() {
        titleScreen = new TitleScreen(this);
        mainScreen = new MainScreen(this);
        titleScreen.initialize();
    }

    // MODIFIES: this
    // EFFECTS: initializes and sets up main screen
    private void initializeMain() {
        mainScreen.initialize();
    }

    // MODIFIES: this
    // EFFECTS: initializes and sets up typing screen
    private void initializeTyping() {
        typingScreen = new TypingScreen(this);
        typingScreen.initialize();
    }

    // MODIFIES: this
    // EFFECTS: initializes and sets up history screen
    private void initializeHistory() {
        historyScreen = new HistoryScreen(this);
        historyScreen.initialize();
    }

    // MODIFIES: this
    // EFFECTS: runs loadRegularTyping() in TypingScreen
    public void loadRegularTyping() {
        typingScreen.loadRegularTyping();
    }

    // MODIFIES: this
    // EFFECTS: runs loadShortTyping() in TypingScreen
    public void loadShortTyping() {
        typingScreen.loadShortTyping();
    }

    // MODIFIES: this
    // EFFECTS: runs loadPunctuationTyping() in TypingScreen
    public void loadPunctuationTyping() {
        typingScreen.loadPunctuationTyping();
    }

    // MODIFIES: this
    // EFFECTS: runs loadNumberTyping() in TypingScreen
    public void loadNumberTyping() {
        typingScreen.loadNumberTyping();
    }

    // MODIFIES: this
    // EFFECTS: runs loadTypingHistory() in TypingScreen
    public void loadTypingHistory() {
        historyScreen.loadTypingHistory();
    }

    // MODIFIES: this
    // EFFECTS: runs clearData() in TypingScreen
    public void clearTypingHistory() {
        historyScreen.clearData();
    }

    // getters
    public TypingScreen getTypingScreen() {
        return typingScreen;
    }

    public HistoryScreen getHistoryScreen() {
        return historyScreen;
    }
}
