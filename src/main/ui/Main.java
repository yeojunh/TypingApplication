package ui;

import ui.screens.Layout;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
//        new TypingApp();
        try {
            new TypingApplication();
        } catch (InterruptedException | IOException e) {
            System.err.println("interrupted latch call");
        }
//        Layout myLayout = new Layout("My Custom Layout", new TypingApplication());
//        myLayout.setVisible(true);
    }
}
