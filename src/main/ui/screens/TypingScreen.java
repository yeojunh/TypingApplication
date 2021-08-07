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
    private String userInput; //todo: clear this after
    private ArrayList<String> phraseToTypeArray;
    private JPanel typingArea;
    private JTextArea textArea;

    public TypingScreen(TypingApplication typingApplication) {
        super(typingApplication);
        mainContainer = typingApplication.getContentPane();
        centrePanel = typingApplication.getMainScreen().getCentrePanel();
        typingScreenPanel = new JPanel();
        typingScreenLabel = new JLabel();
    }

    public void loadRegularTyping() {
        clearScreen();
        setupTypingPanel("REGULAR TYPING PRACTICE", "regular");
//        countdown();
// todo: change the body (second or whatever field of the gridpanel and replace with countdown panel)

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
        System.out.println(userInput);
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
        System.out.println(actualPhraseToType);
        return textArea;
    }

    // REQUIRES: focus must be one of: regular, short, punctuation, or number //todo: exceptions
    // EFFECTS: returns the typing practice phrase for the user to type
    public String getTypingText(String focus) {
        TypingPractice typingPractice = new TypingPractice(focus);
        return typingPractice.choosePhraseToType(focus);
    }

    public JPanel setupTypingArea() {
        typingArea = new JPanel();
        typingArea.setBackground(MAINCONTAINER_COLOR);
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
        typingArea.add(textField);
        return typingArea;
    }

    public void setupTypingAreaTextField(JTextField textField) {
        textField.setBackground(MAINCONTAINER_COLOR);
        //        textField.setBackground(MAINCONTAINER_COLOR);
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
