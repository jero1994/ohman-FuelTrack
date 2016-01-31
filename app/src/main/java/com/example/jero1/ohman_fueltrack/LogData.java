package com.example.jero1.ohman_fueltrack;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.Buffer;
import java.util.ArrayList;

/**
 * Created by jero1 on 2016-01-30.
 */
public class LogData {
    /*
    The module for storing the full set of data from the log file.
    The list of entries and the total cost calculation are available from this module.
     */
    private static ArrayList<LogEntry> log = new ArrayList<>();

    public static void LoadLog(ArrayList<LogEntry> logEntries) {
        log = logEntries;
    }

    public static ArrayList<LogEntry> GetLog() {
        return log;
    }

    public static void AddLogEntry(LogEntry entry) {
        log.add(entry);
    }
    public static LogEntry GetEntryAt(int i) {
        return log.get(i);
    }

    public static double GetTotalCost() {
        double result = 0;
        for (LogEntry logEntry : log) { result += logEntry.GetCost(); }
        return result;
    }
}
