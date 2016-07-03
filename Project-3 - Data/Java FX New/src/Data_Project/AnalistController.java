package Data_Project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by bart on 10-3-2015.
 */
public class AnalistController implements Initializable {

    //Login credentials
    private final String usernameDB = "mijnma1q_prjuser";
    private final String passwordDB = "password";
    private final String url = "jdbc:mysql://mijnmarklinbaan.nl:3306/mijnma1q_PrjData";
    public ImageView weerplaatje;
    //SQL
    private weerInfo weatherInfo = new weerInfo();
    private dbConnect connect = new dbConnect();
    private Rating positief = new Rating();
    private Connection con;
    private int SQLT;
    static String SQLresult;
    static String SQLresult2 = "";

    Calendar cal = Calendar.getInstance();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public AnalistController() throws Exception {
        con = connect.connectToDb();
    }

    //fxml
    @FXML
    PieChart Piechart;
    @FXML
    public Label outputTempArea;
    @FXML
    public Label outputTempDisc;
    @FXML
    public Label outputDate;
    public ToggleGroup raiting;
    public CheckBox F1;
    public CheckBox F2;
    public CheckBox F3;
    public CheckBox W1;
    public CheckBox W2;
    public CheckBox W3;
    public CheckBox W4;
    public CheckBox W5;
    public CheckBox W6;
    private List<CheckBox> ChbTemp = new ArrayList<CheckBox>();
    private List<CheckBox> ChbWeather = new ArrayList<CheckBox>();
    private List<Date> dates = new ArrayList<Date>();
    private List<Integer> berichten = new ArrayList<Integer>();
    @FXML
    private ToggleGroup socialmedia;
    private String sql = "";

    @FXML // log out scherm
    private void logoutButtonAction() {
        fxmlController logout = new fxmlController();
        logout.setLogin("Log in", "Login.fxml");
    }

    @FXML // data scherm,
    private void UpdatedataButtonAction() {
        fxmlController UD = new fxmlController();
        UD.setMainStage("Update Data", "UpdateDataWindow.fxml");
    }

    @FXML // Button voor het ophalen van het weer.
    private void WeerButtonAction() throws Exception {
        outputTempArea.setText("");//cleanup
        outputTempDisc.setText("");
        weerInfo info = new weerInfo(); //init weerInfo
        outputTempArea.setText(String.valueOf(info.getGemid()) + "'C "); // vult labels met de juiste info uit de weerinfo class
        outputTempDisc.setText(String.valueOf(info.getTranslate()));
        //date
        outputDate.setText(dateFormat.format(cal.getTime()));


    }
    public void getSocialMediaGroup() {
        try {
            String toggle = ((RadioButton) socialmedia.selectedToggleProperty().getValue()).getText();
            if(toggle.equals("Alles")){
                sql = "";
                sql += "SELECT COUNT(IF(socialmedia='Twitter','1',null)) AS Twitter, " +
                        "COUNT(IF(socialmedia='Facebook','1',null)) AS Facebook, " +
                        "COUNT(IF(socialmedia='Google','1',null)) AS Google FROM Bericht";
                SQLresult2 += "SELECT COUNT(IF(socialmedia='Twitter','1',null)) AS Twitter, " +
                        "COUNT(IF(socialmedia='Facebook','1',null)) AS Facebook, " +
                        "COUNT(IF(socialmedia='Google','1',null)) AS Google FROM Bericht";


                getPositiveOrNegative();
            }
            else {
                sql = "";
                sql += "SELECT COUNT(IF(socialmedia='" + toggle +"','1',null)) AS "+ toggle + " FROM Bericht";
                SQLresult2 += "SELECT COUNT(IF(socialmedia='" + toggle +"','1',null)) AS "+ toggle + " FROM Bericht";
                getPositiveOrNegative();
            }
        }
        catch (Exception e){
            System.out.println("Klik een button aan om een analyse te doen!");
        }
    }

