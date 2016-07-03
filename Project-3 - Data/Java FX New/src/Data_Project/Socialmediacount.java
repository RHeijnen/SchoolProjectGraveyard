package Data_Project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Gebruiker on 21-3-2015.
 */
public class Socialmediacount {
    // Social media content count
    private int Googlecount;
    private int Twittercount;
    private int Facebookcount;
    private dbConnect connect = new dbConnect();


    public Socialmediacount() throws Exception {
        try {
            Connection con = connect.connectToDb();
            Statement statement = con.createStatement();
            String sql = "SELECT COUNT(socialmedia) as 'Twitter' FROM Bericht where socialmedia ='twitter'";
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()){
                Twittercount = rs.getInt(1);
            }
            sql = "SELECT COUNT(socialmedia) as 'Google' FROM Bericht where socialmedia ='google'";
            rs = statement.executeQuery(sql);
            if(rs.next()){
                Googlecount = rs.getInt(1);
            }
            sql = "SELECT COUNT(socialmedia) as 'Facebook' FROM Bericht where socialmedia ='facebook'";
            rs = statement.executeQuery(sql);
            if(rs.next()){
                Facebookcount = rs.getInt(1);
            }

            con.close();
/*            System.out.println("Social media results klaar");
            System.out.println("Twitter:" + Twittercount);
            System.out.println("Facebook:" + Facebookcount);
            System.out.println("Google:" + Googlecount);*/


        } catch (SQLException e ) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // getters
    public int getGoogle() {
        return Googlecount;
    }
    public int getTwitter(){
        return Twittercount;
    }
    public int getFacebook(){
        return Facebookcount;
    }

}
