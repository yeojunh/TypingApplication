package ui;

import model.Record;
import model.TypingPractice;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TypingApp {
    private TypingPractice typingPractice;
    private Record record;
    private Scanner input;

    public TypingApp() {
        runTyping();
    }

    private void runTyping() {
        boolean appOn = true;
        String command = null;
        record = new Record();

        while (appOn) {
            displayMenu();

            input = new Scanner(System.in);
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                appOn = false;
            } else {
                try {
                    processCommand(command);
                } catch (InterruptedException e) {
                    System.out.println("Something interrupted execution. Quitting program.");
                    appOn = false;
                    e.printStackTrace();
                }
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n Select from:");
        System.out.println("\tType r for a regular practice");
        System.out.println("\tType s for short phrase practice");
        System.out.println("\tType p for punctuation practice");
        System.out.println("\tType n for number practice");
        System.out.println("\tType h to view user typing history");
        System.out.println("\tTo quit, type q.");
    }

    private void processCommand(String command) throws InterruptedException {
        if (command.equals("r")) {
            runRegular();
            runTypingTest();
        } else if (command.equals("s")) {
            runShort();
            runTypingTest();
        } else if (command.equals("p")) {
            runPunctuation();
            runTypingTest();
        } else if (command.equals("n")) {
            runNumber();
            runTypingTest();
        } else if (command.equals("h")) {
            runRecord();
        } else {
            System.out.println("Selection is not valid...");
            runTyping();
        }
    }

    private void runRecord() {
        // TODO: this
        if (record.size() == 0) {
            System.out.println("You have no previous typing practice records. Try one out now!\n");
            runTyping();
        } else {
            System.out.println("You have practiced " + record.size() + " times.");
            System.out.println("Your average typing speed is " + record.calculateAverageTypingSpeed() + " words per minute.");
            System.out.println("Your average accuracy is " + record.calculateAverageAccuracy() + "%.\n");
        }
    }

    private void runTypingTest() throws InterruptedException {
        System.out.println(typingPractice.getPhraseToType());
        countdown();
        input = new Scanner(System.in);
        String userInput = input.nextLine();
        typingPractice.setUserTypingInput(userInput);
        typingPractice.finishedTyping();
        System.out.println("Your typing speed is: " + typingPractice.calculateTypingSpeed() + " words per minute.");
        System.out.println("Your accuracy (percentage of words spelt correctly) is: "
                + typingPractice.calculateAccuracy() + "%.");
        record.addUserHistory(typingPractice);
    }

    private void runRegular() {
        typingPractice = new TypingPractice("regular");
        System.out.println("A regular typing practice will start in 3 seconds...");
        typingPractice.choosePhraseToType("regular");
    }

    private void runShort() {
        typingPractice = new TypingPractice("short");
        System.out.println("A short typing practice will start in 3 seconds...");
        typingPractice.choosePhraseToType("short");
    }

    private void runPunctuation() {
        typingPractice = new TypingPractice("punctuation");
        System.out.println("A punctuation-focused typing practice will start in 3 seconds...");
        typingPractice.choosePhraseToType("punctuation");
    }

    private void runNumber() {
        typingPractice = new TypingPractice("number");
        System.out.println("A number-focused typing practice will start in 3 seconds...");
        typingPractice.choosePhraseToType("number");
    }

    // MODIFIES: typingPractice
    // EFFECTS: counts down 3 seconds until the user can type and starts stopwatch
    public void countdown() throws InterruptedException {
        System.out.println("3");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("2");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("1");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("GO!");

        typingPractice.startedTyping();
        // more information about this exception below:
        // https://stackoverflow.com/questions/1087475/when-does-javas-thread-sleep-throw-interruptedexception
    }
}
