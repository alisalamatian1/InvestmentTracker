package ui;

import model.Event;
import model.EventLog;

// A class for printing into the printLog
public class LogPrinter {

    // EFFECTS: printing the elements in the log
    public void printLog(EventLog eventLog) {
        System.out.println("Print log:");
        for (Event event : eventLog) {
            System.out.println(event.toString());
        }
    }
}
