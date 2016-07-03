package Data_Project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Lappie on 3/22/2015.
 */
public class weerhistorie {
    // week weer data
    private double Tempmin7;
    private double Tempmin6;
    private double Tempmin5;
    private double Tempmin4;
    private double Tempmin3;
    private double Tempmin2;
    private double Tempmin1;
    private double Tempmin0;

    Calendar cal = Calendar.getInstance();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    ArrayList<Double> testar = new ArrayList();


    //SQL
    private dbConnect connect = new dbConnect();

    public weerhistorie() throws Exception {
        //set dates
        try {
            Connection con = connect.connectToDb();
            Statement statement = con.createStatement();
            String sql = "SELECT Temperatuur FROM mijnma1q_PrjData.Weersvoorspelling where Datum = " + "'" + dateFormat.format(cal.getTime()) + "'";
            cal.add(Calendar.DATE, -1);
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                Tempmin0 = rs.getDouble(1);
            }
            sql = "SELECT Temperatuur FROM mijnma1q_PrjData.Weersvoorspelling where datum = " + "'" + dateFormat.format(cal.getTime()) + "'";
            rs = statement.executeQuery(sql);
            cal.add(Calendar.DATE, -1);

            if (rs.next()) {
                Tempmin1 = rs.getDouble(1);
            }
            sql = "SELECT Temperatuur FROM mijnma1q_PrjData.Weersvoorspelling where datum = " + "'" + dateFormat.format(cal.getTime()) + "'";
            rs = statement.executeQuery(sql);
            cal.add(Calendar.DATE, -1);
            if (rs.next()) {
                Tempmin2 = rs.getDouble(1);
            }
            sql = "SELECT Temperatuur FROM mijnma1q_PrjData.Weersvoorspelling where datum = " + "'" + dateFormat.format(cal.getTime()) + "'";
            rs = statement.executeQuery(sql);
            cal.add(Calendar.DATE, -1);
            if (rs.next()) {
                Tempmin3 = rs.getDouble(1);
            }
            sql = "SELECT Temperatuur FROM mijnma1q_PrjData.Weersvoorspelling where datum = " + "'" + dateFormat.format(cal.getTime()) + "'";
            rs = statement.executeQuery(sql);
            cal.add(Calendar.DATE, -1);
            if (rs.next()) {
                Tempmin4 = rs.getDouble(1);
            }
            sql = "SELECT Temperatuur FROM mijnma1q_PrjData.Weersvoorspelling where datum = " + "'" + dateFormat.format(cal.getTime()) + "'";
            rs = statement.executeQuery(sql);
            cal.add(Calendar.DATE, -1);
            if (rs.next()) {
                Tempmin5 = rs.getDouble(1);
            }
            sql = "SELECT Temperatuur FROM mijnma1q_PrjData.Weersvoorspelling where datum = " + "'" + dateFormat.format(cal.getTime()) + "'";
            rs = statement.executeQuery(sql);
            cal.add(Calendar.DATE, -1);
            if (rs.next()) {
                Tempmin6 = rs.getDouble(1);
            }
            con.close();
        } catch (SQLException e ) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public double getminD1() {
        return Tempmin1;
    }
    public double getminD2(){
        return Tempmin2;
    }
    public double getminD3(){
        return Tempmin3;
    }
    public double getminD4() {
        return Tempmin4;
    }
    public double getminD5(){
        return Tempmin5;
    }
    public double getminD6(){
        return Tempmin6;
    }
    public double getToday(){
        return Tempmin0;
    }
}
