package ui;

public class Main {
    public static void main(String[] args) {
        try {
            new TypingApplicationGUI();
        } catch (InterruptedException e) {
            System.err.println("Something interrupted the title screen. Please try again later.");
        }
    }
}
