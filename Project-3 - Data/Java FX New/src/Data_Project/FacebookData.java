package Data_Project;

import facebook4j.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * DOET NOG NIETS
 */
public class FacebookData {
    private dbConnect connect = new dbConnect();
    Connection con;
    // Facebook data
    private int sharecount;
    private int Pagelikecount;
    private ResponseList<Post> feeds;

    public FacebookData () throws FacebookException {
        Facebook facebook = new FacebookFactory().getInstance();
        feeds = facebook.getFeed("313850611958467",new Reading().limit(75));
        Page pgId = facebook.getPage("313850611958467");
        Pagelikecount = pgId.getLikes(); // aantal likes op de facebook pagina van rotterdam

        for (Post post : feeds) {
            String msg = post.getMessage();
            Integer sharecount = post.getSharesCount();
            if (sharecount == null) {
                sharecount = 0;

            }

            // database stuff
            try {
                Connection con = connect.connectToDb();
                String sql = "INSERT INTO Bericht (Datum, Beschrijving,socialmedia,Positief) VALUES (?,?,?,?)";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
                preparedStatement.setString(2, msg);
                preparedStatement.setString(3, "FaceBook");
                preparedStatement.setInt(4, 1);
                preparedStatement.execute();

                sql = "INSERT INTO facebook (Bericht_BerichtID,likes,gedeeld) VALUES (?,?,?)";
                PreparedStatement preparedStatement2 = con.prepareStatement(sql);
                preparedStatement2.setInt(1, connect.getSocialMedia("Facebook"));
                preparedStatement2.setInt(2, Pagelikecount);
                preparedStatement2.setInt(3, sharecount);
                preparedStatement2.execute();
                System.out.println("Facebook data updated");

                /**Close connection with Database **/
                con.close();
                /**Catch exception when data can't be saved into database for example: There is nothing filled in **/
            } catch (SQLException e) {
                System.out.println("geen nieuwe updates");
                e.printStackTrace();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public int getPagelike() {
        return Pagelikecount;
    }
    public int getFBShares(){
        return sharecount;
    }
    public ResponseList<Post> getFeeds(){
        return feeds;
    }

}

