package model;

import exception.EmptyStringException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TypingPracticeTest {
    public TypingPractice testTyping;
    public TypingPractice testPunctuation;
    public TypingPractice testNumber;

    @BeforeEach
    public void setup() {
        testTyping = new TypingPractice("overall");
    }

    @Test
    public void testChoosePhraseToTypeOverall() {
        String phraseToType = testTyping.choosePhraseToType("overall");
        assertTrue(testTyping.getOverallPrac().contains(phraseToType));
    }

    @Test
    public void testChoosePhraseToTypeShort() {
        String phraseToType = testTyping.choosePhraseToType("short");
        assertTrue(testTyping.getShortPrac().contains(phraseToType));
    }

    @Test
    public void testChoosePhraseToTypePunctuation() {
        String phraseToType = testTyping.choosePhraseToType("punctuation");
        assertTrue(testTyping.getPunctuationPrac().contains(phraseToType));
    }

    @Test
    public void testChoosePhraseToTypeNumber() {
        String phraseToType = testTyping.choosePhraseToType("number");
        assertTrue(testTyping.getNumberPrac().contains(phraseToType));
    }

    // just check with contains? not sure if i can redo the random part
    @Test
    public void testChoosePhraseByFocusOverall() {
        testTyping.setupOverallPhrases();
        testTyping.choosePhraseByFocus(testTyping.getOverallPrac());
        assertFalse(testTyping.getPhraseToType() == null);
        assertTrue(testTyping.getOverallPrac().contains(testTyping.getPhraseToType()));
    }

    @Test
    public void testChoosePhraseByFocusShort() {
        testTyping.setupShortPhrases();
        testTyping.choosePhraseByFocus(testTyping.getShortPrac());
        assertFalse(testTyping.getPhraseToType() == null);
        assertTrue(testTyping.getShortPrac().contains(testTyping.getPhraseToType()));
    }

    @Test
    public void testChoosePhraseByFocusNumber() {
        testTyping.setupNumberPhrases();
        testTyping.choosePhraseByFocus(testTyping.getNumberPrac());
        assertFalse(testTyping.getPhraseToType() == null);
        assertTrue(testTyping.getNumberPrac().contains(testTyping.getPhraseToType()));
    }

    @Test
    public void testChoosePhraseByFocusPunctuation() {
        testTyping.setupPunctuationPhrases();
        testTyping.choosePhraseByFocus(testTyping.getPunctuationPrac());
        assertFalse(testTyping.getPhraseToType() == null);
        assertTrue(testTyping.getPunctuationPrac().contains(testTyping.getPhraseToType()));
    }

    @Test
    public void testSetupOverallPhrases() {
        testTyping.setupOverallPhrases();
        assertEquals(5, testTyping.getOverallPrac().size());
        assertTrue(testTyping.getOverallPrac().contains("He walked down the steps from the train station in a bit of "
                + "a hurry knowing the secrets in the "
                + "briefcase must be secured as quickly as possible. Bounding down the steps, he heard "
                + "something behind him and quickly turned in a panic. There was nobody there but a pair of "
                + "old worn-out shoes were placed neatly on the steps he had just come down."
                + "He was about to turn and be on his way when a deep chill filled his body.\n"));
        assertTrue(testTyping.getOverallPrac().contains("One dollar and eighty-seven cents. That was all. "
                + "And sixty cents of it was in pennies. Pennies saved "
                + "one and two at a time by bulldozing the grocer and the vegetable man and the butcher until "
                + "one’s cheeks burned with the silent imputation of parsimony that such close dealing "
                + "implied. One dollar and eighty-seven cents. And the next day would be Christmas...\n"));
        assertTrue(testTyping.getOverallPrac().contains("Sometimes it's the first moment of the day that catches "
                + "you off guard. That's what Wendy was thinking."
                + " She opened her window to see fire engines screeching down the street. "
                + "While this wasn't something completely unheard of, it also wasn't normal. "
                + "It was a sure sign of what was going to happen that day.\n"));
        assertTrue(testTyping.getOverallPrac().contains("He took a sip of the drink. He wasn't sure whether "
                + "he liked it or not, but at this moment it didn't "
                + "matter. She had made it especially for him so he would have forced it down even if he had "
                + "absolutely hated it. That's simply the way things worked. She made him a new-fangled drink "
                + "each day and he took a sip of it and smiled, saying it was excellent.\n"));
        assertTrue(testTyping.getOverallPrac().contains("There was a time when he would have embraced the "
                + "change that was coming. In his youth, he sought "
                + "adventure and the unknown, but that had been years ago. He wished he could go back and "
                + "find the excitement that came with change but it was useless. That curiosity had long left "
                + "him to where he had come to loathe anything that put him out of his comfort zone.\n"));
    }

    @Test
    public void testSetupShortPhrases() {
        testTyping.setupShortPhrases();
        assertEquals(4, testTyping.getShortPrac().size());
        assertTrue(testTyping.getShortPrac().contains("The quick brown fox jumps over the lazy dog."));
        assertTrue(testTyping.getShortPrac().contains("Trust the natural recursion."));
        assertTrue(testTyping.getShortPrac().contains("A foolish man is lactose intolerant. "
                + "A wise man simply tolerates it."));
        assertTrue(testTyping.getShortPrac().contains("Cats are rebellious creatures, "
                + "but cows... cows are docile creatures."));
    }

    // TODO: shorten this somehow
    @Test
    public void testSetupPunctuationPhrases() {
        testTyping.setupPunctuationPhrases();
        assertEquals(5, testTyping.getPunctuationPrac().size());
        assertTrue(testTyping.getPunctuationPrac().contains("Facing his greatest fear, he ate his first marshmallow. "
                + "It was a slippery slope and he was willing "
                + "to 'slide' all the way to the deepest depths. My biggest joy is roasting almonds while "
                + "stalking prey! Why are you never at home on Sundays? Tom got a small piece of pie.\n"));
        assertTrue(testTyping.getPunctuationPrac().contains("It was difficult for Mary to admit that most of her "
                + "workout consisted of - well - poor judgment. "
                + "As he looked out the window, he saw a clown walk by. There's a message for you if you look "
                + "up. Never underestimate the willingness of the greedy to throw you under the bus! "
                + "Doris enjoyed tapping her nails on the table to annoy everyone.\n"));
        assertTrue(testTyping.getPunctuationPrac().contains("At that moment he wasn't listening to music, "
                + "he was living an experience. The miniature pet elephant "
                + "became the envy of the neighborhood. Please tell me you don't work in a morgue! "
                + "When he asked her favorite number, she answered without hesitation that it was diamonds. "
                + "The swirled lollipop had issues with the pop rock candy.\n"));
        assertTrue(testTyping.getPunctuationPrac().contains("He had \"accidentally\" hacked into his company's server."
                + " Excitement replaced fear until the "
                + "final moment. As he entered the church, he could hear the soft voice of someone whispering "
                + "into a cell phone. It was the first time he had ever seen someone cook dinner on an... "
                + "elephant? It's obvious she is hungry, sweaty, and tired.\n"));
        assertTrue(testTyping.getPunctuationPrac().contains( "Van life is difficult with 2 cats and a dog. "
                + "It must be five o'clock somewhere! "
                + "He figured a few sticks of dynamite were easier than a fishing pole to catch fish. "
                + "As the rental car rolled to a stop on the dark road, her fear increased by the moment. "
                + "The waves were crashing on the shore; it was a lovely sight.\n"));
    }

    @Test
    public void testSetupNumberPhrases() {
        testTyping.setupNumberPhrases();
        assertEquals(3, testTyping.getNumberPrac().size());
        assertTrue(testTyping.getNumberPrac().contains("The tart lemonade cost 3 dollars and 79 cents. "
                + "Our zoo showcases 5 killer whales and 23 emperor penguins. "
                + "Well, he tried to eat 40 pancakes this morning, but he could only eat 5 before "
                + "he got absolutely disgusted by the thought of eating anymore.\n"));
        assertTrue(testTyping.getNumberPrac().contains("The award-winning game, The Witcher 3: Wild Hunt, "
                + "had over 1.5 million pre-orders "
                + "even before its release. It sold 67, 385 copies in the first week and 4 million copies "
                + "in the second week. By the end of 2019, "
                + "the game had sold over 40 million copies worldwide.\n"));
        assertTrue(testTyping.getNumberPrac().contains("The Witcher 3: Wild Hunt received the best RPG game "
                + "at the IGN Best of E3 Awards in 2013 and 2014. "
                + "It received 260 game of the year awards and remained the most awarded game of all time "
                + "until 2021, when it was overtaken by The Last of Us Part II.\n"));
    }

    @Test
    public void testCountdown() {
        // countdown() works the same regardless of focus
        try {
            testTyping.countdown();
        } catch (InterruptedException e) {
            fail();
        }
        assertTrue(testTyping.getIsTyping());
    }

    @Test
    public void testDetermineNumWordsAttemptedZero() {
        testTyping.setPhraseToType("");
        testTyping.determineNumWordsAttempted();
        assertEquals(0, testTyping.getNumWordsAttempted());
    }

    @Test
    public void testDetermineNumWordsAttemptedSpace() {
        testTyping.setPhraseToType(" ");
        testTyping.determineNumWordsAttempted();
        assertEquals(0, testTyping.getNumWordsAttempted());
    }

    @Test
    public void testDetermineNumWordsAttemptedNormal() {
        testTyping.setPhraseToType("Set phrase to type... Yes");
        testTyping.determineNumWordsAttempted();
        assertEquals(5, testTyping.getNumWordsAttempted());
    }

    @Test
    public void testDetermineNumWordsTypedIncorrectlyAllCorrect() {
        testTyping.setPhraseToType("Set phrase to type... Yes");
        testTyping.setUserTypingInput("Set phrase to type... Yes");
        testTyping.determineNumWordsTypedIncorrectly();
        assertEquals(0, testTyping.getNumWordsTypedIncorrectly());
    }

    @Test
    public void testSetupWordsAndArrayListsEmptyString() {
        testTyping.setPhraseToType("Hello, world!");
        try {
            testTyping.setupWordsAndArrayLists("");
            fail("an empty string input would throw an exception");
        } catch (EmptyStringException e) {
            // do nothing. this exception is expected
        }
    }

    @Test
    public void testSetupWordsAndArrayListOneWordNoSpaces() {
        testTyping.setPhraseToType("Hello, world!");
        try {
            testTyping.setupWordsAndArrayLists("Hello");
        } catch (EmptyStringException e) {
            fail("It is not supposed to throw an exception");
        }
        List<String> answer = new ArrayList<String>();
        answer.add("Hello");
        assertEquals(answer, testTyping.getUserTypedInWords());
    }

    @Test
    public void testSetupWordsAndArrayListSentence() {
        testTyping.setPhraseToType("Hello, world!");
        try {
            testTyping.setupWordsAndArrayLists("Hello, world!");
        } catch (EmptyStringException e) {
            fail("It is not supposed to throw an exception");
        }
        List<String> answer = new ArrayList<String>();
        answer.add("Hello,");
        answer.add("world!");
        assertEquals(answer, testTyping.getUserTypedInWords());
    }

    @Test
    public void testDetermineNumWordsTypedIncorrectlyNoneTyped() {
        testTyping.setPhraseToType("Set phrase to type... Yes");
        testTyping.setUserTypingInput("");
        testTyping.determineNumWordsTypedIncorrectly();
        assertEquals(5, testTyping.getNumWordsTypedIncorrectly());
    }

    @Test
    public void testDetermineNumWordsTypedIncorrectlyAllIncorrect() {
        testTyping.setPhraseToType("Set phrase to type... Yes");
        testTyping.setUserTypingInput("No Yes No Yes No");
        testTyping.determineNumWordsTypedIncorrectly();
        assertEquals(5, testTyping.getNumWordsTypedIncorrectly());
    }

    @Test
    public void testDetermineNumWordsTypedIncorrectlySlightTypo() {
        testTyping.setPhraseToType("Set phrase to type... Yes");
        testTyping.setUserTypingInput("Set phrase to type... Yep");
        testTyping.determineNumWordsTypedIncorrectly();
        assertEquals(1, testTyping.getNumWordsTypedIncorrectly());
    }

    @Test
    public void testDetermineNumWordsTypedIncorrectlyPunctuationDifference() {
        testTyping.setPhraseToType("Set phrase to type... Yes");
        testTyping.setUserTypingInput("Set phrase to type.. Yes");
        testTyping.determineNumWordsTypedIncorrectly();
        assertEquals(1, testTyping.getNumWordsTypedIncorrectly());
    }

    // depends on how we're throwing exceptions/specifications
    // TODO: DECIDE ON THIS
    @Test
    public void testFinishedTyping() {
        testTyping.setIsTyping(true);
        testTyping.finishedTyping();
        assertFalse(testTyping.getIsTyping());
        assertTrue(testTyping.getEndTime() > 0);
    }

    @Test
    public void testFinishedTypingAlreadyFinished() {
        testTyping.setIsTyping(false);
        testTyping.finishedTyping();
        assertFalse(testTyping.getIsTyping());
    }

    @Test
    public void testStartedTyping() {
        testTyping.setIsTyping(false);
        testTyping.startedTyping();
        assertTrue(testTyping.getIsTyping());
        assertTrue(testTyping.getStartTime() > 0);
    }

    @Test
    public void testCalculateTypingAccuracy100Percent() {
        testTyping.setPhraseToType("Hello, world!");
        testTyping.setUserTypingInput("Hello, world!");
        assertEquals(100, testTyping.calculateAccuracy());
    }

    @Test
    public void testCalculateTypingAccuracyOneCharacterMissing() {
        testTyping.setPhraseToType("Hello, world!");
        testTyping.setUserTypingInput("Hello, world");
        assertEquals(50, testTyping.calculateAccuracy());
    }

    @Test
    public void testCalculateTypingAccuracyOneCharacterTooMany() {
        testTyping.setPhraseToType("Hello, world!");
        testTyping.setUserTypingInput("Hello, world!!");
        assertEquals(50, testTyping.calculateAccuracy());
    }

    @Test
    public void testCalculateTypingAccuracyEmptyInput() {
        testTyping.setPhraseToType("Hello, world!");
        testTyping.setUserTypingInput("");
        assertEquals(0, testTyping.calculateAccuracy());
    }

    @Test
    public void testCalculateTypingAccuracyOnlyGivenOneWord() {
        testTyping.setPhraseToType("Hello, world!");
        testTyping.setUserTypingInput("Hello");
        assertEquals(50.0, testTyping.calculateAccuracy());
    }

    @Test
    public void testCalculateTypingAccuracyGotMoreWrongThanAttempted() {
        testTyping.setPhraseToType("Hello, world!");
        testTyping.setUserTypingInput("ahhh hhhh hlksdjflkj");
        assertEquals(0, testTyping.getAccuracy());
    }

    @Test
    public void testCalculateTypingSpeedZero() {
        testTyping.setPhraseToType("Hello, world!");
        testTyping.setUserTypingInput("");
        testTyping.setTimeElapsed(10);
        testTyping.setNumWordsTyped(0);
        assertEquals(0.0, testTyping.calculateTypingSpeed());
    }

    @Test
    public void testCalculateTypingSpeedMiddle() {
        testTyping.setPhraseToType("Hello, world!");
        testTyping.setUserTypingInput("Hello, world!");
        testTyping.setTimeElapsed(10);
        assertEquals(0.2, testTyping.calculateTypingSpeed());
    }

    // testing setters
    @Test
    public void testSetIsTypingFalseToTrue() {
        assertFalse(testTyping.getIsTyping());
        testTyping.setIsTyping(true);
        assertTrue(testTyping.getIsTyping());
    }

    @Test
    public void testSetIsTypingTrueToFalse() {
        testTyping.startedTyping();
        assertTrue(testTyping.getIsTyping());
        testTyping.setIsTyping(false);
        assertFalse(testTyping.getIsTyping());
    }

    @Test
    public void testSetWpm() {
        testTyping.setWpm(100);
        assertEquals(100, testTyping.getWpm());
        testTyping.setWpm(45);
        assertEquals(45, testTyping.getWpm());
    }

    @Test
    public void testSetAccuracy() {
        testTyping.setAccuracy(100);
        assertEquals(100, testTyping.getAccuracy());
        testTyping.setAccuracy(45);
        assertEquals(45, testTyping.getAccuracy());
    }
}