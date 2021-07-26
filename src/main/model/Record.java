package model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;

public class Record {
    List<TypingPractice> record;
    private double averageWpm;
    private double averageAccuracy;
    // regex or substring
    // keep a counter for n of wrongs
    // can also track the number of string length difference


    // EFFECTS: creates a new list of past typing practices for the given user number (unique)
    public Record() {
        record = new ArrayList<TypingPractice>();
    }

    // EFFECTS: return the typing history for the given user
    public List<TypingPractice> getUserHistory() {
        return record;
    }

    // MODIFIES: this
    // EFFECTS: adds given typing practice result to user history
    public void addUserHistory(TypingPractice tp) {
        record.add(tp);
        // we will not remove certain runs from user history - it defeat the purpose of having a history
    }

    // EFFECTS: returns the average typing speed of  user
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

    // EFFECTS: returns the average accuracy of  user
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

    public double roundToTwoDecimalPlaces(double average) {
        average = average * 100;
        average = Math.round(average);
        average = average / 100;
        return average;
    }

    public int size() {
        return record.size();
    }

    public double getAverageWpm() {
        return averageWpm;
    }

    public double getAverageAccuracy() {
        return averageAccuracy;
    }
}
