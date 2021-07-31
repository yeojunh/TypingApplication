package persistence;

import model.Record;
import model.TypingPractice;
import org.json.JSONArray;
import org.json.JSONObject;
import ui.TypingApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer that writes JSON representation of record to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer and throws FileNotFoundException
    //          if destination file cannot be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of TypingPractice to file
    public void write(Record rec) {
        JSONObject json = rec.toJson();
        saveToFile(json.toString(TAB));
    }

//    public void clear(Record rec) {
//        JSONArray jsonArray = rec.toJson().getJSONArray("history");
//        for (int i = 0; i < jsonArray.length(); i++) {
//            jsonArray.remove(i);
//        }
//        write(rec);
//    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }
}
