package model;

import java.lang.reflect.Array;
import java.util.*;

// Represents the manager that handles discipline depending on the accuracy percentage the user has lost.
// Handles the series of questionnaires that appear after the user completes a typing test
// For every 20% accuracy lost, the number of questions given increases (undecided)
public class AccuracyDisciplineManager {
    private TypingPractice typingPractice;
    private Map<String, String> questionPairs; // key: question     value: answer
    private Set<String> question; // includes questions that are keys to questionPairs
    private String userResponse;
    private int questionNum;

    public AccuracyDisciplineManager(TypingPractice typingPractice) {
        this.typingPractice = typingPractice;
        questionPairs = new HashMap<>();
    }

    // REQUIRES: all keys in questionPairs must be a String
    // MODIFIES: this
    // EFFECTS: returns a list of all questions in questionPairs
    public List<String> getQuestions() {
        String[] arrayKeySet = (String[]) questionPairs.keySet().toArray(); // casting, so must only include strings
        List<String> keySets = new ArrayList<>();
        for (int i = 0; i < arrayKeySet.length; i++) {
            keySets.add(arrayKeySet[i]);
        }
        return keySets;
    }

    // REQUIRES: there must be at least 1 element in questions
    // MODIFIES: this
    // EFFECTS: returns a random key from questions map and the answer, and removes the value from the map
    public String getQuestion() {
        Random rand = new Random();
        questionNum = rand.nextInt(questionPairs.size());

        return getQuestions().get(questionNum);
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
        this.getQuestionPairs().put(key, value);
    }


    // getters
    public TypingPractice getTypingPractice() {
        return typingPractice;
    }

    public Map<String, String> getQuestionPairs() {
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
