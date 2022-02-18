package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import model.UserProfileAndWallet;
import org.json.JSONArray;
import org.json.JSONObject;

// citation: this is the same code that can be found on this repository: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonWriting {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriting(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public void write(UserProfileAndWallet userProfileAndWallet) {
        JSONArray json = userProfileAndWallet.toArrayOfJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}

