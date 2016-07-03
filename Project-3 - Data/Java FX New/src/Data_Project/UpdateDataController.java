package Data_Project;

import facebook4j.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Review;
import twitter4j.GeoLocation;
import twitter4j.Paging;
import twitter4j.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
/**
 * Created by Lappie on 3/25/2015.
 */
public class UpdateDataController implements Initializable {
    //Login credentials
    private final String usernameDB = "mijnma1q_prjuser";
    private final String passwordDB = "password";
    private final String url = "jdbc:mysql://mijnmarklinbaan.nl:3306/mijnma1q_PrjData";
    public String Message = "";
    //SQL
    private dbConnect connect = new dbConnect();
    private Rating positief = new Rating();
    private Connection con;

    public UpdateDataController() throws Exception {
        con = connect.connectToDb();
    }
    //fxml
    @FXML
    PieChart Piechart;
    @FXML
    public TextField outputTempArea;
    @FXML
    public TextField outputTempDisc;
    @FXML
    public TextField inputTextArea;
    @FXML
    public TextField inputusernamefield;
    @FXML
    public TextField inputhashtaggfield;
    @FXML
    public TextArea outputTextArea;
    @FXML
    public TextField inputeditdata;
    @FXML
    String editdata;

    @FXML // log out scherm
    private void logoutButtonAction() {
        fxmlController logout = new fxmlController();
        logout.setLogin("Log in", "Login.fxml");
    }

    @FXML // data scherm,
    private void dataButtonAction() throws Exception {
        fxmlController logout = new fxmlController();
        DataChartController dc = new DataChartController();
        logout.setLogin("Data", "Data.fxml");
    }
    @FXML // data scherm,
    private void UpdatedataButtonAction() throws Exception {
        fxmlController logout = new fxmlController();
        DataChartController dc = new DataChartController();
        logout.setLogin("Log in", "UpdateDataWindow.fxml");
    }
    @FXML // Analisten scherm,
         private void AnalistButtonAction() {
        fxmlController AN = new fxmlController();
        AN.setMainStage("Analist", "AnalistWindow.fxml");
    }

