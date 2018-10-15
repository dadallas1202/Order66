package edu.mgrace31gatech.donationtracker.app.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Donation {
    private String comments;
    private LocalDate date;
    private String long_description;
    private String name;
    private String short_description;
    private LocalTime time;
    private double value;

    public Donation(String name, String short_description, String long_description, double value,
                    String comments, LocalTime time, LocalDate date) {
        this.name = name;
        this.short_description = short_description;
        this.long_description = long_description;
        this.value = value;
        this.comments = comments;
        this.time = time;
        this.date = date;
    }

    public Donation(String name, String short_description, String long_description,
                    double value, LocalTime time, LocalDate date) {
        this(name, short_description, long_description, value, "", time, date);
    }

    public String getComments() { return comments; }
    
    public LocalDate getDate() {return date; }

    public String getLongDescription() { return long_description; }

    public String getName() { return name; }

    public String getShortDescription() { return short_description; }

    public LocalTime getTime() { return time; }

    public double getValue() { return value; }
}
