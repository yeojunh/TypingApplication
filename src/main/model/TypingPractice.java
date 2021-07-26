package model;

import exception.EmptyStringException;

import java.util.*;

// TODO: add specifications for all methods
// Represents a typing practice run with a specific focus (regular, short, punctuation, numbers)
// Records the run's typing speed (in wpm) and accuracy
public class TypingPractice {
    private double wpm;
    private double accuracy;
    private String focus;                   // regular, short punctuation, numbers
    private boolean isTyping;               // true if the user can type, false otherwise
    private String phraseToType;
    private int numWordsTyped;              // for wpm calculation
    private double timeElapsed;             // for wpm calculation, in minutes
    private int numWordsAttempted;           // for accuracy calculation
    private int numWordsTypedIncorrectly;    // for accuracy calculation
    private double startTime;
    private double endTime;

    private List<String> regularPrac = new ArrayList<String>();
    private List<String> shortPrac = new ArrayList<String>();
    private List<String> punctuationPrac = new ArrayList<String>();
    private List<String> numberPrac = new ArrayList<String>();

    private ArrayList<String> phraseToTypeInWords = new ArrayList<String>();
    private ArrayList<String> userTypedInWords = new ArrayList<String>();
    private String userTypingInput;

    // EFFECTS: creates a new typing practice given the focus of typing test and whether it is timed
    public TypingPractice(String focus) {
        this.focus = focus;
    }

    // MODIFIES: this
    // EFFECTS: begins timer and sets isTyping to true
    public void startedTyping() {
        startTime = System.currentTimeMillis();
        isTyping = true;
    }

    // MODIFIES: this
    // EFFECTS: stops timer, sets isTyping to false and calculates timeElapsed in minutes
    public void finishedTyping() {
        endTime = System.currentTimeMillis();
        isTyping = false;
        timeElapsed = (endTime - startTime) / 1000 / 60;
    }

    // MODIFIES: this
    // EFFECTS: calculates the typing speed of this run
    public double calculateTypingSpeed() {
        this.determineNumWordsTyped();
        if (numWordsTyped == 0) {
            wpm = 0.0;
        } else {
            wpm = numWordsTyped / timeElapsed;
        }
        wpm = roundToTwoDecimalPlaces(wpm);
        return wpm;
    }

    // MODIFIES: this
    // EFFECTS: calculates the accuracy of this run
    public double calculateAccuracy() {
        this.determineNumWordsAttempted();
        this.determineNumWordsTypedIncorrectly();

        if (numWordsTypedIncorrectly >= numWordsAttempted) {
            accuracy = 0;
        } else {
            double pointsLost = (numWordsTypedIncorrectly * 100) / (numWordsAttempted);
            accuracy = 100.0 - pointsLost;
        }
        accuracy = roundToTwoDecimalPlaces(accuracy);
        return accuracy;
    }

    // EFFECTS: returns the given num rounded to 2 decimal places
    public double roundToTwoDecimalPlaces(double num) {
        num = num * 100;
        num = Math.round(num);
        num = num / 100;
        return num;
    }

    // MODIFIES: this
    // EFFECTS: determines the number of words the user was prompted to type
    public void determineNumWordsAttempted() {
        if (phraseToType.equals("")) {
            numWordsAttempted = 0;
        } else if (phraseToType.equals(" ")) {
            numWordsAttempted = 0;
        } else {
            String[] phraseToTypeWords = phraseToType.split(" ");
            numWordsAttempted = phraseToTypeWords.length;
        }
    }

    // MODIFIES: this
    // EFFECTS: determines the number of words the user typed
    public void determineNumWordsTyped() {
        if (userTypingInput.equals("") || userTypingInput.equals(" ")) {
            numWordsTyped = 0;
        } else if (!userTypingInput.contains(" ")) {
            numWordsTyped = 1;
        } else {
            String[] userTypeIntoWords = userTypingInput.split(" ");
            numWordsTyped = userTypeIntoWords.length;
        }
    }

    // MODIFIES: this
    // EFFECTS: determines the number of words the user typed incorrectly
    public void determineNumWordsTypedIncorrectly() {
        try {
            this.setupWordsAndArrayLists(userTypingInput);
        } catch (EmptyStringException e) {
            numWordsTypedIncorrectly = phraseToTypeInWords.size();
            return;
        }
        this.comparePhraseToTypeAndUserTyped();
    }

