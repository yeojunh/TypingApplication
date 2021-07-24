package model;

import java.util.List;

// find a new name for this class
public class Phrase {
    private String focus;           // overall, punctuation, numbers
    private String phraseToType;
    private List<String> overallPrac;
    private List<String> punctuationPrac;
    private List<String> numberPrac;
    private int phraseNum;

    // EFFECTS: constructs a new phrase for the user to type
    public Phrase(String focus) {
        // stub
        // call a method for that given genre
        // that calls a helper method (universal) to select a random string
        // out of a set of given random phrases
    }

    // MODIFIES: this
    // EFFECTS: gets a random phrase for the user to type based on the given focus
    public String getPhraseToType(String focus) {
        return ""; // stub
    }

    // MODIFIES: this
    // EFFECTS: instantiates sentencePrac
    public void addItemsToOverallPrac() {
        // stub
        // add items to sentencePrac here, then getPhraseToType will choose a random one
        // maybe get length of sentencePrac and create a random number that is <= length
        // then take the value in that integer
    }

    // MODIFIES: this
    // EFFECTS: instantiates punctuationPrac
    public void addItemsToPunctuationPrac() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: instantiates numberPrac
    public void addItemsToNumberPrac() {
        // stub
    }

}
