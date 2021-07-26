package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecordTest {
    Record testRecord;
    TypingPractice testTp;

    @BeforeEach
    public void setup() {
        testRecord = new Record();
        testTp = new TypingPractice("overall");
        testTp.setWpm(80.0);
        testTp.setAccuracy(96.0);
    }

    @Test
    public void testAddUserHistoryOne() {
        testRecord.addUserHistory(testTp);
        List<TypingPractice> expected = new ArrayList<TypingPractice>();
        expected.add(testTp);
        assertEquals(expected, testRecord.getUserHistory());
    }

    @Test
    public void testAddUserHistoryMultiple() {
        TypingPractice tp2 = new TypingPractice("number");
        TypingPractice tp3 = new TypingPractice("short");
        ArrayList<TypingPractice> expected = new ArrayList<TypingPractice>();
        expected.add(testTp);
        expected.add(tp2);
        expected.add(tp3);
        testRecord.addUserHistory(testTp);
        testRecord.addUserHistory(tp2);
        testRecord.addUserHistory(tp3);
        assertEquals(expected, testRecord.getUserHistory());
    }

    @Test
    public void testCalculateAverageTypingSpeedNoRecords() {
        assertEquals(0.0, testRecord.calculateAverageTypingSpeed());
    }

    @Test
    public void testCalculateAverageTypingSpeedOneRecord() {
        testRecord.addUserHistory(testTp);
        assertEquals(80.0, testRecord.calculateAverageTypingSpeed());
    }

    @Test
    public void testCalculateAverageTypingSpeedMultipleRecordsDouble() {
        TypingPractice tp2 = new TypingPractice("number");
        TypingPractice tp3 = new TypingPractice("short");
        tp2.setWpm(85.0);
        tp3.setWpm(90.0);
        testRecord.addUserHistory(testTp);
        testRecord.addUserHistory(tp2);
        testRecord.addUserHistory(tp3);
        assertEquals(85, testRecord.calculateAverageTypingSpeed());
    }

    @Test
    public void testCalculateAverageTypingSpeedMultipleRecordsInfiniteDecimal() {
        TypingPractice tp2 = new TypingPractice("number");
        TypingPractice tp3 = new TypingPractice("short");
        tp2.setWpm(50.0);
        tp3.setWpm(90.0);
        testRecord.addUserHistory(testTp);
        testRecord.addUserHistory(tp2);
        testRecord.addUserHistory(tp3);
        assertEquals(73.33, testRecord.calculateAverageTypingSpeed());
    }

    @Test
    public void testCalculateAverageAccuracyNoRecord() {
        assertEquals(0.0, testRecord.calculateAverageAccuracy());
    }

    @Test
    public void testCalculateAverageAccuracyOneRecord() {
        testRecord.addUserHistory(testTp);
        assertEquals(96.0, testRecord.calculateAverageAccuracy());
    }

    @Test
    public void testCalculateAverageAccuracyMultipleRecords() {
        TypingPractice tp2 = new TypingPractice("number");
        TypingPractice tp3 = new TypingPractice("short");
        tp2.setAccuracy(99.0);
        tp3.setAccuracy(90.0);
        testRecord.addUserHistory(testTp);
        testRecord.addUserHistory(tp2);
        testRecord.addUserHistory(tp3);
        assertEquals(95, testRecord.calculateAverageAccuracy());
    }

    @Test
    public void testCalculateAverageAccuracyMultipleRecordsInfiniteDecimal() {
        TypingPractice tp2 = new TypingPractice("number");
        TypingPractice tp3 = new TypingPractice("short");
        tp2.setAccuracy(85.0);
        tp3.setAccuracy(90.0);
        testRecord.addUserHistory(testTp);
        testRecord.addUserHistory(tp2);
        testRecord.addUserHistory(tp3);
        assertEquals(90.33, testRecord.calculateAverageAccuracy());
    }

    @Test
    public void testRoundToTwoDecimalPlaces() {
        assertEquals(0, testRecord.roundToTwoDecimalPlaces(0));
        assertEquals(10.0, testRecord.roundToTwoDecimalPlaces(10));
        assertEquals(10.0, testRecord.roundToTwoDecimalPlaces(10.0001));
        assertEquals(100.12, testRecord.roundToTwoDecimalPlaces(100.123));
    }

    @Test
    public void testGetAverageWpm() {
        testTp.setWpm(100.0);
        testRecord.addUserHistory(testTp);
        testRecord.calculateAverageTypingSpeed();
        assertEquals(100.0, testRecord.getAverageWpm());
    }

    @Test
    public void testGetAverageAccuracy() {
        testTp.setAccuracy(99.0);
        testRecord.addUserHistory(testTp);
        testRecord.calculateAverageAccuracy();
        assertEquals(99.0, testRecord.getAverageAccuracy());
    }

    @Test
    public void testSizeZero() {
        assertEquals(0, testRecord.size());
    }
    @Test
    public void testSizeMulitple() {
        TypingPractice tp2 = new TypingPractice("number");
        TypingPractice tp3 = new TypingPractice("short");
        testRecord.addUserHistory(testTp);
        testRecord.addUserHistory(tp2);
        testRecord.addUserHistory(tp3);
        assertEquals(3, testRecord.size());
    }
}
