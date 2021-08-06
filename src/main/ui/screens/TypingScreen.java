package ui.screens;

import model.TypingPractice;
import ui.TypingApplication;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// represents the centre panel that shows the typing test area of the application
public class TypingScreen extends MainScreen {
    private JPanel typingScreenPanel;
    private JLabel typingScreenLabel;
    private static final int TYPING_AREA_COL = 40;
    private String userInput; //todo: clear this after

    public TypingScreen(TypingApplication typingApplication) {
        super(typingApplication);
        mainContainer = typingApplication.getContentPane();
        centrePanel = typingApplication.getMainScreen().getCentrePanel();
        typingScreenPanel = new JPanel();
        typingScreenLabel = new JLabel();
    }

    public void loadRegularTyping() {
        System.out.println("pretend that the regular typing screen loaded");
        setupTypingPanel("THIS IS THE TYPING SCREEN", "regular");
    }

    public void loadShortTyping() {
        setupTypingPanel("STARTING SHORT TYPING", "short");
    }

    public void loadPunctuationTyping() {
        setupTypingPanel("STARTING PUNCTUATION TYPING", "punctuation");
    }

    public void loadNumberTyping() {
        setupTypingPanel("STARTING NUMBER TYPING", "number");
    }

    // helper that sets up a new typingScreenPanel, label, and validates panel
    public void setupTypingPanel(String labelText, String focus) {
        typingScreenLabel.setText(labelText);
        typingScreenPanel.setBackground(MAINCONTAINER_COLOR);
        typingScreenLabel.setBackground(MAINCONTAINER_COLOR);
        setLabelFont(typingScreenLabel, SIDEPANEL_FONT_COLOR, 20);
        typingScreenLabel.setBorder(new EmptyBorder(10, 0, 20,0));
        typingScreenPanel.removeAll();
        typingScreenPanel.add(typingScreenLabel);
        typingScreenPanel.add(setupTypingText(getTypingText(focus)));
        typingScreenPanel.add(setupTypingArea());
        mainContainer.add(typingScreenPanel, BorderLayout.CENTER);
        mainContainer.validate();
        mainContainer.revalidate();
    }
    // try to recycle the panel

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
    // todo: setup a typing area and save the data somewhere and return it to somewhere we can process it

    public JPanel setupTypingArea() {
        JPanel typingAreaEncapsulator = new JPanel();
        typingAreaEncapsulator.setBackground(MAINCONTAINER_COLOR);
        JTextField textField = new JTextField(TYPING_AREA_COL);
        setupTypingAreaTextField(textField);
        KeyListener doneTypingListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // we don't need this
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    userInput = textField.getText();
                    System.out.println(userInput);
                    textField.setText("");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // we don't need this
            }
        };
        textField.addKeyListener(doneTypingListener);
        typingAreaEncapsulator.add(textField);
        return typingAreaEncapsulator;
    }

    public void setupTypingAreaTextField(JTextField textField) {
        textField.setBackground(MAINCONTAINER_COLOR);
        textField.setForeground(SIDEPANEL_FONT_COLOR);
        textField.setFont(new Font(textField.getFont().toString(), Font.PLAIN, 18));
        textField.setEditable(true);
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
