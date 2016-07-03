package info.androidhive.materialnavbar;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by mark on 21-5-15.
 */
public class Fact implements Serializable{
    String Name = "";
    String Description = "";
    Date date = null;

    public Fact(String name, String description) {
        Name = name;
        Description = description;
    }

    public Fact(String name, String description, Date factDate) {
        Name = name;
        Description = description;
        date = factDate;
    }
    public Fact(String description)
    {
        Description = description;
    }


    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public Date getDate() {
        return date;
    }
}
