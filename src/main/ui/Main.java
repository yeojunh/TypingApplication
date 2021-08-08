package ui;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
//        new TypingApp();
        try {
            new TypingApplicationGUI();
        } catch (InterruptedException | IOException e) {
            System.err.println("interrupted latch call");
        }
    }
}
