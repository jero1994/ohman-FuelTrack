package com.example.jero1.ohman_fueltrack;

import android.test.ActivityInstrumentationTestCase2;

import java.util.Date;

/**
 * Created by jero1 on 2016-01-31.
 */
public class LogEntryTest extends ActivityInstrumentationTestCase2 {
    public LogEntryTest() { super(LogViewActivity.class); }

    public void testGetters() {
        Date date = new Date(1000000);
        LogEntry testEntry = new LogEntry(date, "A", 1, "B", 2, 3);
        assertEquals(testEntry.getDate(), date);
        assertTrue(testEntry.getStation() == "A");
        assertTrue(testEntry.getOdometer() == 1);
        assertTrue(testEntry.getGrade() == "B");
        assertTrue(testEntry.getAmount() == 2);
        assertTrue(testEntry.getUnitCost() == 3);
    }

    public void testSetters() {
        Date date = new Date(1000000);
        Date date2 = new Date(2000000);
        Date date3 = new Date(30000000);
        LogEntry testEntry = new LogEntry(date, "A", 1, "B", 2, 3);
        LogEntry testEntry2 = new LogEntry(date2, "C", 4, "D", 5, 6);
        assertEquals(testEntry.getDate(), date);
        assertTrue(testEntry.getStation() == "A");
        assertTrue(testEntry.getOdometer() == 1);
        assertTrue(testEntry.getGrade() == "B");
        assertTrue(testEntry.getAmount() == 2);
        assertTrue(testEntry.getUnitCost() == 3);
        testEntry.Set(testEntry2);
        assertEquals(testEntry.getDate(), date2);
        assertTrue(testEntry.getStation() == "C");
        assertTrue(testEntry.getOdometer() == 4);
        assertTrue(testEntry.getGrade() == "D");
        assertTrue(testEntry.getAmount() == 5);
        assertTrue(testEntry.getUnitCost() == 6);
        testEntry.setDate(date3);
        testEntry.setStation("E");
        testEntry.setOdometer(7);
        testEntry.setGrade("F");
        testEntry.setAmount(8);
        testEntry.setUnitCost(9);
        assertEquals(testEntry.getDate(), date3);
        assertTrue(testEntry.getStation() == "E");
        assertTrue(testEntry.getOdometer() == 7);
        assertTrue(testEntry.getGrade() == "F");
        assertTrue(testEntry.getAmount() == 8);
        assertTrue(testEntry.getUnitCost() == 9);
    }


}
