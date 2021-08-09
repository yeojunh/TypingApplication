package ui.screens;

import model.History;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.TypingApplicationGUI;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// Layout tutorial from Madsycode on YouTube
// https://www.youtube.com/watch?v=Cxp_HvXZh6g

// Represents a main screen for the UI, including the top, left, right, and center panels
// Superclass of TypingScreen and HistoryScreen classes
public class MainScreen implements ActionListener {
    protected static final Color MAIN_CONTAINER_COLOR = new Color(10, 46, 79);
    protected static final Color TOP_PANEL_COLOR = new Color(173, 177, 237);
    protected static final Color SIDE_PANEL_COLOR = new Color(1,30,61);
    protected static final Color SIDE_PANEL_FONT_COLOR = Color.white;
    protected static final int SIDE_PANEL_FONT_SIZE = 25;
    protected static final int CENTRE_LABEL_FONT_SIZE = 25;
    private static final int TOP_PANEL_FONT_SIZE = 30;
    private static final int BUTTON_FONT_SIZE = 20;
    private static final int HGAP = 8;
    private static final int VGAP = 6;
    protected static final String filler = "    ";              // design-related

    protected TypingApplicationGUI typingApplicationGUI;        // UI-related
    protected History history;
    protected Container mainContainer;
    protected JPanel centrePanel;
    protected JButton regularBtn;
    protected JButton shortBtn;
    protected JButton punctuationBtn;
    protected JButton numberBtn;
    protected JButton viewHistoryBtn;
    protected JButton saveBtn;
    protected JButton loadBtn;
    protected JButton clearBtn;

    protected static final String JSON_STORE = "./data/history.json";
    protected JsonWriter jsonWriter;
    protected JsonReader jsonReader;                            // JSON-related

    // EFFECTS: initializes the MainScreen's typingApplication as the provided typingApplicationGUI
    public MainScreen(TypingApplicationGUI typingApplicationGUI) {
        this.typingApplicationGUI = typingApplicationGUI;
    }

    // MODIFIES: this
    // EFFECTS: sets up components for the main screen. mainContainer is initially set to invisible
    public void initialize() {
        history = new History();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        loadDataFromJson();
        setupButtons();
        setupMainContainer();
        setupTopPanel(mainContainer);
        setupLeftPanel(mainContainer);
        setupRightPanel(mainContainer);
        setupCentrePanel();
    }

