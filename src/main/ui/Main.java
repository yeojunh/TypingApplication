package ui;

import ui.screens.Layout;

public class Main {
    public static void main(String[] args) {
//        new TypingApp();
        try {
            new TypingApplication();
        } catch (InterruptedException e) {
            System.err.println("interrupted latch call");
        }
//        Layout myLayout = new Layout("My Custom Layout", new TypingApplication());
//        myLayout.setVisible(true);
    }
}
