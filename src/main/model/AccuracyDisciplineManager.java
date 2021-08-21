package model;

import ui.TypingApplicationGUI;

import java.util.HashMap;
import java.util.Map;

// Represents the manager that handles discipline depending on the accuracy percentage the user has lost.
// Handles the series of questionnaires that appear after the user completes a typing test
// For every 20% accuracy lost, the number of questions given increases (undecided)
public class AccuracyDisciplineManager {
    private TypingPractice typingPractice;
    private Map<String, String> questions; // key: question     value: answer
    private String userResponse;

    public AccuracyDisciplineManager(TypingPractice typingPractice) {
        this.typingPractice = typingPractice;
        questions = new HashMap<>();
    }

    // REQUIRES: there must be at least 1 element in questions
    // MODIFIES: this
    // EFFECTS: returns a random key from questions map and the answer, and removes the value from the map
    public String getQuestion() {
        return null; // stub
    }

    // EFFECTS: returns the answer (value) based on the given key
    public String getAnswer(String key) {
        return null; // stub
    }

    // EFFECTS: returns true if the user's response is equal to the actual answer, false otherwise
    public boolean isResponseCorrect() {
        return false; // stub
    }

    public void addQuestion(String key, String value) {
        this.getQuestions().put(key, value);
    }


    // getters
    public TypingPractice getTypingPractice() {
        return typingPractice;
    }

    public Map<String, String> getQuestions() {
        return null; // stub
    }

    public String getUserResponse() {
        return userResponse;
    }


    // setters
    public void setUserResponse(String response) {
        userResponse = response;
    }
}
