package ui;

import persistence.JsonReader;
import persistence.JsonWriter;
import ui.screens.HistoryScreen;
import ui.screens.MainScreen;
import ui.screens.WelcomeScreen;
import ui.screens.TypingScreen;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

// Graphical User Interface Implementation with Swing
// Familiarizing Swing's GUI before actual implementation
public class TypingApplicationGUI extends JFrame {
    private static final int MIN_WIDTH = 1250;
    private static final int MIN_HEIGHT = 700;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/record.json";
    private JFrame frame;

    private WelcomeScreen welcomeScreen;
    private MainScreen mainScreen;
    private ui.screens.TypingScreen typingScreen;
    private HistoryScreen historyScreen;

    // https://stackoverflow.com/questions/19025366/wait-until-boolean-value-changes-it-state
    public final CountDownLatch latch = new CountDownLatch(1);      // "type anything to continue" from welcomeScreen

    public TypingApplicationGUI() throws InterruptedException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initializeTypingApplication();
    }

    // effects for runTyping(): while the app is on, keeps the typing test going
    //          and prompts the user to choose an option
    public void initializeTypingApplication() throws InterruptedException {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bread Nut Typing App Pro :)");
        setResizable(false);
//        setPreferredSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
//        setMaximumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        initializeWelcome();
        welcomeScreen.load();       // runTyping();
        latch.await();
        initializeMain();
        initializeTyping();
        initializeHistory();
        pack(); // basically "packs" it all together
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null); // this line makes the window centered (only mainScreen though)

        mainScreen.load();
    }



    private void initializeWelcome() {
        welcomeScreen = new WelcomeScreen(this);
        mainScreen = new MainScreen(this);
        welcomeScreen.initialize();
    }

    private void initializeMain() {
        mainScreen.initialize();
    }

    private void initializeTyping() {
        typingScreen = new TypingScreen(this);
        typingScreen.initialize();
    }

    private void initializeHistory() {
        historyScreen = new HistoryScreen(this);
        historyScreen.initialize();

    }

    // getters
    public TypingScreen getTypingScreen() {
        return typingScreen;
    }

    public HistoryScreen getHistoryScreen() {
        return historyScreen;
    }
}
