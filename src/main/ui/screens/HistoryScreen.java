package ui.screens;

import model.History;
import model.TypingPractice;
import ui.TypingApplicationGUI;

import javax.swing.*;
import java.awt.*;

// Represents the centre panel that handles the user's typing history display of the application
public class HistoryScreen extends MainScreen {
    public static final int HISTORY_TITLE_FONT_SIZE = 20;
    public static final int HISTORY_CONTENT_FONT_SIZE = 18;

    // EFFECTS: creates a HistoryScreen with the given typingApplicationGUI
    public HistoryScreen(TypingApplicationGUI typingApplicationGUI) {
        super(typingApplicationGUI);
    }

    // MODIFIES: this
    // EFFECTS: plays the menu audio and constructs a label and panel with the typing history of the user
    //          if the user has no typing history, prompt the user to start a typing practice
    public void loadTypingHistory() {
        playAudio("menuAudio");
        int numRows = 0;
        JPanel typingHistoryViewerPanel = new JPanel();
        typingHistoryViewerPanel.setBackground(MAIN_CONTAINER_COLOR);
        if (history.size() == 0) {
            setupInitialHistoryTexts(typingHistoryViewerPanel);
            numRows = 5;
        } else {
            setupInitialHistoryText(typingHistoryViewerPanel, "You have practiced " + history.size() + " time(s):\n");
            for (int i = 0; i < history.size(); i++) {
                setupRunData(typingHistoryViewerPanel, i);
                numRows++;
            }
            setupAverageData(typingHistoryViewerPanel);
            numRows += 2;
        }
        typingHistoryViewerPanel.setLayout(new GridLayout(numRows, 2));
        typingApplicationGUI.add(typingHistoryViewerPanel);
        typingHistoryViewerPanel.setVisible(true);
        typingHistoryViewerPanel.revalidate();
        typingHistoryViewerPanel.repaint();
    }

    // MODIFIES: typingHIstoryViewerPanel
    // EFFECTS: sets up the instructions for typing history viewer panel for when the user has no previous history
    private void setupInitialHistoryTexts(JPanel typingHistoryViewerPanel) {
        setupInitialHistoryText(typingHistoryViewerPanel,
                "You have no previous typing practice history. Try one out now!");
        setupInitialHistoryText(typingHistoryViewerPanel,
                "If you have just cleared your typing history and would like to retrieve it,");
        setupInitialHistoryText(typingHistoryViewerPanel,
                "then press the LOAD button!");
    }

    // MODIFIES: panel
    // EFFECTS: constructs a label for the first line of typing history and places it inside the given panel
    private void setupInitialHistoryText(JPanel panel, String s) {
        JLabel initialHistoryText = new JLabel(s);
        setLabelFont(initialHistoryText, SIDE_PANEL_FONT_COLOR, HISTORY_TITLE_FONT_SIZE);
        panel.add(initialHistoryText);
    }

    // MODIFIES: typingHistoryViewerPanel
    // EFFECTS: constructs a panel with information of the ith typing practice and adds it to typingHistoryViewerPanel
    private void setupRunData(JPanel typingHistoryViewerPanel, int i) {
        JPanel gridForARun = new JPanel(new GridLayout(5, 1));
        gridForARun.setBackground(MAIN_CONTAINER_COLOR);
        JLabel yourRunNum = new JLabel("Your run #" + (i + 1));
        JLabel optionSelected = new JLabel("Option selected: " + history.getNthTypingPrac(i).getFocus());
        JLabel typingSpeed = new JLabel("Typing Speed (wpm): " + history.getNthTypingPrac(i).getWpm());
        JLabel accuracy = new JLabel("Accuracy (%): " + history.getNthTypingPrac(i).getAccuracy());
        gridForARun.add(yourRunNum);
        gridForARun.add(optionSelected);
        gridForARun.add(typingSpeed);
        gridForARun.add(accuracy);
        setLabelFont(yourRunNum, SIDE_PANEL_FONT_COLOR, HISTORY_CONTENT_FONT_SIZE);
        setLabelFont(optionSelected, SIDE_PANEL_FONT_COLOR, HISTORY_CONTENT_FONT_SIZE);
        setLabelFont(typingSpeed, SIDE_PANEL_FONT_COLOR, HISTORY_CONTENT_FONT_SIZE);
        setLabelFont(accuracy, SIDE_PANEL_FONT_COLOR, HISTORY_CONTENT_FONT_SIZE);
        typingHistoryViewerPanel.add(gridForARun);
    }

    // MODIFIES: typingHistoryViewerPanel
    // EFFECTS: constructs a panel with information about the user's average wpm and accuracy
    //          and adds it to tpingHistoryViewerPanel
    private void setupAverageData(JPanel typingHistoryViewerPanel) {
        JPanel gridForAvgResult = new JPanel(new GridLayout(4, 1));
        gridForAvgResult.setBackground(MAIN_CONTAINER_COLOR);
        JLabel avgWpm = new JLabel("Your average typing speed is " + history.calculateAverageTypingSpeed()
                + " words per minute.");
        JLabel avgAcc = new JLabel("Your average accuracy is "
                + history.calculateAverageAccuracy() + "%.");
        gridForAvgResult.add(avgWpm);
        gridForAvgResult.add(avgAcc);
        setLabelFont(avgWpm, SIDE_PANEL_FONT_COLOR, HISTORY_CONTENT_FONT_SIZE);
        setLabelFont(avgAcc, SIDE_PANEL_FONT_COLOR, HISTORY_CONTENT_FONT_SIZE);
        typingHistoryViewerPanel.add(gridForAvgResult);
    }

    // MODIFIES: this
    // EFFECTS: adds the given typingPractice to history
    public void saveData(TypingPractice typingPractice) {
        history.addUserHistory(typingPractice);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new empty history
    public void clearData() {
        history = new History();
    }
}
