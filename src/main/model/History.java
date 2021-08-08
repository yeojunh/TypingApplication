package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;

// Represents a list for a user's typing runs including the average wpm and accuracy
public class History implements Writable {
    List<TypingPractice> history;
    private double averageWpm;
    private double averageAccuracy;

    // constructor
    // EFFECTS: creates a new list of past typing practices for the current user
    public History() {
        history = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds given typing practice result to user history
    public void addUserHistory(TypingPractice tp) {
        history.add(tp);
        // we will not remove certain runs from user history - it defeats the purpose of having a history
    }

    // MODIFIES: this
    // EFFECTS: calculates, sets, and returns the average typing speed across all recorded runs
    public double calculateAverageTypingSpeed() {
        double avgWpm = 0;
        if (history.isEmpty()) {
            averageWpm = 0;
        } else {
            for (TypingPractice next : history) {
                avgWpm += next.getWpm();
            }
            averageWpm = avgWpm / history.size();
            averageWpm = roundToTwoDecimalPlaces(averageWpm);
        }
        return averageWpm;
    }

    // MODIFIES: this
    // EFFECTS: calculates, sets, and returns the average accuracy across all recorded runs
    public double calculateAverageAccuracy() {
        double avgAcc = 0;
        if (history.isEmpty()) {
            averageAccuracy = 0;
        } else {
            for (TypingPractice next: history) {
                avgAcc += next.getAccuracy();
            }
            averageAccuracy = avgAcc / history.size();
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("history", historyToJson());
        return json;
    }

    // EFFECTS: returns typing practices in this history as JSONArray
    public JSONArray historyToJson() {
        JSONArray jsonArray = new JSONArray();
        for (TypingPractice tp : history) {
            jsonArray.put(tp.toJson());
        }
        return jsonArray;
    }


    // getters
    public int size() {
        return history.size();
    }

    public double getAverageWpm() {
        return averageWpm;
    }

    public double getAverageAccuracy() {
        return averageAccuracy;
    }

    public List<TypingPractice> getUserHistory() {
        return history;
    }

    public TypingPractice getNthTypingPrac(int n) {
        return history.get(n);
    }
}
