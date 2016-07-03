package Data_Project;

import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Lappie on 4/3/2015.
 */
public class ResultController implements Initializable {

    //setup
    private String ResultSQL = "leeg";
    private String ResultSQL2 = "leeg";
    private dbConnect connect = new dbConnect();
    private Connection con;
    //analyse variables
    private int socialmediaSQLcount1;
    private int socialmediaSQLcount2;
    private int socialmediaSQLcount3;
 //   static int [] SocialmediaSQLresult = new int[3];
  //  static String [] SocialmediaSQLnaam = new String[3];
    static  ArrayList Socialmedialist = new ArrayList();
    static ArrayList Socialnaamlist = new ArrayList();
    static ArrayList Socialratinglist = new ArrayList();


    //Start analyse
    public ResultController() throws Exception {
        ResultSQL2 = AnalistController.SQLresult2 + " WHERE positief >= 5";
        ResultSQL = AnalistController.SQLresult;
        //  System.out.println(ResultSQL);
        setResults();
    }

    //zet analyse results
    private void setResults() throws Exception {
        con = connect.connectToDb(); // open sql connectie

        try{ // probeer de data te analyseren
            Statement statement = con.createStatement(); // maakt sql statement
            ResultSet rs = statement.executeQuery(ResultSQL);// van de string
            ResultSetMetaData rsmd = rs.getMetaData(); // haalt de collumn naam op
            int columnsNummer = rsmd.getColumnCount(); // kijkt naar het aantal rijen beschikbaar
            System.out.println("--=SQL Results=--");

            while (rs.next()) { // loop op lengte van het aantal rijen
                for (int i = 1; i <= columnsNummer; i++) {// loop op lengte van het aantal rijen
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    Socialnaamlist.add(rsmd.getColumnName(i)); // sql rij naam array
                    Socialmedialist.add(Integer.parseInt(columnValue)); // result array
                    System.out.print(Socialnaamlist.get(i - 1) + " " + Socialmedialist.get(i - 1)); // test print arrays
                }
                System.out.println("");
            }
            System.out.println("--=SQL Finished=--");
            System.out.println("Arraylist contains: " + Socialnaamlist.toString());
            System.out.println("Arraylist contains: " + Socialmedialist.toString());
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Niet alle data is gebruikt, probeer meer social media te gebruiken");
        }

        try { // probeer positiviteit te controleren - TOTAALE/GLOBAAL POSITIVITEIT
            Statement statement = con.createStatement(); // maakt sql statement
            ResultSet rs = statement.executeQuery(ResultSQL2);// van de string
            ResultSetMetaData rsmd = rs.getMetaData(); // haalt de collumn naam op
            int columnsNummer = rsmd.getColumnCount(); // kijkt naar het aantal rijen beschikbaar

            System.out.println("--=SQL positiviteit Results=--");
            while (rs.next()) { // loop op lengte van het aantal rijen
                for (int i = 1; i <= columnsNummer; i++) {// loop op lengte van het aantal rijen
                    String columnValue = rs.getString(i);
                    Socialratinglist.add(Integer.parseInt(columnValue)); // result array
                }
            }
            System.out.println(" Arraylist media: " + Socialnaamlist.toString());
            System.out.println(" Arraylist rating: " + Socialratinglist.toString()); // test print arrays
            System.out.println("--=SQL positiviteit Results Finished=--");

        }catch(Exception e){
            System.out.println("Nope rating");
        }


        con.close(); // sluit sql connectie

    }
    public void initialize(URL url, ResourceBundle rb) {
    // bla
    }

    // returners / gets



}