package persistence;

import org.json.JSONObject;

// Reference: CPSC 210 JsonSerializationDemo file
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}