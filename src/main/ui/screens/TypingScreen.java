package ui.screens;

import model.TypingPractice;
import ui.TypingApplication;

import javax.swing.*;
import java.awt.*;

// represents the centre panel that shows the typing test area of the application
public class TypingScreen extends MainScreen {
    private JPanel typingScreenPanel;
    private JLabel typingScreenLabel;

    public TypingScreen(TypingApplication typingApplication) {
        super(typingApplication);
        mainContainer = typingApplication.getContentPane();
        centrePanel = typingApplication.getMainScreen().getCentrePanel();
    }

    @Override
    public void initialize() {
        System.out.println("typing screen is initializing...");
        // might not need this
    }

    public void load() {
        JPanel typingScreenPanel = new JPanel();
        JLabel typingIntroLabel = new JLabel("THIS IS THE TYPING SCREEN");
        typingScreenPanel.add(typingIntroLabel);
        mainContainer.remove(typingApplication.getMainScreen().getCentrePanel());
        typingApplication.getMainScreen().getCentrePanel().setVisible(false);
        mainContainer.add(typingScreenPanel, BorderLayout.CENTER);
        mainContainer.validate();
        mainContainer.revalidate();
    }

    public void loadRegularTyping() {
        System.out.println("pretend that the regular typing screen loaded");
        loadRegularTypingPanel();
    }

    public void loadRegularTypingPanel() {
        setupTypingPanel(typingScreenPanel, typingScreenLabel, "THIS IS THE TYPING SCREEN", "regular");
    }

    // helper that sets up a new typingScreenPanel, label, and validates panel
    public void setupTypingPanel(JPanel panel, JLabel label, String labelText, String focus) {
        panel = new JPanel();
        label = new JLabel(labelText);
        panel.setBackground(MAINCONTAINER_COLOR);
        label.setBackground(MAINCONTAINER_COLOR);
        setLabelFont(label, SIDEPANEL_FONT_COLOR, 20);
        panel.add(label);
        mainContainer.remove(getCentrePanel());
        mainContainer.add(panel, BorderLayout.CENTER);
        mainContainer.add(setupTypingText(getTypingText(focus)));
        mainContainer.add(setupTypingArea());
        mainContainer.validate();
        mainContainer.revalidate();
    }

    public JLabel setupTypingText(String phraseToType) {
        JLabel phraseToTypeLabel = new JLabel(phraseToType);
        setLabelFont(phraseToTypeLabel, SIDEPANEL_FONT_COLOR, 20); // todo: not sure how it'll have breaks here
        // todo: maybe run a for loop and for every n words we'll make a breakpoint with <br> in html?
        return phraseToTypeLabel;
    }

    // REQUIRES: focus must be one of: regular, short, punctuation, or number //todo: exceptions
    // EFFECTS: returns the typing practice phrase for the user to type
    public String getTypingText(String focus) {
        // todo: get some info from the typing Practice and load the info here
        TypingPractice typingPractice = new TypingPractice(focus);
        return typingPractice.choosePhraseToType(focus);
    }

    public JPanel setupTypingArea() {
        JPanel typingAreaEncapsulator = new JPanel();
        typingAreaEncapsulator.setBackground(MAINCONTAINER_COLOR);
        JTextField textField = new JTextField(10);
        textField.setBackground(MAINCONTAINER_COLOR);
        textField.setForeground(SIDEPANEL_FONT_COLOR);
        // todo: set font size
        textField.setEditable(true);
        typingAreaEncapsulator.add(textField);
//        textField.setMaximumSize(new Dimension(1, 1));
        // todo: setup a typing area and save the data somewhere and return it to somewhere we can process it
        return typingAreaEncapsulator;
    }

    public void loadShortTyping() {
        System.out.println("pretend that the short typing screen loaded");
        JPanel typingScreenPanel = new JPanel();
        JLabel typingIntroLabel = new JLabel("THIS IS THE SHORT TYPING SCREEN");
        typingScreenPanel.add(typingIntroLabel);
//        mainContainer.remove(typingApplication.getMainScreen().getCentrePanel());
//        typingApplication.getMainScreen().getCentrePanel().setVisible(false);
        mainContainer.add(typingScreenPanel, BorderLayout.CENTER);
        mainContainer.validate();
        mainContainer.revalidate();
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
