package ui.screens;

import ui.TypingApplication;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class WelcomeScreen extends Screen {
    private TypingApplication typingApplication;
    JLabel picLabel;
    JFrame frame;

    public WelcomeScreen(TypingApplication typingApplication) {
        this.typingApplication = typingApplication;
        frame = typingApplication.getFrame();
    }

    @Override
    // anonymous class and KeyListener information from Edu4Java
    // http://www.edu4java.com/en/game/game4.html
    public void initialize() {
        try {
//            Container titleContainer = typingApplication.getContentPane();
            ImageIcon icon = new ImageIcon(ImageIO.read(new File("./images/titleImage.png")));
            picLabel = new JLabel();
            picLabel.setIcon(icon);
            typingApplication.add(picLabel);
            picLabel.setVisible(true);
            typingApplication.setVisible(true);
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
                typingApplication.latch.countDown();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // we don't need this
            }
        };
        typingApplication.addKeyListener(welcomeListener);
    }
}
