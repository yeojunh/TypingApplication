package model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;

// Represents a list for a user's typing runs including the average wpm and accuracy.
public class Record {
    List<TypingPractice> record;
    private double averageWpm;
    private double averageAccuracy;

    // EFFECTS: creates a new list of past typing practices for the current user
    public Record() {
        record = new ArrayList<TypingPractice>();
    }

    // MODIFIES: this
    // EFFECTS: adds given typing practice result to user history
    public void addUserHistory(TypingPractice tp) {
        record.add(tp);
        // we will not remove certain runs from user history - it defeat the purpose of having a history
    }

    // MODIFIES: this
    // EFFECTS: calculates, sets, and returns the average typing speed of the user
    public double calculateAverageTypingSpeed() {
        double avgWpm = 0;
        if (record.isEmpty()) {
            averageWpm = 0;
        } else {
            for (TypingPractice next : record) {
                avgWpm += next.getWpm();
            }
            averageWpm = avgWpm / record.size();
            averageWpm = roundToTwoDecimalPlaces(averageWpm);
        }
        return averageWpm;
    }

    // MODIFIES: this
    // EFFECTS: calculates, sets, and returns the average accuracy of  user
    public double calculateAverageAccuracy() {
        double avgAcc = 0;
        if (record.isEmpty()) {
            averageAccuracy = 0;
        } else {
            for (TypingPractice next: record) {
                avgAcc += next.getAccuracy();
            }
            averageAccuracy = avgAcc / record.size();
            averageAccuracy = roundToTwoDecimalPlaces(averageAccuracy);
        }
        return averageAccuracy;
    }

    // EFFECTS: returns the given num rounded to 2 decimal places
    public double roundToTwoDecimalPlaces(double average) {
        average = average * 100;
        average = Math.round(average);
        average = average / 100;
        return average;
    }

    // getters
    public int size() {
        return record.size();
    }

    public double getAverageWpm() {
        return averageWpm;
    }

    public double getAverageAccuracy() {
        return averageAccuracy;
    }

    public List<TypingPractice> getUserHistory() {
        return record;
    }

    public TypingPractice getNthTypingPrac(int n) {
        return record.get(n);
    }
}