    public void getTemperature1() {
        int Count = 0;
        try {
            for (CheckBox aChb : ChbTemp) {
                if (aChb.isSelected() && Count == 0) {
                    sql += " AND temperatuur " + aChb.getText();
                    Count++;
                    getWeather();
                }
                else if(aChb.isSelected() && Count >= 1){
                    sql += " OR temperatuur " + aChb.getText();
                    getWeather();
                }
                else {
                    System.out.println("check");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getPositiveOrNegative() {
        String positief = "positief >= 5";
        String negatief = "positief <= 5";
        String allebei = "positief > -50";

        try {
            String toggle = ((RadioButton) raiting.selectedToggleProperty().getValue()).getText();
            switch (toggle) {
                case "Allebei":
                    sql += " LEFT OUTER JOIN Weersvoorspelling ON Weersvoorspelling.Datum = Bericht.Datum WHERE" + " " + allebei;
                    getTemperature1();
                    break;
                case "Positief":
                    sql += " LEFT OUTER JOIN Weersvoorspelling ON Weersvoorspelling.Datum = Bericht.Datum WHERE" + " " + positief;
                    getTemperature1();
                    break;
                default:
                    sql += " LEFT OUTER JOIN Weersvoorspelling ON Weersvoorspelling.Datum = Bericht.Datum WHERE" + " " + negatief;
                    getTemperature1();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Kies!");
        }
    }

    public void getWeather() {
        int Count = 0;
        try {
            for (CheckBox aChb : ChbWeather) {
                if (aChb.isSelected() && Count == 0) {
                    sql += " AND Weersituatie = " + aChb.getText();
                    Count++;
                }
                else if(aChb.isSelected() && Count >= 1){
                    sql += " OR Weersituatie = " + aChb.getText();
                }
                else {
                    System.out.println("check");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void AnalyseUitvoeren() throws Exception {

        //TODO reset string
        getSocialMediaGroup();
        System.out.println("--==--==---==--==--");
        System.out.println(sql);
        System.out.println("--==--==---==--==--");
        System.out.println(SQLresult2);
        System.out.println("--==--==---==--==--");
        SQLresult = sql;
        new ResultController();
        fxmlController AN = new fxmlController();
        AN.setMainStage("Analyse", "Data.fxml");
    }


    @FXML // Button voor het wegschrijven van het weer naar de db
    private void UpdateWeather() throws Exception {
        weerInfo info = new weerInfo();
        try {
            Connection con = connect.connectToDb();
            String sql = "INSERT INTO Weersvoorspelling (Datum, Temperatuur, Weersituatie) VALUES (?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
            preparedStatement.setDouble(2, info.getGemid());
            preparedStatement.setString(3, info.getTranslate());
            preparedStatement.execute();
            System.out.println("Success?");

            /**Close connection with Database **/
            con.close();
            /**Catch exception when data can't be saved into database for example: There is nothing filled in **/
        }catch (SQLException e) {
            System.out.println("Weer al ge-update, wacht tot morgen.");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    // Start de volgende methodes als de het analisten scherm opent
    // maakt pie chart op basis van SQL query
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("\n\r");
        ChbTemp.add(F1);
        ChbTemp.add(F2);
        ChbTemp.add(F3);
        ChbWeather.add(W1);
        ChbWeather.add(W2);
        ChbWeather.add(W3);
        ChbWeather.add(W4);
        ChbWeather.add(W5);
        ChbWeather.add(W6);

       weerplaatje.setImage((new Image(weatherInfo.setWeatherImage(weatherInfo.getWeatherConditionImg(weatherInfo.getDescrip())))));
        try {
            WeerButtonAction();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Socialmediacount SocMed = null;

        try {
            SocMed = new Socialmediacount();
        } catch (Exception e) {
            e.printStackTrace();
        }


        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Google", SocMed.getGoogle()),
                        new PieChart.Data("Twitter", SocMed.getTwitter()),
                        new PieChart.Data("Facebook", SocMed.getFacebook()));

        Piechart.setData(pieChartData);



    }
    public  List<Date> getDate() {
        return dates;
    }

    public  List<Integer> getAmount() {
        return berichten;
    }

    public void grafiek1(ActionEvent actionEvent) {
        try {
            Connection con = connect.connectToDb();
            Statement statement = con.createStatement();
            String sql;
            sql = "SELECT count(Bericht.BerichtId), Datum FROM Bericht \n" +
                    "GROUP BY Datum";
            //execute sql query
            ResultSet rs = statement.executeQuery(sql);
            //process result of query
            while (rs.next()) {
                dates.add((Date) rs.getDate("Datum"));
                int a = rs.getInt(("count(Bericht.BerichtId)"));
                berichten.add(a);
            }
            //close database connection
            con.close();
            VoorbeeldAnalyse d = new VoorbeeldAnalyse(this);
            fxmlController AN = new fxmlController();
            AN.setMainStage("Analyse", "Voorbeeld.fxml");


            /**Catch exception when data can't be saved into database for example: There is nothing filled in **/
        } catch (SQLException e) {
            System.out.println("Weer al ge-update, wacht tot morgen.");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public String ReturnSQL(){
        return sql;
    }

}



