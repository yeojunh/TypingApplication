package ui.screens;

import ui.TypingApplication;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

// Layout tutorial from Madsycode on YouTube
// https://www.youtube.com/watch?v=Cxp_HvXZh6g
public class MainScreen extends Screen {
    private TypingApplication typingApplication;
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
    private static final Color MAINCONTAINER_COLOR = new Color(4, 53, 101);
//    private static final Color TOPPANEL_COLOR = new Color(255,238,207); // beige
    private static final Color TOPPANEL_COLOR = new Color(133,138,227);
    private static final Color SIDEPANEL_COLOR = new Color(1,30,61);
    private static final Color SIDEPANEL_FONT_COLOR = Color.white;

    public MainScreen(TypingApplication typingApplication) {
        this.typingApplication = typingApplication;
    }

    // todo: is there a way to get rid of the middle section?
    // todo: can i make the width of each l/r panel as half of the screen size so it always fills it?
    @Override
    public void initialize() {
        setupButtons();
        setupMainContainer();
        setupTopPanel();
        setupLeftPanel();
        setupRightPanel();
        mainContainer.setVisible(true);
    }

    @Override
    public void load() {
        // todo: this... i think just setting it as visible is ok
        System.out.println("insert main screen here >:)");
    }

    // higher caller of the top panel creator
    public void setupTopPanel() {
        JPanel topPanel = new JPanel();
        JPanel topLabelPanel = new JPanel();
        setupTopLabelPanel(topLabelPanel);
//        topPanel.setBorder(new EmptyBorder(30, 10, 30, 10));
        setupSidePanel(topPanel, topLabelPanel, "top");
        mainContainer.add(topPanel, BorderLayout.NORTH);
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
            JLabel leftLabel2 = new JLabel("  Select from one of these options:  ", JLabel.CENTER);
            setupSideLabel(leftLabel1);
            setupSideLabel(leftLabel2);
            gridPanel.add(leftLabel1);
            gridPanel.add(leftLabel2);
        } else {
            JLabel rightLabel = new JLabel("           Typing History:           ", JLabel.CENTER);
            setupSideLabel(rightLabel);
            gridPanel.add(rightLabel);
        }
        gridPanel.setBackground(SIDEPANEL_COLOR);
        panel.add(gridPanel);
    }

    // method for side label setup
    public void setupSideLabel(JLabel label) {
        label.setForeground(SIDEPANEL_FONT_COLOR);
        label.setBackground(SIDEPANEL_COLOR);
        label.setFont(new Font(label.getFont().toString(), Font.PLAIN, 20));
        label.setOpaque(true);
        label.setVisible(true);;
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

    // makes a panel for the top panel, not sure why i did this but does the setup stuff here
    public void setupTopLabelPanel(JPanel panel) {
        JLabel label = new JLabel("Bread Nut Typing App Pro :)", SwingConstants.CENTER);
        label.setFont(new Font(label.getFont().toString(), Font.PLAIN, 30));
        label.setVisible(true);
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
    }

    // sets up the list of buttons for the UI
    private void setupButtons() {
        regularBtn = setupButton("Regular");
        shortBtn = setupButton("Short");
        punctuationBtn = setupButton("Punctuations");
        numberBtn = setupButton("Numbers");

        viewHistoryBtn = setupButton("View Typing History");
        saveBtn = setupButton("Save Data");
        loadBtn = setupButton("Load Data");
        clearBtn = setupButton("Clear Data");
    }

    private JButton setupButton(String name) {
        JButton button = new JButton(name);
        String font = button.getFont().toString();
        button.setFont(new Font(font, Font.PLAIN, 18));
        return button;
    }
}
