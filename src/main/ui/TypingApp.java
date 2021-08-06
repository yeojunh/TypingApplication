package ui;

import model.Record;
import model.TypingPractice;
import org.json.JSONArray;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

// Typing practice application with Console User Interface
// Reference: CPSC 210 TellerApp example
// https://github.students.cs.ubc.ca/CPSC210/TellerApp
public class TypingApp {
    private static final String JSON_STORE = "./data/record.json";
    private TypingPractice typingPractice;
    private Record record;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private boolean firstRun = true;

    // EFFECTS: starts runTyping() method
    public TypingApp() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runTyping();
    }

    // EFFECTS: while the app is on, keeps the typing test going
    //          and prompts the user to choose an option OR prompts the user to type for the test
    private void runTyping() {
        boolean appOn = true;
        String command = null;
        record = new Record();
        runIfFirstTimeRunning();

        while (appOn) {
            firstRun = false;
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

    // EFFECTS: runs loadHistory if the application was just opened
    private void runIfFirstTimeRunning() {
        if (firstRun) {
            loadHistory();
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\n Select from:");
        System.out.println("\tType r for a regular practice");
        System.out.println("\tType s for short phrase practice");
        System.out.println("\tType p for punctuation practice");
        System.out.println("\tType n for number practice");
        System.out.println("\tType h to view user typing history");
        System.out.println("\tType save to save typing history");
        System.out.println("\tType load to load typing history");
        System.out.println("\tType clear to clear typing history");
        System.out.println("\tTo quit, type q.");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) throws InterruptedException {
        if (command.equals("r")) {
            runRegular();
        } else if (command.equals("s")) {
            runShort();
        } else if (command.equals("p")) {
            runPunctuation();
        } else if (command.equals("n")) {
            runNumber();
        } else if (command.equals("h")) {
            runHistory();
        } else if (command.equals("save")) {
            saveHistory();
        } else if (command.equals("load")) {
            loadHistory();
        } else if (command.equals("clear")) {
            clearHistory();
        } else {
            System.out.println("Selection is not valid...");
            runTyping();
        }
    }
    // todo: if extra time: make loading default and allow user to start from scratch if wanted

    // MODIFIES: this
    // EFFECTS: starts the typing test run and asks the user if they want to add this run to their typing history
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
        askToAddRun();
    }

    // EFFECTS: saves history to file
    private void saveHistory() {
        try {
            jsonWriter.open();
            jsonWriter.write(record);
            jsonWriter.close();
            System.out.println("Saved current history to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads history from file
    private void loadHistory() {
        try {
            record = jsonReader.read();
            System.out.println("Loaded previous typing history from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: clears user typing history
    //          user can still access the most recent deleted history if they load before saving again
    private void clearHistory() {
        record = new Record();
        System.out.println("Successfully cleared typing history.");
        System.out.println("If you want to revert your history, please enter LOAD.");
    }

    // MODIFIES: this
    // EFFECTS: asks user if they want to add this run to their hyping history
    //          if user replies yes, then stores the data, otherwise leaves the history as is
    private void askToAddRun() {
        System.out.println("Add this run to your typing history? (y/n)");
        String command = input.next();
        command = command.toLowerCase();
        if (command.equals("y")) {
            record.addUserHistory(typingPractice);
        } else if (command.equals("n")) {
            // do nothing
        } else {
            System.out.println("Invalid input. Your options are y (yes) and n (no).");
            askToAddRun();
        }
    }

    // MODIFIES: this
    // EFFECTS: runs the regular typing practice
    private void runRegular() throws InterruptedException {
        typingPractice = new TypingPractice("regular");
        System.out.println("A regular typing practice will start in 3 seconds...");
        typingPractice.choosePhraseToType("regular");
        runTypingTest();
    }

    // MODIFIES: this
    // EFFECTS: runs the short typing practice
    private void runShort() throws InterruptedException {
        typingPractice = new TypingPractice("short");
        System.out.println("A short typing practice will start in 3 seconds...");
        typingPractice.choosePhraseToType("short");
        runTypingTest();
    }

    // MODIFIES: this
    // EFFECTS: runs the punctuation-focused typing practice
    private void runPunctuation() throws InterruptedException {
        typingPractice = new TypingPractice("punctuation");
        System.out.println("A punctuation-focused typing practice will start in 3 seconds...");
        typingPractice.choosePhraseToType("punctuation");
        runTypingTest();
    }

    // MODIFIES: this
    // EFFECTS: runs the number-focused typing practice
    private void runNumber() throws InterruptedException {
        typingPractice = new TypingPractice("number");
        System.out.println("A number-focused typing practice will start in 3 seconds...");
        typingPractice.choosePhraseToType("number");
        runTypingTest();
    }

    // MODIFIES: this
    // EFFECTS: displays the user's previous typing history including number of times practiced, average wpm, accuracy
    //          if the user has no previous records, prompt the user to start a typing test
    private void runHistory() {
        if (record.size() == 0) {
            System.out.println("You have no previous typing practice history. Try one out now!\n");
            runTyping();
        } else {
            System.out.println("You have practiced " + record.size() + " times.");
            for (int i = 0; i < record.size(); i++) {
                System.out.println("Your run #" + (i + 1) + "\n  Option selected: "
                        + record.getNthTypingPrac(i).getFocus() + "\n  Typing Speed (wpm):  "
                        + record.getNthTypingPrac(i).getWpm() + "\n  Accuracy (%): "
                        + record.getNthTypingPrac(i).getAccuracy() + "\n");
            }
            System.out.println("Your average typing speed is " + record.calculateAverageTypingSpeed()
                    + " words per minute.");
            System.out.println("Your average accuracy is " + record.calculateAverageAccuracy() + "%.");
        }
    }
    // todo: if extra time, add user's name to persistence it so that they can retrieve their own data

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
    }
}