    // helper for determineNumWordsTypedIncorrectly
    // MODIFIES: this
    // EFFECTS: calculates the number of words the user typed incorrectly
    public void comparePhraseToTypeAndUserTyped() {
        if (phraseToTypeInWords.size() == userTypedInWords.size()) {
            for (int i = 0; i < phraseToTypeInWords.size(); i++) {
                if (!(phraseToTypeInWords.get(i).equals(userTypedInWords.get(i)))) {
                    numWordsTypedIncorrectly++;
                }
            }
        } else if (phraseToTypeInWords.size() > userTypedInWords.size()) { // user typed too few words
            numWordsTypedIncorrectly += phraseToTypeInWords.size() - userTypedInWords.size();
            for (int i = 0; i < userTypedInWords.size(); i++) {
                if (!phraseToTypeInWords.contains(userTypedInWords.get(i))) {
                    numWordsTypedIncorrectly++;
                }
            }
        } else { // phraseToTypeWords.size() < userTypedWords.size(), user typed too many words
            for (int i = 0; i < phraseToTypeInWords.size(); i++) {
                if (!userTypedInWords.contains(phraseToTypeInWords.get(i))) {
                    numWordsTypedIncorrectly++;
                }
            }
            numWordsTypedIncorrectly += userTypedInWords.size() - phraseToTypeInWords.size();
        }
    }

