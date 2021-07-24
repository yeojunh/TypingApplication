package model;

import java.util.ArrayList;
import java.util.List;

public class History {
    List<TypingPractice> history;

    // regex or substring
    // keep a counter for n of wrongs
    // can also track the number of string length difference


    // EFFECTS: creates a new list of past typing practices for the given user number (unique)
    public History(int userNumber) {
        // stub
    }

    // EFFECTS: return the typing history for the given user
    public History getUserHistory() {
        return new History(0001); // stub
    }

    // MODIFIES: this
    // EFFECTS: adds given typing practice result to user history
    public void addUserHistory(TypingPractice tp) {
        // stub
        // we will not remove certain runs from user history - it defeat the purpose of having a history
    }

    // EFFECTS: returns the average typing speed of  user
    public double getAverageTypingSpeed() {
        return 0.0; // stub
    }

    // EFFECTS: returns the average accuracy of  user
    public double getAverageAccuracy() {
        return 0.0; // stub
    }
}
