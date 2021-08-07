package ui.screens;

import model.TypingPractice;
import ui.TypingApplication;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

// represents the centre panel that shows the typing test area of the application
public class TypingScreen extends MainScreen {
    private JPanel typingScreenPanel;
    private JLabel typingScreenLabel;
    private static final int TYPING_AREA_COL = 40;
    private String userInput;
    private String phraseToType;
    private ArrayList<String> phraseToTypeArray;
    private JPanel typingArea;
    private JTextArea textArea;
    private TypingPractice typingPractice;

    public TypingScreen(TypingApplication typingApplication) {
        super(typingApplication);
        mainContainer = typingApplication.getContentPane();
        centrePanel = typingApplication.getMainScreen().getCentrePanel();
        typingScreenPanel = new JPanel();
        typingScreenLabel = new JLabel();
        userInput = "";
    }

    public void loadRegularTyping() {
        clearScreen();
        setupTypingPanel("REGULAR TYPING PRACTICE", "regular");
    }

    public void loadShortTyping() {
        clearScreen();
        setupTypingPanel("STARTING SHORT TYPING", "short");
    }

    public void loadPunctuationTyping() {
        clearScreen();
        setupTypingPanel("STARTING PUNCTUATION TYPING", "punctuation");
    }

    public void loadNumberTyping() {
        clearScreen();
        setupTypingPanel("STARTING NUMBER TYPING", "number");
    }

    public void clearScreen() {
        typingScreenPanel.removeAll();
        typingScreenPanel.revalidate();
        typingScreenPanel.repaint();
    }

    // helper that sets up a new typingScreenPanel, label, and validates panel
    public void setupTypingPanel(String labelText, String focus) {
        typingScreenLabel.setText(labelText);
        typingScreenPanel.setBackground(MAINCONTAINER_COLOR);
        typingScreenLabel.setBackground(MAINCONTAINER_COLOR);
        setLabelFont(typingScreenLabel, SIDEPANEL_FONT_COLOR, 20);
        typingScreenLabel.setBorder(new EmptyBorder(10, 0, 20,0));
        typingScreenPanel.add(typingScreenLabel);
        typingScreenPanel.add(setupTextToShow(getTypingText(focus)));
        typingScreenPanel.add(setupTypingArea());
        mainContainer.add(centrePanel, BorderLayout.CENTER);
        mainContainer.add(typingScreenPanel, BorderLayout.CENTER);
        mainContainer.validate();
        mainContainer.revalidate();
    }

    public JTextArea setupTextToShow(String phraseToType) {
        String actualPhraseToType = "";

        phraseToTypeArray = new ArrayList<String>();
        String[] wordsToTypeArray = phraseToType.split(" ");
        for (String next: wordsToTypeArray) {
            phraseToTypeArray.add(next);
        }
        for (int i = 0; i < phraseToTypeArray.size(); i++) {
            if (i % 10 == 0) {
                phraseToTypeArray.add(i, "\n");
            }
        }
        for (String next: phraseToTypeArray) {
            actualPhraseToType = actualPhraseToType + " " + next;
        }

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setFont(new Font(textArea.getFont().toString(), Font.PLAIN, 20));
        textArea.setBackground(MAINCONTAINER_COLOR);
        textArea.setForeground(SIDEPANEL_FONT_COLOR);
        textArea.setText(actualPhraseToType);
        return textArea;
    }

    // REQUIRES: focus must be one of: regular, short, punctuation, or number //todo: exceptions
    // EFFECTS: returns the typing practice phrase for the user to type
    public String getTypingText(String focus) {
        typingPractice = new TypingPractice(focus);
        phraseToType = typingPractice.choosePhraseToType(focus);
        return phraseToType;
    }

    public JPanel setupTypingArea() {
        typingArea = new JPanel();
        typingArea.setBackground(MAINCONTAINER_COLOR);
        JTextField textField = new JTextField(TYPING_AREA_COL);
        setupTypingAreaTextField(textField);
        KeyListener doneTypingListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!typingPractice.getIsTyping()) {
                    typingPractice.startedTyping();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    keyPressedActions(textField);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        };
        textField.addKeyListener(doneTypingListener);
        typingArea.add(textField);
        return typingArea;
    }

    private void keyPressedActions(JTextField textField) {
        userInput = textField.getText();
        textField.setText("");
        textField.setEditable(false);
        typingPractice.finishedTyping();
        typingScreenPanel.add(setupResultArea());
        typingScreenPanel.revalidate();
        typingScreenPanel.repaint();
    }

    public void setupTypingAreaTextField(JTextField textField) {
        textField.setBackground(MAINCONTAINER_COLOR);
        textField.setForeground(SIDEPANEL_FONT_COLOR);
        textField.setFont(new Font(textField.getFont().toString(), Font.PLAIN, 18));
        textField.setEditable(true);
    }

    public JPanel setupResultArea() {
        JPanel resultPanel = new JPanel();
        JTextArea resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        resultPanel.add(resultTextArea);
        resultPanel.setBackground(MAINCONTAINER_COLOR);
        typingPractice.setUserTypingInput(userInput);
        typingPractice.setPhraseToType(phraseToType);
        typingPractice.calculateTypingSpeed();
        typingPractice.calculateAccuracy();
        String output = "  Your typing speed is: " + typingPractice.getWpm() + " wpm  \n"
                + "  Your accuracy is: " + typingPractice.getAccuracy() + "%  \n";
        resultTextArea.setText(output);
        resultTextArea.setBackground(TOPPANEL_COLOR);
        resultTextArea.setFont(new Font(resultTextArea.getFont().toString(), Font.ITALIC, 15));

//        resultPanel.add(setupResultAreaSavePrompt());
        return resultPanel;
    }

//    public JPanel setupResultAreaSavePrompt() {
////        JPanel savePromptPanel = new JPanel();
////        return null;
//    }
}