    // MODIFIES: mainContainer
    // EFFECTS: constructs the top panel of the mainContainer passed in as a parameter
    private void setupTopPanel(Container mainContainer) {
        JPanel topPanel = new JPanel();
        JPanel topLabelPanel = new JPanel();
        setupTopLabelPanel(topLabelPanel);
        setupSidePanel(topPanel, topLabelPanel, "top");
        mainContainer.add(topPanel, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: constructs the centre panel of the mainContainer passed in as a parameter
    private void setupCentrePanel() {
        centrePanel = new JPanel();
        centrePanel.setBackground(MAIN_CONTAINER_COLOR);
        setupCentreLabelPanel(centrePanel);
        mainContainer.add(centrePanel, BorderLayout.CENTER);
    }

    // MODIFIES: mainContainer
    // EFFECTS: constructs the left panel of the mainContainer passed in as a parameter
    private void setupLeftPanel(Container mainContainer) {
        JPanel leftPanel = new JPanel();
        JPanel leftLabelPanel = new JPanel();
        setupSidePanelGrid(leftLabelPanel, "left");
        setupSidePanel(leftPanel, leftLabelPanel, "side");
        setupPanelButtons(leftPanel, "left");
        mainContainer.add(leftPanel, BorderLayout.WEST);
    }

    // MODIFIES: mainContainer
    // EFFECTS: constructs the right panel of the mainContainer passed in as a parameter
    private void setupRightPanel(Container mainContainer) {
        JPanel rightPanel = new JPanel();
        JPanel rightLabelPanel = new JPanel();
        setupSidePanelGrid(rightLabelPanel, "right");
        setupSidePanel(rightPanel, rightLabelPanel, "side");
        setupPanelButtons(rightPanel, "right");
        mainContainer.add(rightPanel, BorderLayout.EAST);
    }

    // MODIFIES: centrePanel
    // EFFECTS: constructs labels with welcome texts and puts them into a panel
    //          then adds this panel to centrePanel, which is the centre panel of mainContainer
    private void setupCentreLabelPanel(JPanel centrePanel) {
        JPanel labelEncapsulator = new JPanel();
        labelEncapsulator.setLayout(new GridLayout(4, 0));
        labelEncapsulator.setBackground(MAIN_CONTAINER_COLOR);

        JLabel centreLabel = new JLabel("Welcome to Bread Nut Typing App Pro :) !!!", SwingConstants.CENTER);
        JLabel centreLabel2 = new JLabel("- 🍞🥜 -", SwingConstants.CENTER);
        setLabelFont(centreLabel, SIDE_PANEL_FONT_COLOR, CENTRE_LABEL_FONT_SIZE);
        setLabelFont(centreLabel2, SIDE_PANEL_FONT_COLOR, CENTRE_LABEL_FONT_SIZE);
        centreLabel2.setBorder(new EmptyBorder(0,0,20,0));

        JLabel introLabel1 = new JLabel("Press buttons on the left to start a new typing practice",
                SwingConstants.CENTER);
        JLabel introLabel2 = new JLabel("Press buttons on the right to view or change your saved history",
                SwingConstants.CENTER);
        setLabelFont(introLabel1, SIDE_PANEL_FONT_COLOR, SIDE_PANEL_FONT_SIZE);
        setLabelFont(introLabel2, SIDE_PANEL_FONT_COLOR, SIDE_PANEL_FONT_SIZE);

        labelEncapsulator.add(centreLabel);
        labelEncapsulator.add(centreLabel2);
        labelEncapsulator.add(introLabel1);
        labelEncapsulator.add(introLabel2);
        centrePanel.add(labelEncapsulator);
    }

    // MODIFIES: label
    // EFFECTS: changes the font colour and size of the given label to the given colour and size
    public void setLabelFont(JLabel label, Color color, int size) {
        label.setFont(new Font(label.getFont().toString(), Font.PLAIN, size));
        label.setForeground(color);
    }

    // MODIFIES: panel
    // EFFECTS: constructs a grid panel to encapsulate labels, allowing the left panel to have 2 lines
    //          and the right panel to have 1 line with fillers
    private void setupSidePanelGrid(JPanel panel, String side) {
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(2, 1, 0, 5));
        if (side.equals("left")) {
            JLabel leftLabel1 = new JLabel("Start a new practice!", JLabel.CENTER);
            JLabel leftLabel2 = new JLabel("  Select from one of these options:" + filler, JLabel.CENTER);
            setupSideLabel(leftLabel1);
            setupSideLabel(leftLabel2);
            gridPanel.add(leftLabel1);
            gridPanel.add(leftLabel2);
        } else {
            JLabel rightLabel = new JLabel(filler +  filler + filler
                    + "Typing History:"
                    + filler + filler + filler,
                    JLabel.CENTER);
            setupSideLabel(rightLabel);
            gridPanel.add(rightLabel);
        }
        gridPanel.setBackground(SIDE_PANEL_COLOR);
        panel.add(gridPanel);
    }

    // MODIFIES: label
    // EFFECTS: sets the background colour of the label to the side panel's default colour
    //          and sets the font and text colour to side panel's default font colour and size
    private void setupSideLabel(JLabel label) {
        label.setBackground(SIDE_PANEL_COLOR);
        setLabelFont(label, SIDE_PANEL_FONT_COLOR, SIDE_PANEL_FONT_SIZE);
    }

    // MODIFIES: panel, labelPanel
    // EFFECTS: sets up the panel and label's background colour to the default panel colour for the side (top or side)
    //          and adds the label to the panel
    private void setupSidePanel(JPanel panel, JPanel labelPanel, String side) {
        Color panelColor;
        if (side.equals("top")) {
            panelColor = TOP_PANEL_COLOR;
        } else {
            panelColor = SIDE_PANEL_COLOR;
        }
        panel.setBackground(panelColor);
        labelPanel.setBackground(panelColor);
        panel.add(labelPanel);
    }

    // MODIFIES: panel
    // EFFECTS: creates a new label with the title and adds the label to the given panel
    private void setupTopLabelPanel(JPanel panel) {
        JLabel label = new JLabel("Bread Nut Typing App Pro :)", SwingConstants.CENTER);
        setLabelFont(label, label.getForeground(), TOP_PANEL_FONT_SIZE);
        label.setBackground(TOP_PANEL_COLOR);
        panel.setBackground(TOP_PANEL_COLOR);
        panel.add(label);
    }

    // MODIFIES: panel
    // EFFECTS: adds appropriate buttons for the given side into a grid panel with 4 rows for vertical organization
    private void setupPanelButtons(JPanel panel, String side) {
        panel.setLayout(new GridLayout(2, 1));
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(4, 1, HGAP, VGAP));

        if (side.equals("left")) {
            gridPanel.add(regularBtn);
            gridPanel.add(shortBtn);
            gridPanel.add(punctuationBtn);
            gridPanel.add(numberBtn);
        } else {
            gridPanel.add(viewHistoryBtn);
            gridPanel.add(saveBtn);
            gridPanel.add(loadBtn);
            gridPanel.add(clearBtn);
        }
        gridPanel.setBackground(SIDE_PANEL_COLOR);
        panel.add(gridPanel);
    }

