package ui.screens;

import ui.TypingApplication;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WelcomeScreen extends Screen {
    private TypingApplication typingApplication;
    private MainScreen mainScreen;
    BufferedImage titleImage;
    JLabel picLabel;

    public WelcomeScreen(TypingApplication typingApplication) {
        this.typingApplication = typingApplication;
        mainScreen = typingApplication.getMainScreen();
    }

    @Override
    // anonymous class information and KeyListener information from Edu4Java
    // http://www.edu4java.com/en/game/game4.html
    public void initialize() {
        try {
            titleImage = ImageIO.read(new File("./images/titleImage.png"));
            picLabel = new JLabel(new ImageIcon(titleImage));
            typingApplication.add(picLabel);
            picLabel.setVisible(false);
        } catch (IOException e) {
            System.err.println("no image found!! bad stuff!!!!!");
        }
    }

    public void load() {
        picLabel.setVisible(true);
        KeyListener welcomeListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // we don't need this
            }

            @Override
            public void keyPressed(KeyEvent e) {
                picLabel.setVisible(false);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // we don't need this
            }
        };
        typingApplication.addKeyListener(welcomeListener);
        typingApplication.setFocusable(true); // allows this class to receive the focus

    }
}
