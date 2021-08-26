package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AccuracyDisciplineManagerTest {
    private AccuracyDisciplineManager testManager;

    @BeforeEach
    public void setup() {
        testManager = new AccuracyDisciplineManager(new TypingPractice("overall"));
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testManager.getQuestionPairs().keySet().size());
        assertEquals("overall", testManager.getTypingPractice().getFocus());
        assertNull(testManager.getUserResponse());
    }

    @Test
    public void testGetQuestionPairsEmpty() {
        assertEquals(0, testManager.getQuestionPairs().keySet().size());
    }

    @Test
    public void testGetQuestionPairsMultiple() {
//        testManager.getQuestions().put("Key 1", "This is the value for key 1");
//        testManager.getQuestions().put("Key 2", "This is the value for key 2");
//        testManager.getQuestions().put("Key 3", "This is the value for key 3");
        for (int i = 1; i <= 3; i++) {
            testManager.getQuestionPairs().put("Key " + i, "This is the value for key " + i);
        }

        assertEquals(3, testManager.getQuestionPairs().keySet().size());
        assertTrue(testManager.getQuestionPairs().keySet().contains("Key 1"));
        assertTrue(testManager.getQuestionPairs().keySet().contains("Key 2"));
        assertTrue(testManager.getQuestionPairs().keySet().contains("Key 3"));

        assertEquals("This is the value for key 1", testManager.getQuestionPairs().get("Key 1"));
        assertEquals("This is the value for key 2", testManager.getQuestionPairs().get("Key 2"));
        assertEquals("This is the value for key 3", testManager.getQuestionPairs().get("Key 3"));
    }

    @Test
    public void testGetQuestionEmpty() {
        try {
            testManager.getQuestion();
            fail("Should have thrown an exception");
        } catch (Exception e) { // TODO: change to a class exception when implementing
            // all good!
        }
    }

    @Test
    public void testGetQuestionOne() {
        String retrievedQuestion = null;
        testManager.addQuestion("Key 1", "Value 1");
        try {
            retrievedQuestion = testManager.getQuestion();
        } catch (Exception e) {
            fail("Should not have thrown an exception");
        }
        assertEquals("Key 1", retrievedQuestion);

        try {
            retrievedQuestion = testManager.getQuestion();
            fail("Should have thrown an exception");
        } catch (Exception e) {
            // all good!
        }

        assertEquals("Key 1", retrievedQuestion);
    }

    @Test
    public void testGetQuestionMultiple() {
        String retrievedQuestion = null;
        testManager.addQuestion("Key 1", "Value 1");
        testManager.addQuestion("Key 2", "Value 2");

        try {
            retrievedQuestion = testManager.getQuestion();
        } catch (Exception e) {
            fail("Should not have thrown an exception");
        }
        assertEquals("Key 1", retrievedQuestion);

        try {
            retrievedQuestion = testManager.getQuestion();
        } catch (Exception e) {
            fail("Should not have thrown an exception");
        }
        assertEquals("Key 2", retrievedQuestion);

        try {
            retrievedQuestion = testManager.getQuestion();
            fail("Should have thrown an exception");
        } catch (Exception e) {
            // all good!
        }
    }

    @Test
    public void testGetQuestionsNone() {
        assertEquals(new ArrayList<>(), testManager.getQuestions());
    }

    @Test
    public void testGetQuestionsOne() {
        testManager.addQuestion("Key 1", "Value 1");
        assertEquals(1, testManager.getQuestions().size());
        assertTrue(testManager.getQuestions().contains("Key 1"));
    }

    @Test
    public void testGetQuestionsMultiple() {
        testManager.addQuestion("Key 1", "Value 1");
        testManager.addQuestion("Key 2", "Value 2");
        assertEquals(2, testManager.getQuestions().size());
        assertTrue(testManager.getQuestions().contains("Key 1"));
        assertTrue(testManager.getQuestions().contains("Key 2"));
    }
}
