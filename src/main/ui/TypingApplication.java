package ui;

import persistence.JsonReader;
import persistence.JsonWriter;
import ui.screens.HistoryScreen;
import ui.screens.MainScreen;
import ui.screens.WelcomeScreen;
import ui.screens.TypingScreen;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


// Familiarizing Swing's GUI before actual implementation
public class TypingApplication extends JFrame {
    private static final int SCREEN_WIDTH = 1600;
    private static final int SCREEN_HEIGHT = 1000;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/record.json";

    private WelcomeScreen welcomeScreen;
    private MainScreen mainScreen;
    private ui.screens.TypingScreen typingScreen;
    private HistoryScreen historyScreen;
    private boolean appOn = true;

    public TypingApplication() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new FlowLayout());

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        initializeWelcome();
        initializeMain();
        initializeTyping();
        initializeHistory();
        pack(); // basically displays the item
        setLocationRelativeTo(null);
        setVisible(false);
        setResizable(true);

        runTyping();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: while the app is on, keeps the typing test going
    //          and prompts the user to choose an option 
    public void runTyping() {
        // initial setup for the GUI screen
//        JButton btn = new JButton("Test");
//        btn.setActionCommand("Start Regular Practice");
//        add(btn);

        welcomeScreen.load();
        mainScreen.load();
        setVisible(true);

//        while (appOn) {
//            // todo
//
//        }
    }

    private void initializeWelcome() {
        welcomeScreen = new WelcomeScreen(this);
        welcomeScreen.initialize();
    }

    private void initializeMain() {
        mainScreen = new MainScreen(this);
        mainScreen.initialize();
        // some code here
        // mainScreen.setVisible(false);
    }

    private void initializeTyping() {
        typingScreen = new TypingScreen(this);
        typingScreen.initialize();
        // some code here
        // mainScreen.setVisible(false);
    }

    private void initializeHistory() {
        historyScreen = new HistoryScreen(this);
        historyScreen.initialize();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Start Regular Practice")) {
            System.out.println("Starting regular practice!");
        }
    }


    // getters
    public WelcomeScreen getWelcomeScreen() {
        return welcomeScreen;
    }

    public MainScreen getMainScreen() {
        return mainScreen;
    }

    public TypingScreen getTypingScreen() {
        return typingScreen;
    }

    public HistoryScreen getHistoryScreen() {
        return historyScreen;
    }
}
