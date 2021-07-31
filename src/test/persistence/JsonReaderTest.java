package persistence;

import model.Record;
import model.TypingPractice;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Record rec = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // all good!
        }
    }

    @Test
    void testReaderEmptyRecord() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyHistory.json");
        try {
            Record rec = reader.read();
            assertEquals(0, rec.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralRecord() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralHistory.json");
        try {
            Record record = reader.read();
            List<TypingPractice> typingPractices = record.getUserHistory();
            assertEquals(2, typingPractices.size());
            checkRecord(90, 96, record.getNthTypingPrac(0));
            checkRecord(80, 50, record.getNthTypingPrac(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
