package model;

// TODO: add class level comments
// TODO: do we need to test setters and getters
public class TypingPractice {
    private double wpm;
    private double accuracy;
    private String focus;                   // alphabet, punctuation, number
    private boolean isTyping;               // true if the user can type, false otherwise
    private String phraseToType;
    private boolean stopwatch;              // true if stopwatch is on, false otherwise
    private int numWordsTyped;              // for wpm calculation
    private int timeElapsed;                // for wpm calculation
    private int numCharAttempted;           // for accuracy calculation
    private int numCharTypedIncorrectly;    // for accuracy calculation


    // EFFECTS: creates a new typing practice given the focus of typing test and whether it is timed
    public TypingPractice(String focus) {
        // stub
    }

    // EFFECTS: fetches a phrase for the user to type based on the given focus
    // could change it to be here since they're just hard coded
    public String setPhraseToType(String focus) {
        return ""; // stub
    }

    // EFFECTS: counts down 3 seconds until the user can type and stopwatch starts
    public void countdown() {
        // stub
    }

    // REQUIRES: isTyping must be false
    // MODIFIES: this
    // EFFECTS: begins timer and sets isTyping to true.
    public void startedTyping() {
        // stub
    }

    // REQUIRE: isTyping must be true
    // MODIFIES: this
    // EFFECTS: stops timer and sets isTyping to false. if already false, do nothing
    public void finishedTyping() {
        // stub
    }

    // EFFECTS: calculates the typing speed of this run
    public double calculateTypingSpeed() {
        return 0.0; // stub
    }

    // EFFECTS: calculates the accuracy of this run
    public double calculateAccuracy() {
        return 0.0; // stub
    }


    // getters
    // EFFECTS: returns true if isTyping is true (user can type)
    public boolean getIsTyping() {
        return false;
    }

    public double getWpm() {
        return 0.0; // stub
    }

    public double getAccuracy() {
        return 0.0; // stub
    }

    public String getFocus() {
        return ""; // stub
    }

    public String getPhraseToType() {
        return ""; // stub
    }

    public boolean getStopwatch() {
        return false; // stub
    }

    public int getNumWordsTyped() {
        return 0; // stub
    }

    public int getNumCharAttempted() {
        return 0; // stub
    }

    public int getNumCharTypedIncorrectly() {
        return 0; // stub
    }

    public int getTimeElapsed() {
        return 0; // stub
    }


    // setters
    public void setIsTyping(boolean isTyping) {
        // stub
    }

    public void setWpm(double wpm) {
        // stub
    }

    public void setAccuracy(double accuracy) {
        // stub
    }

    public void setNumWordsTyped(int num) {
        // stub
    }

    public void setNumCharAttempted(int num) {
        // stub
    }

    public void setNumCharTypedIncorrectly(int num) {
        // stub
    }

    public void setTimeElapsed(int num) {
        // stub
    }
}
