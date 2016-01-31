package com.example.jero1.ohman_fueltrack;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jero1 on 2016-01-31.
 */
public class LogDataTest extends ActivityInstrumentationTestCase2 {
    public LogDataTest() { super(LogViewActivity.class); }

    public void testLoadLog() {
        ArrayList<LogEntry> testLog = new ArrayList<LogEntry>();
        LogData.LoadLog(testLog);
        assertEquals(LogData.GetLog(), testLog);
    }

    public void testGetLog() {
        ArrayList<LogEntry> testLog = new ArrayList<LogEntry>();
        LogData.LoadLog(testLog);
        assertEquals(LogData.GetLog(), testLog);
    }

    public void testAddEntry() {
        ArrayList<LogEntry> testLog = new ArrayList<LogEntry>();
        LogEntry testEntry = new LogEntry(new Date(), "", 0, "", 0, 0);
        LogData.LoadLog(testLog);
        assertTrue(testLog.size() == 0);
        LogData.AddLogEntry(testEntry);
        assertTrue(testLog.size() == 1);
        assertEquals(testLog.get(0), testEntry);
    }

    public void testGetEntryAt() {
        ArrayList<LogEntry> testLog = new ArrayList<LogEntry>();
        LogEntry testEntry = new LogEntry(new Date(), "", 0, "", 0, 0);
        LogData.LoadLog(testLog);
        LogData.AddLogEntry(testEntry);
        LogEntry testEntry2 = LogData.GetEntryAt(0);
        assertEquals(testEntry2, testEntry);
    }

    public void testGetTotalCost() {
        ArrayList<LogEntry> testLog = new ArrayList<LogEntry>();
        LogData.LoadLog(testLog);
        LogEntry testEntry = new LogEntry(new Date(), "", 0, "", 20, 20);
        LogEntry testEntry2 = new LogEntry(new Date(), "", 0, "", 30, 30);
        assertTrue(LogData.GetTotalCost() == 0);
        LogData.AddLogEntry(testEntry);
        assertTrue(LogData.GetTotalCost() == 4);
        LogData.AddLogEntry(testEntry2);
        assertTrue(LogData.GetTotalCost() == 13);
    }
}
