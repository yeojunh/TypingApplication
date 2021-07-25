package model;

import exception.EmptyStringException;

import java.util.*;
import java.util.concurrent.TimeUnit;

// TODO: add class level comments
// TODO: do we need to test setters and getters
// TODO: add specifications for all methods
public class TypingPractice {
    private double wpm;
    private double accuracy;
    private String focus;                   // overall, punctuation, numbers
    private boolean isTyping;               // true if the user can type, false otherwise
    private String phraseToType;
    private int numWordsTyped;              // for wpm calculation
    private double timeElapsed;             // for wpm calculation, in minutes
    private int numWordsAttempted;           // for accuracy calculation
    private int numWordsTypedIncorrectly;    // for accuracy calculation
    private long startTime;
    private long endTime;

    private List<String> overallPrac = new ArrayList<String>();
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



    // MODIFIES: this, stopwatch
    // EFFECTS: counts down 3 seconds until the user can type and starts stopwatch
    public void countdown() throws InterruptedException {
        System.out.println("3");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("2");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("1");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("GO!");

        this.startedTyping();
        // more information about this exception here:
        // https://stackoverflow.com/questions/1087475/when-does-javas-thread-sleep-throw-interruptedexception
    }

    // REQUIRES: isTyping must be false
    // MODIFIES: this
    // EFFECTS: begins timer and sets isTyping to true
    public void startedTyping() {
        startTime = System.currentTimeMillis();
        isTyping = true;
    }

    // TODO: decide on this and exceptions
    // REQUIRE: isTyping must be true
    // MODIFIES: this
    // EFFECTS: stops timer and sets isTyping to false
    public void finishedTyping() {
        endTime = System.currentTimeMillis();
        isTyping = false;
        timeElapsed = (endTime - startTime) / 1000;
    }

    // EFFECTS: calculates the typing speed of this run
    public double calculateTypingSpeed() {
        this.determineNumWordsTyped();
        if (numWordsTyped == 0) {
            wpm = 0.0;
        } else {
            wpm = numWordsTyped / timeElapsed;
        }
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
        return accuracy;
    }
    // TODO: public or private?

    public void determineNumWordsAttempted() {
        if (phraseToType.equals("") || phraseToType.equals(" ")) {
            numWordsAttempted = 0;
        } else {
            String[] phraseToTypeWords = phraseToType.split(" ");
            numWordsAttempted = phraseToTypeWords.length;
        }
    }

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

