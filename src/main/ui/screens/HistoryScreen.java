package ui.screens;

import model.Record;
import model.TypingPractice;
import ui.TypingApplication;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HistoryScreen extends MainScreen {
    private JPanel typingHistoryPanel;

    public HistoryScreen(TypingApplication typingApplication) {
        super(typingApplication);
        typingHistoryPanel = new JPanel();
    }

//    public void loadTypingHistory() {
//        clearTypingHistoryPanel();
//        typingHistoryPanel.setVisible(true);
//        record.getUserHistory();
//        String loadResult;
//        if (record.size() == 0) {
//            loadResult = "You have no previous typing practice history. Try one out now!";
//        } else {
//            loadResult = "You have practiced " + record.size() + " time(s).\n\n";
//            for (int i = 0; i < record.size(); i++) {
//                loadResult += "Your run #" + (i + 1) + "\n  Option selected: "
//                        + record.getNthTypingPrac(i).getFocus() + "\n  Typing Speed (wpm):  "
//                        + record.getNthTypingPrac(i).getWpm() + "\n  Accuracy (%): "
//                        + record.getNthTypingPrac(i).getAccuracy() + "\n";
//            }
//            loadResult += "\nYour average typing speed is " + record.calculateAverageTypingSpeed()
//                    + " words per minute.\n";
//            loadResult += "Your average accuracy is " + record.calculateAverageAccuracy() + "%.";
//        }
//        setupTypingHistoryPanel(loadResult);
//        System.out.println(loadResult);
//    }

    // redo but with labels instead of TypingArea
    public void loadTypingHistory() {
        int numRows = 0;
        JPanel typingHistoryViewerPanel = new JPanel();
        typingHistoryViewerPanel.setBackground(MAINCONTAINER_COLOR);
        record.getUserHistory();
        if (record.size() == 0) {
            setupInitialHistoryText(typingHistoryViewerPanel,
                    "You have no previous typing practice history. Try one out now!");
            numRows = 1;
        } else {
            setupInitialHistoryText(typingHistoryViewerPanel, "You have practiced " + record.size() + " time(s)\n");
            for (int i = 0; i < record.size(); i++) {
                setupRunData(typingHistoryViewerPanel, i);
                numRows++;
            }
            setupAverageData(typingHistoryViewerPanel);
            numRows += 2;
        }
        typingHistoryViewerPanel.setLayout(new GridLayout(numRows, 2));
        typingApplication.add(typingHistoryViewerPanel);
        typingHistoryViewerPanel.setVisible(true);
        typingHistoryViewerPanel.revalidate();
        typingHistoryViewerPanel.repaint();
    }

    private void setupInitialHistoryText(JPanel panel, String s) {
        JLabel initialHistoryText = new JLabel(s);
        setLabelFont(initialHistoryText, SIDEPANEL_FONT_COLOR, 15);
        panel.add(initialHistoryText);
    }

    private void setupRunData(JPanel typingHistoryViewerPanel, int i) {
        JPanel gridForARun = new JPanel(new GridLayout(5, 1));
        gridForARun.setBackground(MAINCONTAINER_COLOR);
        JLabel yourRunNum = new JLabel("Your run #" + (i + 1));
        JLabel optionSelected = new JLabel("Option selected: " + record.getNthTypingPrac(i).getFocus());
        JLabel typingSpeed = new JLabel("Typing Speed (wpm): " + record.getNthTypingPrac(i).getWpm());
        JLabel accuracy = new JLabel("Accuracy (%): " + record.getNthTypingPrac(i).getAccuracy());
        gridForARun.add(yourRunNum);
        gridForARun.add(optionSelected);
        gridForARun.add(typingSpeed);
        gridForARun.add(accuracy);
        setLabelFont(yourRunNum, SIDEPANEL_FONT_COLOR, 12);
        setLabelFont(optionSelected, SIDEPANEL_FONT_COLOR, 12);
        setLabelFont(typingSpeed, SIDEPANEL_FONT_COLOR, 12);
        setLabelFont(accuracy, SIDEPANEL_FONT_COLOR, 12);
        typingHistoryViewerPanel.add(gridForARun);
    }

    private void setupAverageData(JPanel typingHistoryViewerPanel) {
        JPanel gridForAvgResult = new JPanel(new GridLayout(4, 1));
        gridForAvgResult.setBackground(MAINCONTAINER_COLOR);
        JLabel avgWpm = new JLabel("Your average typing speed is " + record.calculateAverageTypingSpeed()
                + " words per minute.");
        JLabel avgAcc = new JLabel("Your average accuracy is "
                + record.calculateAverageAccuracy() + "%.");
        gridForAvgResult.add(avgWpm);
        gridForAvgResult.add(avgAcc);
        setLabelFont(avgWpm, SIDEPANEL_FONT_COLOR, 12);
        setLabelFont(avgAcc, SIDEPANEL_FONT_COLOR, 12);
        typingHistoryViewerPanel.add(gridForAvgResult);
    }

    private void setupTypingHistoryPanel(String loadResult) {
        JTextArea typingHistoryTextArea = new JTextArea();
        typingHistoryTextArea.setText(loadResult);
        typingHistoryPanel.add(typingHistoryTextArea);
        typingHistoryTextArea.setVisible(true);
        typingHistoryTextArea.setFocusable(false);
        typingHistoryPanel.setBackground(MAINCONTAINER_COLOR);
        typingHistoryTextArea.setFont(new Font(typingHistoryTextArea.getFont().toString(), Font.PLAIN, 16));
        typingHistoryTextArea.setForeground(SIDEPANEL_FONT_COLOR);
        typingHistoryTextArea.setBackground(MAINCONTAINER_COLOR);
        typingHistoryPanel.setVisible(true);
        typingApplication.getTypingScreen().clearScreen();
        typingApplication.add(typingHistoryPanel);
        typingHistoryPanel.revalidate();
        typingHistoryPanel.repaint();
        typingApplication.revalidate();
        typingApplication.repaint();
    }

    // saves data to Record and returns String so it can used in TypingScreen for a good use (displays it below buttons)
    public String saveData(TypingPractice typingPractice) {
        record.addUserHistory(typingPractice);
        return "Saved successfully!";
    }

    // todo: need to be revised, i just copied this from TypingApp
    public void saveDataToJson() {
        typingApplication.add(new JLabel(writeToJson()));
    }

    public String writeToJson() {
        try {
            jsonWriter.open();
            jsonWriter.write(record);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            return "Unable to write to file: " + JSON_STORE;
        }
        return "Saved current history to " + JSON_STORE;
    }

    public void clearData() {
        record = new Record();
        System.out.println("pretend that the app successfully cleared the data");
    }
}
