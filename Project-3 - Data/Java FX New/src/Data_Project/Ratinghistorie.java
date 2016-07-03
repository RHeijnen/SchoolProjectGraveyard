package Data_Project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Lappie on 3/26/2015.
 */
public class Ratinghistorie {
    private Double Ratingvandaag;
    private Double Ratingmin1;
    private Double Ratingmin2;
    private Double Ratingmin3;
    private Double Ratingmin4;
    private Double Ratingmin5;
    private Double Ratingmin6;

    private dbConnect connect = new dbConnect();
    Calendar cal = Calendar.getInstance();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    public Ratinghistorie() throws Exception {

        try {
            Connection con = connect.connectToDb();
            Statement statement = con.createStatement();
            String sql = "SELECT AVG(positief) AS Positief,datum FROM Bericht where Datum = " + "'" + dateFormat.format(cal.getTime()) + "'";
            ResultSet rs = statement.executeQuery(sql);
            cal.add(Calendar.DATE, -1);
            if (rs.next()) {
                Ratingvandaag = rs.getDouble(1);
            }
            sql = "SELECT AVG(positief) AS Positief,datum FROM Bericht where Datum = " + "'" + dateFormat.format(cal.getTime()) + "'";
            rs = statement.executeQuery(sql);
            cal.add(Calendar.DATE, -1);
            if (rs.next()) {
                Ratingmin1 = rs.getDouble(1);
            }
            sql = "SELECT AVG(positief) AS Positief,datum FROM Bericht where Datum = " + "'" + dateFormat.format(cal.getTime()) + "'";
            rs = statement.executeQuery(sql);
            cal.add(Calendar.DATE, -1);
            if (rs.next()) {
                Ratingmin2 = rs.getDouble(1);
            }
            sql = "SELECT AVG(positief) AS Positief,datum FROM Bericht where Datum = " + "'" + dateFormat.format(cal.getTime()) + "'";
            rs = statement.executeQuery(sql);
            cal.add(Calendar.DATE, -1);
            if (rs.next()) {
                Ratingmin3 = rs.getDouble(1);
            }
            sql = "SELECT AVG(positief) AS Positief,datum FROM Bericht where Datum = " + "'" + dateFormat.format(cal.getTime()) + "'";
            rs = statement.executeQuery(sql);
            cal.add(Calendar.DATE, -1);
            if (rs.next()) {
                Ratingmin4 = rs.getDouble(1);
            }
            sql = "SELECT AVG(positief) AS Positief,datum FROM Bericht where Datum = " + "'" + dateFormat.format(cal.getTime()) + "'";
            rs = statement.executeQuery(sql);
            cal.add(Calendar.DATE, -1);
            if (rs.next()) {
                Ratingmin5 = rs.getDouble(1);
            }
            sql = "SELECT AVG(positief) AS Positief,datum FROM Bericht where Datum = " + "'" + dateFormat.format(cal.getTime()) + "'";
            rs = statement.executeQuery(sql);
            cal.add(Calendar.DATE, -1);
            if (rs.next()) {
                Ratingmin6 = rs.getDouble(1);
            }
            con.close();

        }catch (Exception e) {
            e.printStackTrace();

        }
    }

    public Double getRtoday() {
        return Ratingvandaag;
    }
    public Double getRmin1(){
        return Ratingmin1;
    }
    public Double getRmin2(){
        return Ratingmin2;
    }
    public Double getRmin3(){
        return Ratingmin3;
    }
    public Double getRmin4(){
        return Ratingmin4;
    }
    public Double getRmin5(){
        return Ratingmin5;
    }
    public Double getRmin6(){
        return Ratingmin6;
    }
}
