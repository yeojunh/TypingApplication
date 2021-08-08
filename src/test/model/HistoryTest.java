package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HistoryTest {
    History testHistory;
    TypingPractice testTp;

    @BeforeEach
    public void setup() {
        testHistory = new History();
        testTp = new TypingPractice("overall");
        testTp.setWpm(80.0);
        testTp.setAccuracy(96.0);
    }

    @Test
    public void testAddUserHistoryOne() {
        testHistory.addUserHistory(testTp);
        List<TypingPractice> expected = new ArrayList<>();
        expected.add(testTp);
        assertEquals(expected, testHistory.getUserHistory());
    }

    @Test
    public void testAddUserHistoryMultiple() {
        TypingPractice tp2 = new TypingPractice("number");
        TypingPractice tp3 = new TypingPractice("short");
        ArrayList<TypingPractice> expected = new ArrayList<>();
        expected.add(testTp);
        expected.add(tp2);
        expected.add(tp3);
        testHistory.addUserHistory(testTp);
        testHistory.addUserHistory(tp2);
        testHistory.addUserHistory(tp3);
        assertEquals(expected, testHistory.getUserHistory());
    }

    @Test
    public void testCalculateAverageTypingSpeedNoHistory() {
        assertEquals(0.0, testHistory.calculateAverageTypingSpeed());
    }

    @Test
    public void testCalculateAverageTypingSpeedOneHistory() {
        testHistory.addUserHistory(testTp);
        assertEquals(80.0, testHistory.calculateAverageTypingSpeed());
    }

    @Test
    public void testCalculateAverageTypingSpeedMultipleTypingPracticeDouble() {
        TypingPractice tp2 = new TypingPractice("number");
        TypingPractice tp3 = new TypingPractice("short");
        tp2.setWpm(85.0);
        tp3.setWpm(90.0);
        testHistory.addUserHistory(testTp);
        testHistory.addUserHistory(tp2);
        testHistory.addUserHistory(tp3);
        assertEquals(85, testHistory.calculateAverageTypingSpeed());
    }

    @Test
    public void testCalculateAverageTypingSpeedMultipleTypingPracticeInfiniteDecimal() {
        TypingPractice tp2 = new TypingPractice("number");
        TypingPractice tp3 = new TypingPractice("short");
        tp2.setWpm(50.0);
        tp3.setWpm(90.0);
        testHistory.addUserHistory(testTp);
        testHistory.addUserHistory(tp2);
        testHistory.addUserHistory(tp3);
        assertEquals(73.33, testHistory.calculateAverageTypingSpeed());
    }

    @Test
    public void testCalculateAverageAccuracyNoHistory() {
        assertEquals(0.0, testHistory.calculateAverageAccuracy());
    }

    @Test
    public void testCalculateAverageAccuracyOneHistory() {
        testHistory.addUserHistory(testTp);
        assertEquals(96.0, testHistory.calculateAverageAccuracy());
    }

    @Test
    public void testCalculateAverageAccuracyMultipleTypingPractices() {
        TypingPractice tp2 = new TypingPractice("number");
        TypingPractice tp3 = new TypingPractice("short");
        tp2.setAccuracy(99.0);
        tp3.setAccuracy(90.0);
        testHistory.addUserHistory(testTp);
        testHistory.addUserHistory(tp2);
        testHistory.addUserHistory(tp3);
        assertEquals(95, testHistory.calculateAverageAccuracy());
    }

    @Test
    public void testCalculateAverageAccuracyMultipleTypingHistoryInfiniteDecimal() {
        TypingPractice tp2 = new TypingPractice("number");
        TypingPractice tp3 = new TypingPractice("short");
        tp2.setAccuracy(85.0);
        tp3.setAccuracy(90.0);
        testHistory.addUserHistory(testTp);
        testHistory.addUserHistory(tp2);
        testHistory.addUserHistory(tp3);
        assertEquals(90.33, testHistory.calculateAverageAccuracy());
    }

    @Test
    public void testRoundToTwoDecimalPlaces() {
        assertEquals(0, testHistory.roundToTwoDecimalPlaces(0));
        assertEquals(10.0, testHistory.roundToTwoDecimalPlaces(10));
        assertEquals(10.0, testHistory.roundToTwoDecimalPlaces(10.0001));
        assertEquals(100.12, testHistory.roundToTwoDecimalPlaces(100.123));
    }

    @Test
    public void testGetAverageWpm() {
        testTp.setWpm(100.0);
        testHistory.addUserHistory(testTp);
        testHistory.calculateAverageTypingSpeed();
        assertEquals(100.0, testHistory.getAverageWpm());
    }

    @Test
    public void testGetAverageAccuracy() {
        testTp.setAccuracy(99.0);
        testHistory.addUserHistory(testTp);
        testHistory.calculateAverageAccuracy();
        assertEquals(99.0, testHistory.getAverageAccuracy());
    }

    @Test
    public void testSizeZero() {
        assertEquals(0, testHistory.size());
    }

    @Test
    public void testSizeMultiple() {
        TypingPractice tp2 = new TypingPractice("number");
        TypingPractice tp3 = new TypingPractice("short");
        testHistory.addUserHistory(testTp);
        testHistory.addUserHistory(tp2);
        testHistory.addUserHistory(tp3);
        assertEquals(3, testHistory.size());
    }

    @Test
    public void testGetNthTypingPrac() {
        TypingPractice tp2 = new TypingPractice("number");
        TypingPractice tp3 = new TypingPractice("short");
        testHistory.addUserHistory(testTp);
        testHistory.addUserHistory(tp2);
        testHistory.addUserHistory(tp3);
        assertEquals(testTp, testHistory.getNthTypingPrac(0));
        assertEquals(tp2, testHistory.getNthTypingPrac(1));
        assertEquals(tp3, testHistory.getNthTypingPrac(2));
    }
}
