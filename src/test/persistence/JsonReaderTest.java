package persistence;

import model.History;
import model.TypingPractice;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/jsonTest/noSuchFile.json");
        try {
            History history = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // all good!
        }
    }

    @Test
    void testReaderEmptyHistory() {
        JsonReader reader = new JsonReader("./data/jsonTest/testReaderEmptyHistory.json");
        try {
            History history = reader.read();
            assertEquals(0, history.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralHistory() {
        JsonReader reader = new JsonReader("./data/jsonTest/testReaderGeneralHistory.json");
        try {
            History history = reader.read();
            List<TypingPractice> typingPractices = history.getUserHistory();
            assertEquals(2, typingPractices.size());
            checkHistory(90, 96, "regular", history.getNthTypingPrac(0));
            checkHistory(80, 50, "short", history.getNthTypingPrac(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
