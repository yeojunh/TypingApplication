package ui.screens;

import model.TypingPractice;
import ui.TypingApplicationGUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;

// Represents the centre panel that handles the typing test area of the application
public class TypingScreen extends MainScreen {
    private static final int TYPING_AREA_COL = 40;
    private static final int TYPING_FIELD_FONT_SIZE = 20;
    private static final int RESULT_AREA_FONT_SIZE = 18;
    private static final int NEW_LINE_EVERY_N_WORDS = 8;
    private TypingPractice typingPractice;
    private JPanel resultPanel;
    private JPanel typingScreenPanel;
    private JLabel typingScreenLabel;
    private JButton yesBtn;
    private JButton noBtn;
    private String userInput;
    private String phraseToType;

    // EFFECTS: constructs a TypingScreen object with a mainContainer, main panel, main label, and an empty user input
    public TypingScreen(TypingApplicationGUI typingApplicationGUI) {
        super(typingApplicationGUI);
        mainContainer = typingApplicationGUI.getContentPane();
        typingScreenPanel = new JPanel();
        typingScreenLabel = new JLabel();
        userInput = "";
    }

    // MODIFIES: this
    // EFFECTS: clears the screen so that panels do not overlap, and sets up a typing panel for the focus "regular"
    public void loadRegularTyping() {
        clearScreen();
        setupTypingPanel("STARTING REGULAR TYPING", "regular");
    }

    // MODIFIES: this
    // EFFECTS: clears the screen so that panels do not overlap, and sets up a typing panel for the focus "short"
    public void loadShortTyping() {
        clearScreen();
        setupTypingPanel("STARTING SHORT TYPING", "short");
    }

    // MODIFIES: this
    // EFFECTS: clears the screen so that panels do not overlap, and sets up a typing panel for the focus "punctuation"
    public void loadPunctuationTyping() {
        clearScreen();
        setupTypingPanel("STARTING PUNCTUATION TYPING", "punctuation");
    }

    // MODIFIES: this
    // EFFECTS: clears the screen so that panels do not overlap, and sets up a typing panel for the focus "number"
    public void loadNumberTyping() {
        clearScreen();
        setupTypingPanel("STARTING NUMBER TYPING", "number");
    }

    // MODIFIES: this
    // EFFECTS: clears the typing screen panel so that panels do not overlap when a new typing practice is created
    public void clearScreen() {
        typingScreenPanel.removeAll();
        typingScreenPanel.revalidate();
        typingScreenPanel.repaint();
    }

    // REQUIRES: focus must be one of "regular", "short", "punctuation", or "number"
    // MODIFIES: this
    // EFFECTS: plays audio and set sets up a new typingScreenPanel, label, and validates panel
    public void setupTypingPanel(String labelText, String focus) {
        playAudio("boopAudio");
        typingScreenLabel.setText(labelText);
        JLabel pressEnterWhenDoneLabel = new JLabel("Press ENTER when finished to calculate your result!");
        setLabelFont(pressEnterWhenDoneLabel, SIDE_PANEL_FONT_COLOR, TYPING_FIELD_FONT_SIZE);
        pressEnterWhenDoneLabel.setBackground(MAIN_CONTAINER_COLOR);
        typingScreenPanel.setBackground(MAIN_CONTAINER_COLOR);
        typingScreenLabel.setBackground(MAIN_CONTAINER_COLOR);
        setLabelFont(typingScreenLabel, SIDE_PANEL_FONT_COLOR, SIDE_PANEL_FONT_SIZE);
        typingScreenLabel.setBorder(new EmptyBorder(10, 0, 20,0));
        typingScreenPanel.add(typingScreenLabel);
        typingScreenPanel.add(pressEnterWhenDoneLabel);
        typingScreenPanel.add(setupTextToShow(getTypingText(focus)));
        typingScreenPanel.add(setupTypingArea());
        mainContainer.add(centrePanel, BorderLayout.CENTER);
        mainContainer.add(typingScreenPanel, BorderLayout.CENTER);
        mainContainer.validate();
        mainContainer.revalidate();
    }

    // EFFECTS: returns a JTextArea for the phrase the user has to type, with a new line every n words
    public JTextArea setupTextToShow(String phraseToType) {
        String actualPhraseToType = "";

        String[] wordsToTypeArray = phraseToType.split(" ");
        ArrayList<String> phraseToTypeArray = new ArrayList<>(Arrays.asList(wordsToTypeArray));
        for (int i = 0; i < phraseToTypeArray.size(); i++) {
            if (i % NEW_LINE_EVERY_N_WORDS == 0) {
                phraseToTypeArray.add(i, "\n");
            }
        }
        for (String next: phraseToTypeArray) {
            actualPhraseToType = actualPhraseToType + " " + next;
        }

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setFont(new Font(textArea.getFont().toString(), Font.PLAIN, SIDE_PANEL_FONT_SIZE));
        textArea.setBackground(MAIN_CONTAINER_COLOR);
        textArea.setForeground(SIDE_PANEL_FONT_COLOR);
        textArea.setText(actualPhraseToType);
        return textArea;
    }

