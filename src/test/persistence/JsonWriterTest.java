package persistence;

import model.History;
import model.TypingPractice;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            History history = new History();
            JsonWriter writer = new JsonWriter("./data/jsonTest/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // all good!
        }
    }

    @Test
    void testWriterEmptyHistory() {
        try {
            History history = new History();
            JsonWriter writer = new JsonWriter("./data/jsonTest/testWriterEmptyHistory.json");
            writer.open();
            writer.write(history);
            writer.close();

            JsonReader reader = new JsonReader("./data/jsonTest/testWriterEmptyHistory.json");
            history = reader.read();
            assertEquals(0, history.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralHistory() {
        try {
            History history = new History();
            TypingPractice tp1 = new TypingPractice("regular");
            tp1.setWpm(90);
            tp1.setAccuracy(100);
            TypingPractice tp2 = new TypingPractice("short");
            tp2.setWpm(80);
            tp2.setAccuracy(90);
            TypingPractice tp3 = new TypingPractice("punctuation");
            tp3.setWpm(123);
            tp3.setAccuracy(80);
            TypingPractice tp4 = new TypingPractice("number");
            tp4.setWpm(100);
            tp4.setAccuracy(90);

            history.addUserHistory(tp1);
            history.addUserHistory(tp2);
            history.addUserHistory(tp3);
            history.addUserHistory(tp4);
            JsonWriter writer = new JsonWriter("./data/jsonTest/testWriterGeneralHistory.json");
            writer.open();
            writer.write(history);
            writer.close();

            JsonReader reader = new JsonReader("./data/jsonTest/testWriterGeneralHistory.json");
            history = reader.read();
            List<TypingPractice> tps = history.getUserHistory();
            assertEquals(4, tps.size());
            checkHistory(90, 100, "regular", tps.get(0));
            checkHistory(80, 90, "short", tps.get(1));
            checkHistory(123, 80, "punctuation", tps.get(2));
            checkHistory(100, 90, "number", tps.get(3));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
