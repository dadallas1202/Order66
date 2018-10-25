package edu.mgrace31gatech.donationtracker.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class Donation implements Parcelable {

    public static List<String> categories = Arrays.asList("Clothes", "Hat", "Kitchen", "Electronics", "Household", "Other");
    private String category;
    private String comments;
    private LocalDate date;
    private String long_description;
    private String name;
    private String short_description;
    private LocalTime time;
    private double value;
    private static int Next_Id = 0;
    private int id;

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
        this.id = Donation.Next_Id++;
    }

    public Donation(String name, String short_description, String long_description,
                    double value, String category, LocalTime time, LocalDate date) {
        this(name, short_description, long_description, value, category,"", time, date);
    }

    /**
     * No param constructor -- DO NOT CALL NORMALLY
     * This constructor only for GUI use in edit/new student dialog
     */
    public Donation() { this("enter new name", "NA", "NA", 1000, "NA", "NA", null, null); }

    public String getCategory() { return category; }
    public void setCategory(String _category) { category = _category; }

    public String getComments() { return comments; }

    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy");
        return dateFormat.format(date);
    }
    public void setDate(LocalDate _date) { date = _date; }

    public String getLongDescription() { return long_description; }
    public void setLongDescription(String _longDescription) { long_description = _longDescription; }

    public String getName() { return name; }
    public void setName(String _name) { name = _name; }

    public String getShortDescription() { return short_description; }
    public void setShortDescription(String _shortDescription) { short_description = _shortDescription; }

    public String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        return dateFormat.format(time);
    }
    public void setTime(LocalTime _time) { time = _time; }

    public double getValue() { return value; }
    public void setValue(double _value) { value = _value; }

    public int getId() { return id; }




    @Override
    public String toString() {
        return name;
    }

    private Donation(Parcel in) {
        name = in.readString();
        short_description = in.readString();
        long_description = in.readString();
        value = in.readDouble();
        category = in.readString();
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(short_description);
        dest.writeString(long_description);
        dest.writeDouble(value);
        dest.writeString(category);
    }

    public static final Parcelable.Creator<Donation> CREATOR
            = new Parcelable.Creator<Donation>() {
        public Donation createFromParcel(Parcel in) {return new Donation(in); }
        public Donation[] newArray(int size) { return new Donation[size]; }
    };
}