    // MODIFIES: this
    // EFFECTS: returns the typing practice phrase for the user to type
    public String getTypingText(String focus) {
        typingPractice = new TypingPractice(focus);
        phraseToType = typingPractice.choosePhraseToType(focus);
        return phraseToType;
    }

    // MODIFIES: this
    // EFFECTS: constructs a typing field for the user to type with a key listener
    //          Registers the end of user input when the user presses enter
    public JPanel setupTypingArea() {
        JPanel typingArea = new JPanel();
        typingArea.setBackground(MAIN_CONTAINER_COLOR);
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

    // MODIFIES: this, textField
    // EFFECTS: sets the user input to the text field's value, clears the text field, and notifies to typingPractice
    //          that the user has finished typing
    private void keyPressedActions(JTextField textField) {
        userInput = textField.getText();
        textField.setText("");
        textField.setEditable(false);
        typingPractice.finishedTyping();
        typingScreenPanel.add(setupResultArea());
        typingScreenPanel.revalidate();
        typingScreenPanel.repaint();
    }

    // MODIFIES: textField
    // EFFECTS: sets the background, font size and colour of the given text field
    public void setupTypingAreaTextField(JTextField textField) {
        textField.setBackground(MAIN_CONTAINER_COLOR);
        textField.setForeground(SIDE_PANEL_FONT_COLOR);
        textField.setFont(new Font(textField.getFont().toString(), Font.PLAIN, TYPING_FIELD_FONT_SIZE));
        textField.setEditable(true);
    }

    // MODIFIES: this
    // EFFECTS: constructs and returns a result area that shows the user's typing speed and accuracy of this run
    public JPanel setupResultArea() {
        resultPanel = new JPanel();
        JTextArea resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        resultPanel.add(resultTextArea);
        resultPanel.setBackground(MAIN_CONTAINER_COLOR);
        typingPractice.setUserTypingInput(userInput);
        typingPractice.setPhraseToType(phraseToType);
        typingPractice.calculateTypingSpeed();
        typingPractice.calculateAccuracy();
        String output = "  Your typing speed is: " + typingPractice.getWpm() + " wpm  \n"
                + "  Your accuracy is: " + typingPractice.getAccuracy() + "%  \n";
        resultTextArea.setText(output);
        resultTextArea.setBackground(TOP_PANEL_COLOR);
        resultTextArea.setFont(new Font(resultTextArea.getFont().toString(), Font.ITALIC, RESULT_AREA_FONT_SIZE));

        resultPanel.add(setupResultAreaSavePrompt());
        resultPanel.setLayout(new BoxLayout(resultPanel,1));
        return resultPanel;
    }

    // MODIFIES: this
    // EFFECTS: constructs and returns a prompt with buttons that allow user to save this run to overall typing history
    public JPanel setupResultAreaSavePrompt() {
        JPanel savePromptPanel = new JPanel();
        JLabel savePromptLabel = new JLabel("Save this run to your typing history?");
        yesBtn = new JButton("Yes");
        noBtn = new JButton("No");
        yesBtn.setActionCommand("Save");
        yesBtn.addActionListener(this);
        noBtn.setActionCommand("Do not save");
        noBtn.addActionListener(this);
        savePromptPanel.add(savePromptLabel);
        savePromptPanel.add(yesBtn);
        savePromptPanel.add(noBtn);
        savePromptPanel.setBackground(TOP_PANEL_COLOR);
        savePromptPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, MAIN_CONTAINER_COLOR));
        setLabelFont(savePromptLabel, Color.black, RESULT_AREA_FONT_SIZE);
        return savePromptPanel;
    }

    // MODIFIES: this
    // EFFECTS: if the user pressed "save", saves the data to typing history (not JSON) and displays saved message
    //          if the user did not want to save, displays a prompt to choose an option from the sides
    //          plays a menu audio regardless of option selected
    public void actionPerformed(ActionEvent e) {
        HistoryScreen historyScreen = typingApplicationGUI.getHistoryScreen();
        centrePanel.setVisible(false);
        if ("Save".equals(e.getActionCommand())) {
            historyScreen.saveData(typingPractice);
            displayBelowButton("Saved successfully!");
        } else {
            displayBelowButton("Choose an option from the sides!");
        }
        playAudio("menuAudio");
        yesBtn.setEnabled(false);
        noBtn.setEnabled(false);
    }

    // MODIFIES: this
    // EFFECTS: constructs a label inside a panel with the given string to display, and adds it to the resultPanel
    public void displayBelowButton(String displayString) {
        JPanel chooseAnOptionPanel = new JPanel();
        JLabel chooseAnOptionLabel = new JLabel(displayString);
        chooseAnOptionPanel.add(chooseAnOptionLabel);
        chooseAnOptionPanel.setBackground(TOP_PANEL_COLOR);
        chooseAnOptionPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, MAIN_CONTAINER_COLOR));
        setLabelFont(chooseAnOptionLabel, Color.black, RESULT_AREA_FONT_SIZE);
        chooseAnOptionLabel.setVisible(true);
        chooseAnOptionPanel.setVisible(true);
        resultPanel.add(chooseAnOptionPanel);
        mainContainer.revalidate();
    }
}
