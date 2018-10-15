package edu.mgrace31gatech.donationtracker.app.model;

import java.time.LocalTime;

public class Donation {
    private String comments;
    private String long_description;
    private String name;
    private String short_description;
    private LocalTime time;
    private double value;

    public Donation(String name, String short_description, String long_description, double value,
                    String comments, LocalTime time) {
        this.name = name;
        this.short_description = short_description;
        this.long_description = long_description;
        this.value = value;
        this.comments = comments;
        this.time = time;
    }

    public Donation(String name, String short_description, String long_description,
                    double value, LocalTime time) {
        this(name, short_description, long_description, value, "", time);
    }

    public java.lang.String getComments() { return comments; }

    public String getLongDescription() { return long_description; }

    public String getName() { return name; }

    public String getShortDescription() { return short_description; }

    public String getTime() { return time.toString(); }

    public double getValue() { return value; }
}
