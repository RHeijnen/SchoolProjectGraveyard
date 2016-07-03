package Data_Project;

import java.sql.*;

/**
 * Class handles the DB connection and also the Select method to Choose the right SocialMedia
 * for the database part
 */
public class dbConnect {

    private int socialmediaID;

    public Connection connectToDb() throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver");
        //connect to database
        Connection conn = DriverManager.getConnection("jdbc:mysql://mijnmarklinbaan.nl/mijnma1q_PrjData", "mijnma1q_prjuser", "password");
        return conn;
    }

    /**
     * Generates the right SocialmediaID from the database
     * @param socialmedia : Twitter, Facebook, Google
     * @throws Exception all
     */
    public int getSocialMedia(String socialmedia){

        try {
            Connection conn = connectToDb();
            Statement statement = conn.createStatement();
            String sql = "select BerichtID from Bericht where socialmedia ='" + socialmedia + "'";
            //execute sql query
            ResultSet rs = statement.executeQuery(sql);
            //process result of query
            while (rs.next()) {
                socialmediaID = rs.getInt("BerichtID    ");
            }
            conn.close();
            return socialmediaID;
        }
        catch(Exception e) {
           System.out.println("Er gaat iets fout!");
            return 0;
        }
    }

}
