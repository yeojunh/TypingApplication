package persistence;

import model.TypingPractice;

import static org.junit.jupiter.api.Assertions.assertEquals;

// all persistence tests use reference from CPSC 210's JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonTest {
    protected void checkRecord(double wpm, double accuracy, String focus, TypingPractice tp) {
        assertEquals(wpm, tp.getWpm());
        assertEquals(accuracy, tp.getAccuracy());
        assertEquals(focus, tp.getFocus());
    }
}