    // helper
    // MODIFIES: this
    // EFFECTS: splits phraseToType by words into phraseToTypeInWords
    public void setupWordsAndArrayLists(String userTyped) throws EmptyStringException {
        if (userTyped.equals("")) {
            String[] phraseToTypeWords0 = phraseToType.split(" ");
            for (String next : phraseToTypeWords0) {
                phraseToTypeInWords.add(next);
            }
            throw new EmptyStringException();
        } else if (!userTyped.contains(" ")) {
            String[] phraseToTypeWords0 = phraseToType.split(" ");
            for (String next : phraseToTypeWords0) {
                phraseToTypeInWords.add(next);
            }
            userTypedInWords.add(userTyped);
        } else {
            String[] phraseToTypeWords0 = phraseToType.split(" ");
            String[] userTypedWords0 = userTyped.split(" ");
            for (String next : phraseToTypeWords0) {
                phraseToTypeInWords.add(next);
            } // put the word into ArrayList - ArrayList is easier to work with than Array
            for (String next : userTypedWords0) {
                userTypedInWords.add(next);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a list of all phrases available to type for regularPrac
    // phrase setup for each focus. hard coded phrases
    public void setupRegularPhrases() {
        String[] regularPhrases = new String[]{
                "He walked down the steps from the train station in a bit of a hurry knowing the secrets in the "
                        + "briefcase must be secured as quickly as possible.\n",
                "Bounding down the steps, he heard something behind him and quickly turned in a panic. "
                        + "There was nobody there but a pair of "
                        + "old worn-out shoes were placed neatly on the steps he had just come down.\n",
                "One dollar and eighty-seven cents. That was all. And sixty cents of it was in pennies. Pennies saved "
                        + "one and two at a time by bulldozing the grocer and the vegetable man and the butcher.\n",
                "Sometimes it's the first moment of the day that catches you off guard. That's what Wendy was thinking."
                        + " She opened her window to see fire engines screeching down the street.\n",
                "There was a time when he would have embraced the change that was coming. In his youth, he sought "
                        + "adventure and the unknown, but that had been years ago.\n",
                "He wished he could go back and "
                        + "find the excitement that came with change but it was useless. That curiosity had long left "
                        + "him to where he had come to loathe anything that put him out of his comfort zone.\n"};
        regularPrac.addAll(Arrays.asList(regularPhrases));
    }

    // MODIFIES: this
    // EFFECTS: adds a list of all phrases available to type for shortPrac
    public void setupShortPhrases() {
        String[] shortPhrases = new String[] {
                "The quick brown fox jumps over the lazy dog.",
                "Trust the natural recursion.",
                "A foolish man is lactose intolerant. A wise man simply tolerates it.",
                "Cats are rebellious creatures, but cows... cows are docile creatures."};
        shortPrac.addAll(Arrays.asList(shortPhrases));
    }

    // MODIFIES: this
    // EFFECTS: adds a list of all phrases available to type for punctuationPrac
    public void setupPunctuationPhrases() {
        String[] punctuationPhrases = new String[] {
                "Facing his greatest fear, he ate his first marshmallow! It was a slippery slope, but was he willing "
                        + "to 'slide' all the way to the deepest depths?\n",
                "Sit down and cross your legs, please! How big you have grown - I can't even believe it!\n",
                "He had \"accidentally\" hacked into his company's server. Excitement replaced fear, guilt, and shame "
                        + "until the final moment.\n",
                "It must be five o'clock somewhere! "
                        + "The waves were crashing on the shore; it was a lovely sight.\n"};
        punctuationPrac.addAll(Arrays.asList(punctuationPhrases));
    }

    // MODIFIES: this
    // EFFECTS: adds a list of all phrases available to type for numberPrac
    public void setupNumberPhrases() {
        String[] numberPhrases = new String[] {
                "The tart lemonade cost 3 dollars and 79 cents. "
                        + "Our zoo showcases 5 killer whales and 23 emperor penguins.\n",
                "Well, he tried to eat 40 pancakes this morning, but he could only eat 5 before "
                        + "he got absolutely disgusted by the thought of eating any more.\n",
                "The award-winning game, The Witcher 3: Wild Hunt, had over 1.5 million pre-orders "
                        + "even before its release. It sold 67, 385 copies in the first week and 4 million copies"
                        + "in the second week. By the end of 2019, "
                        + "the game had sold over 40 million copies worldwide.\n",
                "The Witcher 3: Wild Hunt received the best RPG game at the IGN Best of E3 Awards in 2013 and 2014. "
                        + "It received 260 game of the year awards and remained the most awarded game of all time "
                        + "until 2021, when it was overtaken by The Last of Us Part II.\n"};
        numberPrac.addAll(Arrays.asList(numberPhrases));
    }

    // MODIFIES: this
    // EFFECTS: choose a random phrase for the user to type based on the given focus
    public String choosePhraseToType(String focus) {
        if (focus.equals("regular")) {
            this.setupRegularPhrases();
            choosePhraseByFocus(regularPrac);
        } else if (focus.equals("short")) {
            this.setupShortPhrases();
            choosePhraseByFocus(shortPrac);
        } else if (focus.equals("punctuation")) {
            this.setupPunctuationPhrases();
            choosePhraseByFocus(punctuationPrac);
        } else {
            this.setupNumberPhrases();
            choosePhraseByFocus(numberPrac);
        }
        return phraseToType;
    }

    // MODIFIES: this
    // EFFECTS: chooses a random phrase to give the user based on the given list of possible phrases
    public void choosePhraseByFocus(List<String> focus) {
        int phraseNum;
        int focusLength = focus.size();
        Random random = new Random();

        phraseNum = random.nextInt(focusLength);
        phraseToType = focus.get(phraseNum);
    }


    // getters
    public List<String> getRegularPrac() {
        return regularPrac;
    }

    public List<String> getShortPrac() {
        return shortPrac;
    }

    public List<String> getPunctuationPrac() {
        return punctuationPrac;
    }

    public List<String> getNumberPrac() {
        return numberPrac;
    }

    public boolean getIsTyping() {
        return isTyping;
    }

    public double getWpm() {
        return wpm;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public int getNumWordsTyped() {
        return numWordsTyped;
    }

    public int getNumWordsAttempted() {
        return numWordsAttempted;
    }

    public int getNumWordsTypedIncorrectly() {
        return numWordsTypedIncorrectly;
    }

    public double getStartTime() {
        return startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public String getPhraseToType() {
        return phraseToType;
    }

    public ArrayList<String> getUserTypedInWords() {
        return userTypedInWords;
    }


    // setters
    public void setIsTyping(boolean isTyping) {
        this.isTyping = isTyping;
    }

    public void setWpm(double wpm) {
        this.wpm = wpm;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public void setNumWordsTyped(int num) {
        this.numWordsTyped = num;
    }

    public void setNumWordsAttempted(int num) {
        this.numWordsAttempted = num;
    }

    public void setNumWordsTypedIncorrectly(int num) {
        this.numWordsTypedIncorrectly = num;
    }

    public void setTimeElapsed(double num) {
        this.timeElapsed = num;
    }

    public void setUserTypingInput(String str) {
        userTypingInput = str;
    }

    public void setPhraseToType(String str) {
        phraseToType = str;
    }

    public void setPhraseToTypeInWords(ArrayList<String> arr) {
        phraseToTypeInWords = arr;
    }

    public void setUserTypedInWords(ArrayList<String> arr) {
        userTypedInWords = arr;
    }
}
