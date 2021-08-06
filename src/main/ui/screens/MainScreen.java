package ui.screens;

import javafx.beans.property.adapter.JavaBeanLongPropertyBuilder;
import ui.TypingApplication;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Layout tutorial from Madsycode on YouTube
// https://www.youtube.com/watch?v=Cxp_HvXZh6g

// represents a main screen for the UI, including the top, left, right, and center panels
public class MainScreen extends Screen implements ActionListener {
    private TypingApplication typingApplication;
    private JPanel centrePanel;
    private JButton regularBtn;
    private JButton shortBtn;
    private JButton punctuationBtn;
    private JButton numberBtn;
    private JButton viewHistoryBtn;
    private JButton saveBtn;
    private JButton loadBtn;
    private JButton clearBtn;
    private Container mainContainer;
    private static final int HGAP = 8;
    private static final int VGAP = 6;
    private static final Color MAINCONTAINER_COLOR = new Color(10, 46, 79);
//        private static final Color TOPPANEL_COLOR = new Color(255, 246, 230); // beige
    private static final Color TOPPANEL_COLOR = new Color(173, 177, 237);
    private static final Color SIDEPANEL_COLOR = new Color(1,30,61);
    private static final Color SIDEPANEL_FONT_COLOR = Color.white;
    private String filler = "    ";

    public MainScreen(TypingApplication typingApplication) {
        this.typingApplication = typingApplication;
    }

    // sets all content to the visible boolean value - might not need this // todo: might not need this
    public void setVisible(boolean visible) {
        mainContainer.setVisible(visible);
        centrePanel.setVisible(visible);
        regularBtn.setVisible(visible);
        shortBtn.setVisible(visible);
        punctuationBtn.setVisible(visible);
        numberBtn.setVisible(visible);
        viewHistoryBtn.setVisible(visible);
        saveBtn.setVisible(visible);
        loadBtn.setVisible(visible);
        clearBtn.setVisible(visible);

//        typingApplication.getMainScreen().setVisible(visible); // infinite loop
    }

    @Override
    public void initialize() {
        setupButtons();
        setupMainContainer();
        setupTopPanel();
        setupLeftPanel();
        setupRightPanel();
        setupCentrePanel();
        mainContainer.setVisible(true);
    }

    @Override
    public void load() {
        mainContainer.setVisible(true);
    }

    // higher caller of the top panel creator
    public void setupTopPanel() {
        JPanel topPanel = new JPanel();
        JPanel topLabelPanel = new JPanel();
        setupTopLabelPanel(topLabelPanel);
        setupSidePanel(topPanel, topLabelPanel, "top");
        mainContainer.add(topPanel, BorderLayout.NORTH);
    }

    // higher caller method of the centre panel creator
    public void setupCentrePanel() {
        centrePanel = new JPanel();
        centrePanel.setBackground(MAINCONTAINER_COLOR);
        setupCentreLabelPanel(centrePanel);
        mainContainer.add(centrePanel, BorderLayout.CENTER);
    }

    // creates labels with welcome writing and puts them into a panel, then adds the panel to mainContainer
    // these will disappear once the user clicks any of the buttons
    public void setupCentreLabelPanel(JPanel centrePanel) {
        JPanel labelEncapsulator = new JPanel();
        labelEncapsulator.setLayout(new GridLayout(4, 0));
        labelEncapsulator.setBackground(MAINCONTAINER_COLOR);

        JLabel centreLabel = new JLabel("Welcome to Bread Nut Typing App Pro :) !!!", SwingConstants.CENTER);
        JLabel centreLabel2 = new JLabel("- 🍞🥜 -", SwingConstants.CENTER);
        setLabelFont(centreLabel, SIDEPANEL_FONT_COLOR, 25);
        setLabelFont(centreLabel2, SIDEPANEL_FONT_COLOR, 30);
        centreLabel2.setBorder(new EmptyBorder(0,0,20,0));

        JLabel introLabel1 = new JLabel("Press buttons on the left to start a new typing practice",
                SwingConstants.CENTER);
        JLabel introLabel2 = new JLabel("Press buttons on the right to view or change your saved history",
                SwingConstants.CENTER);
        setLabelFont(introLabel1, SIDEPANEL_FONT_COLOR, 20);
        setLabelFont(introLabel2, SIDEPANEL_FONT_COLOR, 20);

        labelEncapsulator.add(centreLabel);
        labelEncapsulator.add(centreLabel2);
        labelEncapsulator.add(introLabel1);
        labelEncapsulator.add(introLabel2);
        centrePanel.add(labelEncapsulator);
    }

