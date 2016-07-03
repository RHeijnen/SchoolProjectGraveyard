package info.androidhive.materialnavbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Class is responsible for the Date Object
 * Based on the Java util Library
 */
public class AndroidDate {
    private Date dateObject = null;
    private String dayNumber = null;
    private String monthNumber = null;
    private String yearNumber = null;
    private String dayName = null;

    public String getFullmonthNumber() {
        return fullmonthNumber;
    }

    private String fullmonthNumber = null;

    public String getDayName() {
        return dayName;
    }

    public String getYearNumber() {
        return yearNumber;
    }

    public String getDayNumber() {
        return dayNumber;
    }

    public String getMonthName() {
        return monthNumber;
    }

    public AndroidDate()

    {
        dateObject = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd",Locale.ENGLISH);
        DateFormat monthFormat = new SimpleDateFormat("MMMM",Locale.ENGLISH);
        DateFormat yearFormat = new SimpleDateFormat("y", Locale.ENGLISH);
        DateFormat dayNameFormat = new SimpleDateFormat("E",Locale.ENGLISH);
        DateFormat monthNumberFullFormat = new SimpleDateFormat("M");

        fullmonthNumber = monthNumberFullFormat.format(dateObject);
        dayNumber = dateFormat.format(dateObject);
        monthNumber = monthFormat.format(dateObject);
        yearNumber = yearFormat.format(dateObject);
        dayName = dayNameFormat.format(dateObject);
    }
}
