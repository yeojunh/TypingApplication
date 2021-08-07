package persistence;

import model.Record;
import model.TypingPractice;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOError;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads record from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads record from file and returns it
    //          and throws IOException if an error occurs reading data from file
    public Record read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRecord(jsonObject);
    }

    // EFFECTS: returns source file as string
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses record from JSON object and returns it
    private Record parseRecord(JSONObject jsonObject) {
        Record record = new Record();
        addTypingPractices(record, jsonObject);
        return record;
    }

    // MODIFIES: record
    // EFFECTS: parses typing practices from JSON object and adds them to Record
    private void addTypingPractices(Record record, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("history");
        for (Object json: jsonArray) {
            JSONObject nextTypingPractice = (JSONObject) json;
            addTypingPractice(record, nextTypingPractice);
        }
    }

    // MODIFIES: record
    // EFFECTS: parses typing practice from JSON object and adds it to record
    private void addTypingPractice(Record record, JSONObject jsonObject) {
        TypingPractice typingPractice = new TypingPractice(jsonObject.getString("focus"));
        typingPractice.setWpm(jsonObject.getDouble("wpm"));
        typingPractice.setAccuracy(jsonObject.getDouble("accuracy"));
        record.addUserHistory(typingPractice);
        record.calculateAverageTypingSpeed();
        record.calculateAverageAccuracy();
    }
}
