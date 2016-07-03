package Data_Project;

import java.sql.*;


/**
 * Created by Mark on 14-2-2015.
 * The test user :
 * username : user2
 * password : pass
 */

public class authorizedUser extends crypt{
    private crypt encryptAES = new crypt();
    public authorizedUser(){};
    public authorizedUser(String name, String pass, String rl)
    {
        username= name;
        password= pass;
        role= rl;
    }

    Boolean authorized = false;
    private String username =null;
    private String password =null;
    private String role =null;
    private String dbRole = null;
    public void updatePassword(String username1, String password1)
    {
        try {
            Connection conn = new dbConnect().connectToDb();
            Statement statement;
            String sql = "UPDATE Leden SET `password` = '" + password1 + "' WHERE username = '" + username1 + "'";
            statement = conn.prepareStatement(sql);
            statement.execute(sql);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateRole(String username1, String nRole)
    {
        try {
            Connection conn = new dbConnect().connectToDb();
            Statement statement;
            String sql = "UPDATE Leden SET `role` = '" + nRole + "' WHERE username = '" + username1 + "'";
            statement = conn.prepareStatement(sql);
            statement.execute(sql);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateUsername(String oldUsername, String replUsername)
    {
        //System.out.println(oldUsername + "\n" + replUsername);
        try {
            Connection conn = new dbConnect().connectToDb();
            Statement statement;
            String sql = "UPDATE Leden SET `username` = '" + replUsername + "' WHERE username = '" + oldUsername + "'";
            statement = conn.prepareStatement(sql);
            statement.execute(sql);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean checkCredentials(String username1, String notEncPassword) {
        Connection conn = null;
        String dbPass = null;



        String password = null;

        try {
            password = encryptAES.encrypt(notEncPassword);
            conn = new dbConnect().connectToDb();
            Statement statement = conn.createStatement();
            String sql;
            sql = "SELECT password,role FROM Leden WHERE username='" + username1 + "'";
            //execute sql query
            ResultSet rs = statement.executeQuery(sql);
            //process result of query
            while (rs.next()) {
                dbPass = rs.getString("password");
                dbRole = rs.getString("role");

            }
            //close database connection
            conn.close();

        } catch (SQLException e) {//throwed when there is an sql exception
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {//throwed when com.mysql.jdbc.Driver is not found

            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (dbPass != null && dbPass.equals(password)) {//when password is not null and equals entered password : log user in
            authorized = true;
            username = username1;

            return true;//returned to loginForm to close the login screen
        } else {

            return false;//returned to loginForm to show error message
        }
    }
    public String getUsername()
    {
        return username;
    }

    public String getPassword() {return password;}
    public String getRole() {return role;}
    public String getDecryptPass()
    {
        String x = "";
        try {
            x = decrypt(password);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return x;
    }
    public String getDBRole() {
        return dbRole;
    }


}

