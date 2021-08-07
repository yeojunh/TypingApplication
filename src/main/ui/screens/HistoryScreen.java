package ui.screens;

import model.Record;
import model.TypingPractice;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.TypingApplication;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

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
//        clearTypingHistoryPanel();
//        typingApplication.getTypingScreen().clearScreen();
        typingHistoryPanel.setVisible(true);

        JPanel newTypingHistoryPanel = new JPanel();        // todo: change this to boxLayout
//        newTypingHistoryPanel.setLayout(new GridLayout(100, 1));
        record.getUserHistory();
        if (record.size() == 0) {
            newTypingHistoryPanel.add(new JLabel("You have no previous typing practice history. Try one out now!"));
        } else {
            newTypingHistoryPanel.add(new JLabel("You have practiced " + record.size() + " time(s).\n\n"));
            for (int i = 0; i < record.size(); i++) {
                newTypingHistoryPanel.add(new JLabel("Your run #" + (i + 1)));
                newTypingHistoryPanel.add(new JLabel("Option selected: " + record.getNthTypingPrac(i).getFocus()));
                newTypingHistoryPanel.add(new JLabel("Typing Speed (wpm): " + record.getNthTypingPrac(i).getWpm()));
                newTypingHistoryPanel.add(new JLabel("Accuracy (%): " + record.getNthTypingPrac(i).getAccuracy()));
            }
            newTypingHistoryPanel.add(new JLabel("Your average typing speed is " + record.calculateAverageTypingSpeed()
                    + " words per minute."));
            newTypingHistoryPanel.add(new JLabel("Your average accuracy is "
                    + record.calculateAverageAccuracy() + "%."));
        }
        // todo: try to get the grid going for newtypinghistory
        typingApplication.add(newTypingHistoryPanel);
        newTypingHistoryPanel.setVisible(true);
//        setupTypingHistoryPanel(loadResult);
        System.out.println("ahhhh");
        newTypingHistoryPanel.revalidate();
        newTypingHistoryPanel.repaint();
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

    public void clearTypingHistoryPanel() {
//        typingApplication.remove(typingHistoryPanel);         // works but only for typingScreen, not history
//        typingHistoryPanel.setVisible(false);
//        typingHistoryPanel.removeAll();                         // removes all components... but doesn't come back?
        typingHistoryPanel.revalidate();
        typingHistoryPanel.repaint();
    }

    // saves data to Record and returns String so it can used in TypingScreen for a good use (displays it below buttons)
    public String saveData(TypingPractice typingPractice) {
        record.addUserHistory(typingPractice);
        return "Saved successfully!";
    }

    // todo: need to be revised, i just copied this from TypingApp
    public String saveDataToJson() {
        try {
            jsonWriter.open();
            jsonWriter.write(record);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            return "Unable to write to file: " + JSON_STORE;
        }
        return "Saved current history to " + JSON_STORE;
    }

    public void loadDataFromJson() {
        System.out.println("pretend that the app successfully loaded the file");
    }

    public void clearData() {
        System.out.println("pretend that the app successfully cleared the data");
    }
}
