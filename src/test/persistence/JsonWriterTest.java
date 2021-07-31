package persistence;

import model.Record;
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
            Record record = new Record();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // all good!
        }
    }

    @Test
    void testWriterEmptyRecord() {
        try {
            Record record = new Record();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(record);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            record = reader.read();
            assertEquals(0, record.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Record record = new Record();
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

            record.addUserHistory(tp1);
            record.addUserHistory(tp2);
            record.addUserHistory(tp3);
            record.addUserHistory(tp4);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(record);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            record = reader.read();
            List<TypingPractice> tps = record.getUserHistory();
            assertEquals(4, tps.size());
            checkRecord(90, 100, "regular", tps.get(0));
            checkRecord(80, 90, "short", tps.get(1));
            checkRecord(123, 80, "punctuation", tps.get(2));
            checkRecord(100, 90, "number", tps.get(3));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
