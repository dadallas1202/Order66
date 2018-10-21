package edu.mgrace31gatech.donationtracker.app.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class Donation {

    public static List<String> categories = Arrays.asList("Clothes", "Hat", "Kitchen", "Electronics", "Household", "Other");
    private String category;
    private String comments;
    private LocalDate date;
    private String long_description;
    private String name;
    private String short_description;
    private LocalTime time;
    private double value;
    private int id = 0;

    public Donation(String name, String short_description, String long_description, double value, String category,
                    String comments, LocalTime time, LocalDate date) {
        this.name = name;
        this.short_description = short_description;
        this.long_description = long_description;
        this.value = value;
        this.category = category;
        this.comments = comments;
        this.time = time;
        this.date = date;
        this.id ++;
    }

    public Donation(String name, String short_description, String long_description,
                    double value, String category, LocalTime time, LocalDate date) {
        this(name, short_description, long_description, value, category,"", time, date);
    }

    public String getCategory() { return category; }

    public String getComments() { return comments; }
    
    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy");
        return dateFormat.format(date);
    }

    public String getLongDescription() { return long_description; }

    public String getName() { return name; }

    public String getShortDescription() { return short_description; }

    public String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        return dateFormat.format(time);
    }

    public double getValue() { return value; }

    public int getId() { return id; }
}
