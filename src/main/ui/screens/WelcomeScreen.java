package ui.screens;

import ui.TypingApplicationGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class WelcomeScreen extends Screen {
    private TypingApplicationGUI typingApplicationGUI;
    JLabel picLabel;

    public WelcomeScreen(TypingApplicationGUI typingApplicationGUI) {
        this.typingApplicationGUI = typingApplicationGUI;
    }

    @Override
    // anonymous class and KeyListener information from Edu4Java
    // http://www.edu4java.com/en/game/game4.html
    public void initialize() {
        try {
//            Container titleContainer = typingApplicationGUI.getContentPane();
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./images/titleImage.png")));
            picLabel = new JLabel();
            picLabel.setIcon(icon);
            typingApplicationGUI.add(picLabel);
            picLabel.setVisible(true);
            typingApplicationGUI.setVisible(true);
        } catch (Exception e) {
            System.err.println("no image found!! bad stuff!!!!!");
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: display the welcome screen and goes away after any key press
    public void load() {
        KeyListener welcomeListener = new KeyListener() {
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
        typingApplicationGUI.addKeyListener(welcomeListener);
    }
}
