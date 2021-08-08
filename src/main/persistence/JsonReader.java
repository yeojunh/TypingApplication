package persistence;

import model.History;
import model.TypingPractice;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads history from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads history from file and returns it
    //          and throws IOException if an error occurs reading data from file
    public History read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseHistory(jsonObject);
    }

    // EFFECTS: returns source file as string
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses history from JSON object and returns it
    private History parseHistory(JSONObject jsonObject) {
        History history = new History();
        addTypingPractices(history, jsonObject);
        return history;
    }

    // MODIFIES: history
    // EFFECTS: parses typing practices from JSON object and adds them to History
    private void addTypingPractices(History history, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("history");
        for (Object json: jsonArray) {
            JSONObject nextTypingPractice = (JSONObject) json;
            addTypingPractice(history, nextTypingPractice);
        }
    }

    // MODIFIES: history
    // EFFECTS: parses typing practice from JSON object and adds it to history
    private void addTypingPractice(History history, JSONObject jsonObject) {
        TypingPractice typingPractice = new TypingPractice(jsonObject.getString("focus"));
        typingPractice.setWpm(jsonObject.getDouble("wpm"));
        typingPractice.setAccuracy(jsonObject.getDouble("accuracy"));
        history.addUserHistory(typingPractice);
        history.calculateAverageTypingSpeed();
        history.calculateAverageAccuracy();
    }
}
