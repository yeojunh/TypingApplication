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
            // pass
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
            TypingPractice tp1 = new TypingPractice("short");
            tp1.setWpm(90);
            tp1.setAccuracy(100);
            TypingPractice tp2 = new TypingPractice("short");
            tp2.setWpm(80);
            tp2.setAccuracy(90);

            record.addUserHistory(tp1);
            record.addUserHistory(tp2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(record);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            record = reader.read();
            List<TypingPractice> tps = record.getUserHistory();
            assertEquals(2, tps.size());
            checkRecord(90, 100, tps.get(0));
            checkRecord(80, 90, tps.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