    // MODIFIES: this
    // EFFECTS: initializes the value of the mainContainer and sets the layout and background
    private void setupMainContainer() {
        mainContainer = typingApplicationGUI.getContentPane();
        mainContainer.setLayout(new BorderLayout(HGAP, VGAP));
        mainContainer.setBackground(MAIN_CONTAINER_COLOR);
    }

    // MODIFIES: this
    // EFFECTS: initializes the list of buttons for the UI
    private void setupButtons() {
        regularBtn = setupButton("Regular");
        shortBtn = setupButton("Short");
        punctuationBtn = setupButton("Punctuation");
        numberBtn = setupButton("Number");

        viewHistoryBtn = setupButton("View Typing History");
        saveBtn = setupButton("Save Data");
        loadBtn = setupButton("Load Data");
        clearBtn = setupButton("Clear Data");
    }

    // EFFECTS: returns an active button with the given name
    private JButton setupButton(String name) {
        JButton button = new JButton(name);
        String font = button.getFont().toString();
        button.setFont(new Font(font, Font.PLAIN, BUTTON_FONT_SIZE));
        button.setActionCommand(name);
        button.addActionListener(this);
        return button;
    }

    // MODIFIES: this
    // EFFECTS: calls different methods depending on the button's action command activated
    public void actionPerformed(ActionEvent e) {
        TypingScreen typingScreen = typingApplicationGUI.getTypingScreen();
        HistoryScreen historyScreen = typingApplicationGUI.getHistoryScreen();
        centrePanel.setVisible(false);
        if ("Regular".equals(e.getActionCommand())) {
            typingScreen.loadRegularTyping();
        } else if ("Short".equals(e.getActionCommand())) {
            typingScreen.loadShortTyping();
        } else if ("Punctuation".equals(e.getActionCommand())) {
            typingScreen.loadPunctuationTyping();
        } else if ("Number".equals(e.getActionCommand())) {
            typingScreen.loadNumberTyping();
        } else if ("View Typing History".equals(e.getActionCommand())) {
            historyScreen.loadTypingHistory();
        } else if ("Save Data".equals(e.getActionCommand())) {
            saveDataToJson();
        } else if ("Load Data".equals(e.getActionCommand())) {
            loadDataFromJson();
            historyScreen.loadTypingHistory();
        } else { // clear data
            historyScreen.clearData();
            historyScreen.loadTypingHistory();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads data from JSON, or outputs an error if JSON file is not found
    private void loadDataFromJson() {
        try {
            history = jsonReader.read();
        } catch (IOException e) {
            System.err.println("Unable to read from file " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: saves data to JSON and plays audio
    private void saveDataToJson() {
        playAudio("menuAudio");
        writeToJson();
    }

    // MODIFIES: this
    // EFFECTS: writes data to JSON or prints an error if file is not found
    private void writeToJson() {
        try {
            jsonWriter.open();
            jsonWriter.write(history);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.err.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // https://stackoverflow.com/questions/15526255/best-way-to-get-sound-on-button-press-for-a-java-calculator
    // EFFECTS: plays a .wav audio in the data folder
    //          prints error if the audio file is of the incorrect type, given a wrong destination, or does not exist
    protected void playAudio(String fileName) {
        String filePath = "./data/" + fileName + ".wav";
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Audio from " + filePath + " is not supported.");
        } catch (IOException e) {
            System.err.println("Unable to load audio file from " + filePath);
        } catch (LineUnavailableException e) {
            System.err.println("Audio file is not available at " + filePath);
        }
    }
}
