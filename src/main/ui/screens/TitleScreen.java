package ui.screens;

import ui.TypingApplicationGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

// Represents the GUI for a title screen of an image with a "press any key to continue" feature
public class TitleScreen {
    private TypingApplicationGUI typingApplicationGUI;
    private JLabel picLabel;

    // MODIFIES: this
    // EFFECTS: constructs a new welcome screen with
    public TitleScreen(TypingApplicationGUI typingApplicationGUI) {
        this.typingApplicationGUI = typingApplicationGUI;
    }

    // MODIFIES: this
    // EFFECTS: initializes necessary components of title screen
    //          throws exception if there is no image found at the given path
    public void initialize() {
        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./data/titleImage.png")));
            picLabel = new JLabel();
            picLabel.setIcon(icon);
            typingApplicationGUI.add(picLabel);
            picLabel.setVisible(true);
            typingApplicationGUI.setVisible(true);
        } catch (Exception e) {
            System.err.println("No image found at ./data/titleImage.png!");
            e.printStackTrace();
        }
    }

    // Anonymous class and KeyListener information from Edu4Java
    // http://www.edu4java.com/en/game/game4.html

    // MODIFIES: this
    // EFFECTS: displays the title screen and removes the image to reveal main screen after any key press from user
    public void load() {
        KeyListener titleListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // we don't need this
            }

            @Override
            public void keyPressed(KeyEvent e) {
                picLabel.setVisible(false);
                typingApplicationGUI.latch.countDown();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // we don't need this
            }
        };
        typingApplicationGUI.addKeyListener(titleListener);
    }
}
