package edu.mgrace31gatech.donationtracker.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a Donation.
 * @author Team: Order 66; Members: Kierra Brigman, Andrew Dallas, Marie Grace,
 * Alayna Panlilio, Julia Tang
 */
public class Donation implements Parcelable {
    public static final List<String> categories = Arrays.asList(
            "Clothes", "Hat", "Kitchen", "Electronics", "Household", "Other");
    private String category;
    private String comments;
    private LocalDate date;
    private String long_description;
    private String name;
    private String short_description;
    private LocalTime time;
    private double value;
    private static int Next_Id = 1;
    private int id;
    private int viewId;

    /**
     * Creates a donation.
     * @param name The name of the donation.
     * @param short_description The short description of the donation.
     * @param long_description The long description of the donation.
     * @param value The value of the donation.
     * @param category The category of the donation.
     * @param comments The comments of the donation.
     * @param time The time the donation was added.
     * @param date The date the donation was added.
     */
    private Donation(String name, String short_description, String long_description, double value,
                     String category, String comments, LocalTime time, LocalDate date) {
        this.name = name;
        this.short_description = short_description;
        this.long_description = long_description;
        this.value = value;
        this.category = category;
        this.comments = comments;
        this.time = time;
        this.date = date;
        this.id = Donation.Next_Id;
        Donation.Next_Id++;
    }

    /**
     * Creates a donation.
     * @param name The name of the donation.
     * @param short_description The short description of the donation.
     * @param long_description The long description of the donation.
     * @param value The value of the donation.
     * @param category The category of the donation.
     * @param time The time the donation was added.
     * @param date The date the donation was added.
     */
    public Donation(String name, String short_description, String long_description,
                    double value, String category, LocalTime time, LocalDate date) {
        this(name, short_description, long_description, value, category,"", time, date);
    }

    /**
     * Creates a donation.
     * @param name The name of the donation.
     * @param short_description The short description of the donation.
     * @param long_description The long description of the donation.
     * @param value The value of the donation.
     * @param category The category of the donation.
     * @param comments The comments of the donation.
     * @param time The time the donation was added.
     * @param date The date the donation was added.
     * @param donId The id of the donation.
     */
    public Donation(String name, String short_description, String long_description,
                    double value, String category, String comments, LocalTime time,
                    LocalDate date, int donId) {
        this.name = name;
        this.short_description = short_description;
        this.long_description = long_description;
        this.value = value;
        this.category = category;
        this.comments = comments;
        this.time = time;
        this.date = date;
        id = donId;
    }

    /**
     * No param constructor -- DO NOT CALL NORMALLY
     * This constructor only for GUI use in edit/new student dialog
     */
    public Donation() {
        this("enter new name", "NA",
                "NA", 1000, "NA", "NA", null, null);
    }

    /**
     * Creates a donation.
     * @param donation The donation created.
     */
    public Donation(Donation donation) {
        this.name = donation.name;
        this.short_description = donation.short_description;
        this.long_description = donation.long_description;
        this.value = donation.value;
        this.category = donation.category;
        this.comments = donation.comments;
        this.time = donation.time;
        this.date = donation.date;
        this.id = donation.id;
        this.viewId = donation.viewId;
    }

    /**
     * Creates a donation.
     * @param s The name of the donation.
     */
    public Donation(String s) {
        this.name = s;
    }

    /**
     * Gets the category of the donation.
     * @return The category of the donation.
     */
    public CharSequence getCategory() { return category; }

    /**
     * Sets the category of the donation.
     * @param _category The category of the donation.
     */
    public void setCategory(String _category) { category = _category; }

//    /**
//     * Gets the comments of the donation.
//     * @return The comments of the donation.
//     */
//    public String getComments() { return comments; }

    /**
     * Gets the date the donation was added.
     * @return The date the donation was added.
     */
    public CharSequence getDate() {
        return date.toString();
    }

    /**
     * Sets the date the donation was added.
     * @param _date The date the donation was added.
     */
    public void setDate(LocalDate _date) { date = _date; }

    /**
     * Gets the long description of the donation.
     * @return The long description of the donation.
     */
    public CharSequence getLongDescription() { return long_description; }

    /**
     * Sets the long description of the donation.
     * @param _longDescription The long description of the donation.
     */
    public void setLongDescription(String _longDescription) { long_description = _longDescription; }

    /**
     * Gets the name of the donation.
     * @return The name of the donation.
     */
    public String getName() { return name; }

    /**
     * Sets the name of the donation.
     * @param _name The name of the donation.
     */
    public void setName(String _name) { name = _name; }

    /**
     * Gets the short description of the donation.
     * @return The short description of the donation.
     */
    public CharSequence getShortDescription() { return short_description; }

    /**
     * Sets the short description of the donation.
     * @param _shortDescription The short description of the donation.
     */
    public void setShortDescription(String _shortDescription) {
        short_description = _shortDescription;
    }

    /**
     * Get the time the donation was added.
     * @return The time the donation was added.
     */
    public CharSequence getTime() {
        return time.toString();
    }

    /**
     * Sets the time the donation was added.
     * @param _time The time the donation was added.
     */
    public void setTime(LocalTime _time) { time = _time; }

    /**
     * Gets the value of the donation.
     * @return The value of the donation.
     */
    public double getValue() { return value; }

    /**
     * Sets the value of the donation.
     * @param _value The value of the donation.
     */
    public void setValue(double _value) { value = _value; }

    /**
     * Gets the id of the donation.
     * @return The id of the donation.
     */
    public int getId() { return id; }

    /**
     * Gets the view id of the donation.
     * @return The view id of the donation.
     */
    public int getViewId() {
        return viewId;
    }

    /**
     * Sets the view id of the donation.
     * @param viewId The view id of the donation.
     */
    public void setViewId(int viewId) {
        this.viewId = viewId;
    }


    /**
     * Converts the name of the donation to a string.
     * @return The name of the donation.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Reads in the donation parcel.
     * @param in The donation parcel.
     */
    private Donation(Parcel in) {
        name = in.readString();
        short_description = in.readString();
        long_description = in.readString();
        value = in.readDouble();
        category = in.readString();
    }

    /**
     * Describe the contents of the donation.
     * @return The number of contents.
     */
    @Override
    public int describeContents() { return 0; }

    /**
     * Write the donation to parcel.
     * @param dest Destination of parcel.
     * @param flags Number of flags.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(short_description);
        dest.writeString(long_description);
        dest.writeDouble(value);
        dest.writeString(category);
    }

    /**
     * Creates a parcel for donation.
     */
    public static final Parcelable.Creator<Donation> CREATOR
            = new Parcelable.Creator<Donation>() {
        /**
         * Creates a parcel for specific donation.
         * @param in The parameters for the donation.
         * @return The donation created.
         */
        @Override
        public Donation createFromParcel(Parcel in) {return new Donation(in); }

        /**
         * Create a new array of donations.
         * @param size The size of the array of donations.
         * @return The donation array.
         */
        @Override
        public Donation[] newArray(int size) { return new Donation[size]; }
    };
}