    public void determineNumWordsTypedIncorrectly() {
        try {
            this.setupWordsAndArrayLists(userTypingInput);
        } catch (EmptyStringException e) {
            numWordsTypedIncorrectly = phraseToTypeInWords.size();
            return;
        }

        if (phraseToTypeInWords.size() == userTypedInWords.size()) {
            for (int i = 0; i < phraseToTypeInWords.size(); i++) {
                if (!(phraseToTypeInWords.get(i).equals(userTypedInWords.get(i)))) {
                    numWordsTypedIncorrectly++;
                }
            }
        } else if (phraseToTypeInWords.size() > userTypedInWords.size()) { // user typed too few words
            for (int i = 0; i < phraseToTypeInWords.size() - 1; i++) {
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
        }
    }

    // helper
    //
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
//    private int determineNumCharTypedIncorrectly(String userInput) {
//        String[] phraseToTypeSplitIntoWords = phraseToType.split(" ");
//        String[] userInputSplitIntoWords = userInput.split(" ");
//        char lastChar = 'a';
//
//        if (phraseToType.equals(userInput)) {
//            return 0;
//        } else if (phraseToTypeSplitIntoWords.length == userInputSplitIntoWords.length) {
//            // if they have the same number of words, then the spacings were relatively correct
//            // iterate through each word and see if they're equal
//            for (int i = 0; i < phraseToTypeSplitIntoWords.length; i++) {
//                if (!phraseToTypeSplitIntoWords[i].equals(userInputSplitIntoWords[i])) {
//                    char[] wordIntoChar = phraseToTypeSplitIntoWords[i].toCharArray();
//                    char[] userInputWordIntoChar = userInputSplitIntoWords[i].toCharArray();
//
//                    wordIntoChar.checkCharAccuracy(userInputWordIntoChar);
//
//                    if (wordIntoChar.length == userInputWordIntoChar.length) {
//                        for (int k = 0; k < wordIntoChar.length; k++) {
//                            if (!(wordIntoChar[k] == userInputWordIntoChar[k])) {
//                                numCharTypedIncorrectly++;
//                            }
//                        }
//                    } else if (wordIntoChar.length > userInputWordIntoChar.length) {
//                        for (int k = 0; k < wordIntoChar.length; k++) {
//                            if (!(wordIntoChar[k] == userInputWordIntoChar[k])) {
//                                numCharTypedIncorrectly++;
//                                if (lastChar == wordIntoChar[k]) {
//
//                                }
//                            }
//                            lastChar = userInputWordIntoChar[k];
//                        }
//                    }
//                }
//            }
//        } else if (phraseToTypeSplitIntoWords.length > userInputSplitIntoWords.length) {
//
//        }
//    }

    // EFFECTS: checks the accuracy of the characters in a given word
//    public void checkCharAccuracy(char[] wordTypedByUser, int nthWord) {
//        ArrayList<Character> wordToType = new ArrayList<Character>();
//        char[] wordsToTypeCharArray = (this.getPhraseToType().split(" ")[nthWord].toCharArray());
//
//        for (char next: wordsToTypeCharArray) {
//            wordToType.add(wordsToTypeCharArray[next]);
//        } // put the word into ArrayList of char - ArrayList is easier to work with than Array
//
//        if (wordToType.size() == wordTypedByUser.length) {
//            for (int k = 0; k < wordToType.size(); k++) {
//                if (!(wordToType.get(k) == wordTypedByUser[k])) {
//                    numCharTypedIncorrectly++;
//                }
//            }
//        } else if (wordToType.size() > wordTypedByUser.length) { // user didn't type enough
//            for (int k = 0; k < wordToType.size(); k++) {
//                if (!(wordToType.get(k) == wordTypedByUser[k])) {
//                    // see if i remove that letter, the rest of it would make sense
//
//                    // see if i replace that letter with something else, the rest would make sense
//                }
//            }
//        }
//    }

    // phrase setup for each focus

    public void setupOverallPhrases() {
        String[] overallPhrases = new String[]{
                "He walked down the steps from the train station in a bit of a hurry knowing the secrets in the "
                        + "briefcase must be secured as quickly as possible. Bounding down the steps, he heard "
                        + "something behind him and quickly turned in a panic. There was nobody there but a pair of "
                        + "old worn-out shoes were placed neatly on the steps he had just come down."
                        + "He was about to turn and be on his way when a deep chill filled his body.\n",
                "One dollar and eighty-seven cents. That was all. And sixty cents of it was in pennies. Pennies saved "
                        + "one and two at a time by bulldozing the grocer and the vegetable man and the butcher until "
                        + "one’s cheeks burned with the silent imputation of parsimony that such close dealing "
                        + "implied. One dollar and eighty-seven cents. And the next day would be Christmas...\n",
                "Sometimes it's the first moment of the day that catches you off guard. That's what Wendy was thinking."
                        + " She opened her window to see fire engines screeching down the street. "
                        + "While this wasn't something completely unheard of, it also wasn't normal. "
                        + "It was a sure sign of what was going to happen that day.\n",
                "He took a sip of the drink. He wasn't sure whether he liked it or not, but at this moment it didn't "
                        + "matter. She had made it especially for him so he would have forced it down even if he had "
                        + "absolutely hated it. That's simply the way things worked. She made him a new-fangled drink "
                        + "each day and he took a sip of it and smiled, saying it was excellent.\n",
                "There was a time when he would have embraced the change that was coming. In his youth, he sought "
                        + "adventure and the unknown, but that had been years ago. He wished he could go back and "
                        + "find the excitement that came with change but it was useless. That curiosity had long left "
                        + "him to where he had come to loathe anything that put him out of his comfort zone.\n"};
        overallPrac.addAll(Arrays.asList(overallPhrases));
    }

    public void setupShortPhrases() {
        String[] shortPhrases = new String[] {
                "The quick brown fox jumps over the lazy dog.",
                "Trust the natural recursion.",
                "A foolish man is lactose intolerant. A wise man simply tolerates it.",
                "Cats are rebellious creatures, but cows... cows are docile creatures."};
        shortPrac.addAll(Arrays.asList(shortPhrases));
    }

    public void setupPunctuationPhrases() {
        String[] punctuationPhrases = new String[] {
                "Facing his greatest fear, he ate his first marshmallow. It was a slippery slope and he was willing "
                        + "to 'slide' all the way to the deepest depths. My biggest joy is roasting almonds while "
                        + "stalking prey! Why are you never at home on Sundays? Tom got a small piece of pie.\n",
                "It was difficult for Mary to admit that most of her workout consisted of - well - poor judgment. "
                        + "As he looked out the window, he saw a clown walk by. There's a message for you if you look "
                        + "up. Never underestimate the willingness of the greedy to throw you under the bus! "
                        + "Doris enjoyed tapping her nails on the table to annoy everyone.\n",
                "At that moment he wasn't listening to music, he was living an experience. The miniature pet elephant "
                        + "became the envy of the neighborhood. Please tell me you don't work in a morgue! "
                        + "When he asked her favorite number, she answered without hesitation that it was diamonds. "
                        + "The swirled lollipop had issues with the pop rock candy.\n",
                "He had \"accidentally\" hacked into his company's server. Excitement replaced fear until the "
                        + "final moment. As he entered the church, he could hear the soft voice of someone whispering "
                        + "into a cell phone. It was the first time he had ever seen someone cook dinner on an... "
                        + "elephant? It's obvious she is hungry, sweaty, and tired.\n",
                "Van life is difficult with 2 cats and a dog. It must be five o'clock somewhere! "
                        + "He figured a few sticks of dynamite were easier than a fishing pole to catch fish. "
                        + "As the rental car rolled to a stop on the dark road, her fear increased by the moment. "
                        + "The waves were crashing on the shore; it was a lovely sight.\n"};
        punctuationPrac.addAll(Arrays.asList(punctuationPhrases));
    }

    public void setupNumberPhrases() {
        String[] numberPhrases = new String[] {
                "The tart lemonade cost 3 dollars and 79 cents. "
                        + "Our zoo showcases 5 killer whales and 23 emperor penguins. "
                        + "Well, he tried to eat 40 pancakes this morning, but he could only eat 5 before "
                        + "he got absolutely disgusted by the thought of eating anymore.\n",
                "The award-winning game, The Witcher 3: Wild Hunt, had over 1.5 million pre-orders "
                        + "even before its release. It sold 67, 385 copies in the first week and 4 million copies "
                        + "in the second week. By the end of 2019, "
                        + "the game had sold over 40 million copies worldwide.\n",
                "The Witcher 3: Wild Hunt received the best RPG game at the IGN Best of E3 Awards in 2013 and 2014. "
                        + "It received 260 game of the year awards and remained the most awarded game of all time "
                        + "until 2021, when it was overtaken by The Last of Us Part II.\n"};
        numberPrac.addAll(Arrays.asList(numberPhrases));
    }

    // MODIFIES: this
    // EFFECTS: gets a random phrase for the user to type based on the given focus
    // MODIFIES: phraseToType, this
    // EFFECTS: fetches a phrase for the user to type based on the given focus
    // could change it to be here since they're just hard coded
    public String choosePhraseToType(String focus) {
        int focusLength;
        Random random = new Random();

        if (focus.equals("overall")) {
            this.setupOverallPhrases();
            choosePhraseByFocus(overallPrac);
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

    public void choosePhraseByFocus(List<String> focus) {
        int phraseNum;
        int focusLength = focus.size();
        Random random = new Random();

        phraseNum = random.nextInt(focusLength);
        phraseToType = focus.get(phraseNum);
    }

    public List<String> getOverallPrac() {
        return overallPrac;
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

    // getters
    // EFFECTS: returns true if isTyping is true (user can type)
    public boolean getIsTyping() {
        return isTyping;
    }

    public double getWpm() {
        return wpm;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public String getFocus() {
        return focus;
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

    public double getTimeElapsed() {
        return timeElapsed;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public String getPhraseToType() {
        return phraseToType;
    }

    public String getUserTypingInput() {
        return userTypingInput;
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
}
