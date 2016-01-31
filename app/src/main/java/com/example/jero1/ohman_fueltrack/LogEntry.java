package com.example.jero1.ohman_fueltrack;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jero1 on 2016-01-30.
 */
public class LogEntry implements Serializable {
    /*
    A module for storing, setting, and retrieving a given log entry's data.
    The cost calculation and TextView display are also available from this module.
     */
    private Date date;
    private String station;
    private double odometer;
    private String grade;
    private double amount;
    private double unitCost;

    public LogEntry(Date date, String station, double odometer, String grade, double amount, double unitCost) {
        setDate(date);
        setStation(station);
        setOdometer(odometer);
        setGrade(grade);
        setAmount(amount);
        setUnitCost(unitCost);
    }

    public Date getDate() {
        return date;
    }
    public String getStation() {
        return station;
    }
    public double getOdometer() {
        return odometer;
    }
    public String getGrade() {
        return grade;
    }
    public double getAmount() {
        return amount;
    }
    public double getUnitCost() {
        return unitCost;
    }

    public double GetCost() {
        return this.getAmount() * this.getUnitCost() / 100; //Convert cents to dollars
    }
    public void Set(LogEntry newEntry) {
        setDate(newEntry.getDate());
        setStation(newEntry.getStation());
        setOdometer(newEntry.getOdometer());
        setGrade(newEntry.getGrade());
        setAmount(newEntry.getAmount());
        setUnitCost(newEntry.getUnitCost());
    }

    @Override
    public String toString() {
        //How the data is displayed in the ListView.
        return "Date: " + new SimpleDateFormat("yyyy-MM-dd").format(this.date)
                + "\nStation: " + this.station
                + "\nOdometer reading: " + new DecimalFormat("#0.0").format(this.odometer) + " km"
                + "\nGrade: " + this.grade
                + "\nFuel Amount: " + new DecimalFormat("#0.000").format(this.amount) + " L"
                + "\nUnit Cost: " + new DecimalFormat("#0.0").format(this.unitCost) + " cents/L"
                + "\nFuel Cost: $" + new DecimalFormat("#0.00").format(this.GetCost());
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public void setStation(String station) {
        this.station = station;
    }
    public void setOdometer(double odometer) {
        this.odometer = odometer;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }
}