    @FXML // haalt tweets op van een timelijn
    public void TwitzoekButtonAction() {

        String inp = inputusernamefield.getText();
        // input > twitternaam > return timelijn/tweets naar output

        try {
            Paging page = new Paging (1, 50); // aantal tweets'perpage'
            Twitter latestTweetChecker = new TwitterFactory().getInstance();
            List<Status> statuses = latestTweetChecker.getUserTimeline(inp, page);
            System.out.println("Showing " + " " + inp + " " + "timeline.\r\n \r\n");
            String Message;
            String Usrname;
            int FollowerCount;
            int RetweetCount;
            int FavoriteCount;
            String ID;  // SQL MSG ID

            for (Status status : statuses) {

                // variable
                Message =  status.getText();
                Usrname =  status.getUser().getName();
                FollowerCount = status.getUser().getFollowersCount();
                RetweetCount = status.getRetweetCount();
                FavoriteCount = status.getFavoriteCount();
                ID = String.valueOf(status.getId());

                //sql connectie
                Connection con = connect.connectToDb();
                String sql = "INSERT INTO Bericht (BerichtID,Datum, Beschrijving,socialmedia,Positief) VALUES (?,?,?,?,?)";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, ID);
                preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
                preparedStatement.setString(3, Message);
                preparedStatement.setString(4, "Twitter");
                preparedStatement.setInt(5, 13);
                preparedStatement.execute();

                sql = "INSERT INTO twitter (Bericht_BerichtID,retweet, favorite,username,gerelateerd,volgercount) VALUES (?,?,?,?,?,?)";
                PreparedStatement preparedStatement2 = con.prepareStatement(sql);
                preparedStatement2.setString(1, ID);
                preparedStatement2.setInt(2, RetweetCount);
                preparedStatement2.setInt(3, FavoriteCount);
                preparedStatement2.setString(4, Usrname);
                preparedStatement2.setInt(5, 0);
                preparedStatement2.setInt(6, FollowerCount);
                preparedStatement2.execute();
                /**Close connection with Database **/
                con.close();
                //textareaoutput
                outputTextArea.appendText(status.getUser().getName() + ":" +
                        status.getText() + "\r\n");
                System.out.println(status.getUser().getName() + ":" +
                        status.getText() + "\r\n");
            }
        }catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed : " + te.getMessage());
            System.exit(0);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML// haalt tweets op van een hashtagg
    public void HashtaggButtonAction() { //twitter api non oob
        String input = inputhashtaggfield.getText(); // kijkt wat je hebt getypt
        int socialmediaID = 0;

        try {
            Connection con = connect.connectToDb();
            twitter4j.Twitter twitter =  TwitterFactory.getSingleton();
            Query query = new Query(input);
            query.setCount(50);// aantal tweets LAG!
            QueryResult result = twitter.search(query);
            String ID;              // SQL MSG ID
            for (Status status : result.getTweets()) {// print uit

                String Usrname = status.getUser().getScreenName();
                int FollowerCount = status.getUser().getFollowersCount();
                int RetweetCount = status.getRetweetCount();
                 Message = status.getText();
                Rating myRating = new Rating();
                int rating = myRating.getRating(Message);
                GeoLocation Location = status.getGeoLocation();
                int FavoriteCount = status.getFavoriteCount();
                ID = String.valueOf(status.getId());      //SQL MSG ID
                String end = "=-=-=-=-=-=";

                outputTextArea.appendText("Naam : @" + Usrname + " : " +
                        "\n\r volger count : " + FollowerCount +
                        "\n\r bericht : " + Message +
                        "\n\r locatie :" + Location +
                        "\n\r fav count:" + FavoriteCount +
                        "\n\r retweet count :" + RetweetCount +
                        "\n\r " + end +
                        "\n\r");
              System.out.println("Naam : @" + Usrname + " : " +
                        "\n\r volger count : " + FollowerCount +
                        "\n\r bericht : " + Message +
                        "\n\r locatie :" + Location +
                        "\n\r fav count:" + FavoriteCount +
                        "\n\r retweet count :" + RetweetCount +
                        "\n\r " + end +
                        "\n\r");
                try {
                    con = connect.connectToDb();
                    String sql = "INSERT INTO Bericht (BerichtID,Datum, Beschrijving,socialmedia,Positief) VALUES (?,?,?,?,?)";                    
                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1, ID);
                    preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
                    preparedStatement.setString(3, Message);
                    preparedStatement.setString(4, "Twitter");
                    preparedStatement.setInt(5, rating);
                    preparedStatement.execute();

                    sql = "INSERT INTO twitter (Bericht_BerichtID,retweet, favorite,username,gerelateerd,volgercount) VALUES (?,?,?,?,?,?)";
                    PreparedStatement preparedStatement2 = con.prepareStatement(sql);
                    preparedStatement2.setString(1, ID);
                    preparedStatement2.setInt(2, RetweetCount);
                    preparedStatement2.setInt(3, FavoriteCount);
                    preparedStatement2.setString(4, Usrname);
                    preparedStatement2.setInt(5, 1);
                    preparedStatement2.setInt(6, FollowerCount);
                    preparedStatement2.execute();
                    /**Close connection with Database **/
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("help");
                }
            }
        }catch (TwitterException te) { // error message
            te.printStackTrace();
            System.out.println("Failed : " + te.getMessage());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML// haalt rating van google places op
    private void PlacesButtonAction() {

        GooglePlaces client = new GooglePlaces("AIzaSyALbXTMU7FfHrYpokHmOYpvJBsXUioQYlg"); //RdamCentraal
        ArrayList<se.walkercrou.places.Place> places = (ArrayList<se.walkercrou.places.Place>) client.getPlacesByQuery("RdamCentraal", GooglePlaces.MAXIMUM_RESULTS);
        int review = 0;
        List<Review> l1 = null;
        Random random = new Random(); // random ID GEN
        String ID; // SQL MSG ID
        for (int i = 0; i < places.size(); i++) {
            // variables
            ID = "GP"+ String.valueOf(random.nextInt(55123124) + 15123); // SQL MSG ID
            se.walkercrou.places.Place me = places.get(i);
            List<Review> da = places.get(i).getReviews();
            String test = places.get(i).getPlaceId();
            Double rating = me.getRating();
            // if geen rating, do nothing
            System.out.println(test);
            if (rating.equals("-1.0")) {
                continue;
            }else {
                try {
                    Connection con = connect.connectToDb();

                 String sql = "INSERT INTO Bericht (BerichtID,Datum, Beschrijving,socialmedia,Positief) VALUES (?,?,?,?,?)";
                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1, ID);
                    preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
                    preparedStatement.setString(3, "Google Location Rating");
                    preparedStatement.setString(4, "Google");
                    preparedStatement.setInt(5, 0);
                    preparedStatement.execute();

                    sql = "INSERT INTO google (Bericht_BerichtID,rating) VALUES (?,?)";
                    PreparedStatement preparedStatement2 = con.prepareStatement(sql);
                    preparedStatement2.setString(1, ID);
                    preparedStatement2.setDouble(2, rating);
                    preparedStatement2.execute();
                    System.out.println("Google+ Rating updated");


                    /**Close connection with Database **/
                    con.close();
                    /**Catch exception when data can't be saved into database for example: There is nothing filled in **/
                } catch (SQLException e) {
                    System.out.println("geen nieuwe updates");
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    System.out.println("Class niet gevonden");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
    }
    /*
     https://developers.facebook.com/tools/explorer/145634995501895/?method=GET&path=me%3Ffields%3Did%2Cname&version=v2.2
      */

    @FXML // haalt facebook posts op van de facebook pagina Rotterdam centraal
    private void FacebookButtonAction() throws FacebookException {

        Facebook facebook = new FacebookFactory().getInstance();
        facebook4j.ResponseList<Post> feeds = facebook.getFeed("313850611958467",new Reading().limit(75));

        Page pgId = facebook.getPage("313850611958467");
        int likeCount = pgId.getLikes(); // aantal likes op de facebook pagina van rotterdam
        for (int i = 0; i < feeds.size(); i++) {
            Post post = feeds.get(i);
            String message = post.getMessage();
            Integer sharecount = post.getSharesCount();
            String ID = post.getId();     // SQL MSG ID
            if (sharecount == null) {
                sharecount = 0;
            }
            // Print textarea test
            outputTextArea.appendText("message : \n\r" + message + "\n\r");
            try {
                Connection con = connect.connectToDb();

                String sql = "INSERT INTO Bericht (BerichtID,Datum, Beschrijving,socialmedia,Positief) VALUES (?,?,?,?,?)";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, ID);
                preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
                preparedStatement.setString(3, message);
                preparedStatement.setString(4, "FaceBook");
                preparedStatement.setInt(5, 7);
                preparedStatement.execute();

                sql = "INSERT INTO facebook (Bericht_BerichtID,likes,gedeeld) VALUES (?,?,?)";
                PreparedStatement preparedStatement2 = con.prepareStatement(sql);
                preparedStatement2.setString(1, ID);
                preparedStatement2.setInt(2, likeCount);
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
    @FXML// haalt facebook posts op van de facebook pagina Rotterdam centraal v2
    private void FacebookData(){

        try {
            FacebookData fb = new FacebookData();
        } catch (FacebookException e) {
            e.printStackTrace();
        }
        try {
            facebook4j.ResponseList<Post> fbmsges = new FacebookData().getFeeds();
            for (int i = 0; i < fbmsges.size(); i++) {
                Post post = fbmsges.get(i);
                String message = post.getMessage();

                System.out.println(message);
            }
        } catch (FacebookException e) {
            e.printStackTrace();
        }

    }

    @FXML // Button voor het wegschrijven van het weer naar de db
    private void UpdateWeather() throws Exception {
        weerInfo info = new weerInfo();
        System.out.println(info.getTranslate());

        try {
            Connection con = connect.connectToDb();
            String sql = "INSERT INTO Weersvoorspelling (Datum, Temperatuur, Weersituatie) VALUES (?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
            preparedStatement.setDouble(2, info.getGemid());
            preparedStatement.setString(3, info.getWeatherConditionImg(info.getDescrip()));
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
    @FXML
    private void Editdata(){
        editdata = inputeditdata.getText();
        System.out.println(editdata);
        fxmlController EDIT = new fxmlController();
        EDIT.setMainStage("Edit Data", "EditDataWindow.fxml");
    }
    // Start de volgende methodes als de het analisten scherm opent
    // maakt pie chart op basis van SQL query
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Socialmediacount SocMed = null;

        try {
            SocMed = new Socialmediacount();
        } catch (Exception e) {
            e.printStackTrace();
        }


/**
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Google", SocMed.getGoogle()),
                        new PieChart.Data("Twitter", SocMed.getTwitter()),
                        new PieChart.Data("Facebook", SocMed.getFacebook()));

        Piechart.setData(pieChartData);
*/



    }
}