    // MODIFIES: this
    // EFFECTS: changes the font colour and size of the given label to the given colour and size
    public void setLabelFont(JLabel label, Color color, int size) {
        label.setFont(new Font(label.getFont().toString(), Font.PLAIN, size));
        label.setForeground(color);
    }

    // higher caller of the left panel creator
    public void setupLeftPanel() {
        JPanel leftPanel = new JPanel();
        JPanel leftLabelPanel = new JPanel();
        setupSidePanelGrid(leftLabelPanel, "left");
        setupSidePanel(leftPanel, leftLabelPanel, "side");
        setupPanelButtons(leftPanel, "left");
        mainContainer.add(leftPanel, BorderLayout.WEST);
    }

    // higher caller of the right panel creator
    public void setupRightPanel() {
        JPanel rightPanel = new JPanel();
        JPanel rightLabelPanel = new JPanel();
        setupSidePanelGrid(rightLabelPanel, "right");
        setupSidePanel(rightPanel, rightLabelPanel, "side");
        setupPanelButtons(rightPanel, "right");
        mainContainer.add(rightPanel, BorderLayout.EAST);
    }

    // creates a grid just for the labels so that left side can be in 2 lines
    public void setupSidePanelGrid(JPanel panel, String side) {
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
        gridPanel.setBackground(SIDEPANEL_COLOR);
        panel.add(gridPanel);
    }

    // method for side label setup
    public void setupSideLabel(JLabel label) {
        label.setBackground(SIDEPANEL_COLOR);
        setLabelFont(label, SIDEPANEL_FONT_COLOR, 20);
    }

    // top/side panel background setup
    public void setupSidePanel(JPanel panel, JPanel labelPanel, String side) {
        Color panelColor;
        if (side.equals("top")) {
            panelColor = TOPPANEL_COLOR;
        } else {
            panelColor = SIDEPANEL_COLOR;
        }
        panel.setBackground(panelColor);
        labelPanel.setBackground(panelColor);
        panel.add(labelPanel);
    }

    // makes a the top label into a panel so that it's easier to work with
    public void setupTopLabelPanel(JPanel panel) {
        JLabel label = new JLabel("Bread Nut Typing App Pro :)", SwingConstants.CENTER);
        setLabelFont(label, label.getForeground(), 30);
        label.setBackground(TOPPANEL_COLOR);
        panel.setBackground(TOPPANEL_COLOR);
        panel.add(label);
    }

    // puts panel buttons in a grid so that it's vertically organized
    public void setupPanelButtons(JPanel panel, String side) {
        panel.setLayout(new GridLayout(2, 1));
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(4, 1, 5, 5));

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
        gridPanel.setBackground(SIDEPANEL_COLOR);
        panel.add(gridPanel);
    }

    // sets up the main (overall) container for the UI
    public void setupMainContainer() {
        mainContainer = typingApplication.getContentPane();
        mainContainer.setLayout(new BorderLayout(HGAP, VGAP));
        mainContainer.setBackground(MAINCONTAINER_COLOR);
        System.out.println("main container has been set up!");
    }

    // sets up the list of buttons for the UI
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

    private JButton setupButton(String name) {
        JButton button = new JButton(name);
        String font = button.getFont().toString();
        button.setFont(new Font(font, Font.PLAIN, 18));
        button.setActionCommand(name);
        button.addActionListener(this);
        return button;
    }

    public void actionPerformed(ActionEvent e) {
        TypingScreen typingScreen = typingApplication.getTypingScreen();
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
            typingScreen.loadTypingHistory();
        } else if ("Save Data".equals(e.getActionCommand())) {
            typingScreen.saveData();
        } else if ("Load Data".equals(e.getActionCommand())) {
            typingScreen.loadData();
        } else {
            typingScreen.clearData();
        }
    }
}
