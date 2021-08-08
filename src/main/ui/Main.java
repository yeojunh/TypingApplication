package ui;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
//        new TypingApp();      // console-based UI
        try {
            new TypingApplicationGUI();     // Graphic UI
        } catch (InterruptedException e) {
            System.err.println("Interrupted latch call. Try again later.");
        }
    }
}
