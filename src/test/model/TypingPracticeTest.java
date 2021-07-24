package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypingPracticeTest {
    public TypingPractice testOverall;
    public TypingPractice testPunctuation;
    public TypingPractice testNumber;

    @BeforeEach
    public void setup() {
        testOverall = new TypingPractice("overall");
        testPunctuation = new TypingPractice("punctuation");
        testNumber = new TypingPractice("number");
    }

//     commenting them out for now because they're harder to test
//     going to wait until implementation
    @Test
    public void testGetPhraseOverall() {

    }

    @Test
    public void testGetPhrasePunctuation() {

    }

    @Test
    public void testGetPhraseNumber() {

    }

    @Test
    public void testCountdown() {
        // countdown() works the same regardless of focus
        testOverall.countdown();
        assertTrue(testOverall.getIsTyping());
    }

    // depends on how we're throwing exceptions/specifications
    // TODO: DECIDE ON THIS
    @Test
    public void testFinishedTyping() {
        testOverall.setIsTyping(true);
        testOverall.finishedTyping();
        assertFalse(testOverall.getIsTyping());
        assertFalse(testOverall.getStopwatch());
    }

    @Test
    public void testFinishedTypingAlreadyFinished() {
        testOverall.setIsTyping(false);
        testOverall.finishedTyping();
        assertFalse(testOverall.getIsTyping());
        assertFalse(testOverall.getStopwatch());
    }

    @Test
    public void testStartedTyping() {
        testOverall.setIsTyping(false);
        assertTrue(testOverall.getIsTyping());
        assertTrue(testOverall.getStopwatch());
    }

    @Test
    public void testCalculateTypingSpeed100Percent() {
        testOverall.setNumCharAttempted(100);
        testOverall.setNumCharTypedIncorrectly(0);
        testOverall.calculateAccuracy();
        assertEquals(100, testOverall.getAccuracy());
    }

    @Test
    public void testCalculateTypingSpeed99Percent() {
        testOverall.setNumCharAttempted(100);
        testOverall.setNumCharTypedIncorrectly(1);
        testOverall.calculateAccuracy();
        assertEquals(99, testOverall.getAccuracy());
    }

    @Test
    public void testCalculateTypingSpeed0Percent() {
        testOverall.setNumCharAttempted(100);
        testOverall.setNumCharTypedIncorrectly(100);
        testOverall.calculateAccuracy();
        assertEquals(0, testOverall.getAccuracy());
    }

    @Test
    public void testCalculateTypingSpeed1Percent() {
        testOverall.setNumCharAttempted(100);
        testOverall.setNumCharTypedIncorrectly(99);
        testOverall.calculateAccuracy();
        assertEquals(1, testOverall.getAccuracy());
    }

    @Test
    public void testCalculateTypingSpeed50Percent() {
        testOverall.setNumCharAttempted(100);
        testOverall.setNumCharTypedIncorrectly(50);
        testOverall.calculateAccuracy();
        assertEquals(50, testOverall.getAccuracy());
    }

    @Test
    public void testCalculateTypingSpeedGotMoreWrongThanAttempted() {
        testOverall.setNumCharAttempted(100);
        testOverall.setNumCharTypedIncorrectly(110);
        testOverall.calculateAccuracy();
        assertEquals(0, testOverall.getAccuracy());
    }

    @Test
    public void testCalculateTypingSpeedZero() {
        testOverall.setTimeElapsed(10);
        testOverall.setNumWordsTyped(0);
        assertEquals(0.0, testOverall.calculateTypingSpeed());
    }

    @Test
    public void testCalculateTypingSpeedMiddle() {
        testOverall.setTimeElapsed(10);
        testOverall.setNumWordsTyped(100);
        assertEquals(10.0, testOverall.calculateTypingSpeed());
    }

    @Test
    public void testCalculateTypingSpeedDecimal() {
        testOverall.setTimeElapsed(10);
        testOverall.setNumWordsTyped(9999);
        assertEquals(999.9, testOverall.calculateTypingSpeed());
    }

    @Test
    public void testCalculateAccuracyZero() {
        testOverall.setNumCharAttempted(100);
        testOverall.setNumCharTypedIncorrectly(100);
        assertEquals(0.0, testOverall.calculateAccuracy());
    }

    @Test
    public void testCalculateAccuracyMoreWrongThanAttempted() {
        testOverall.setNumCharAttempted(100);
        testOverall.setNumCharTypedIncorrectly(110);
        assertEquals(0.0, testOverall.calculateAccuracy());
    }

    @Test
    public void testCalculateAccuracy100() {
        testOverall.setNumCharAttempted(100);
        testOverall.setNumCharTypedIncorrectly(0);
        assertEquals(100.0, testOverall.calculateAccuracy());
    }

    @Test
    public void testCalculateAccuracyMiddle() {
        testOverall.setNumCharAttempted(100);
        testOverall.setNumCharTypedIncorrectly(45);
        assertEquals(45.0, testOverall.calculateAccuracy());
    }

    // testing setters
    public void testSetIsTypingFalseToTrue() {
        assertFalse(testOverall.getIsTyping());
        testOverall.setIsTyping(true);
        assertTrue(testOverall.getIsTyping());
    }

    public void testSetIsTypingTrueToFalse() {
        assertTrue(testOverall.getIsTyping());
        testOverall.setIsTyping(false);
        assertFalse(testOverall.getIsTyping());
    }

    public void testSetWpm() {
        testOverall.setWpm(100);
        assertEquals(100, testOverall.getWpm());
        testOverall.setWpm(45);
        assertEquals(45, testOverall.getWpm());
    }

    public void testSetAccuracy() {
        testOverall.setAccuracy(100);
        assertEquals(100, testOverall.getAccuracy());
        testOverall.setAccuracy(45);
        assertEquals(45, testOverall.getAccuracy());
    }
}