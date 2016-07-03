package Data_Project;

import facebook4j.*;
import facebook4j.ResponseList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Place;
import se.walkercrou.places.Review;
import twitter4j.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import se.walkercrou.places.Place;


/**
 * Created by bart on 10-3-2015.
 */
public class NewAnalistController implements Initializable {


    @FXML
    public TextArea outputTempArea;

    @FXML
    public TextArea outputTextArea;
    @FXML
    public TextField inputTextArea;


    @FXML
    private void logoutButtonAction() {
        fxmlController logout = new fxmlController();
        logout.setLogin("Log in", "sample.fxml");
    }

    @FXML
    private void dataButtonAction() {
        fxmlController logout = new fxmlController();
        DataController dc = new DataController();
        logout.setMainStage("Data", "NewAnalistWindow.fxml");
    }

    @FXML
    private void HashtaggButtonAction() { //twitter api non oob
        String input = inputTextArea.getText(); // kijkt wat je hebt getypt
        try {
            outputTextArea.setText(" "); // cleanup
            Twitter twitter = TwitterFactory.getSingleton();
            Query query = new Query(input);
            QueryResult result = twitter.search(query);
            for (Status status : result.getTweets()) {// print uit
                outputTextArea.appendText("@" + status.getUser().getScreenName() + " : " + status.getText() + " : " + status.getGeoLocation() + "\n\r");
            }
        } catch (TwitterException te) { // error message
            te.printStackTrace();
            outputTextArea.appendText("Failed : " + te.getMessage());
            System.exit(0);
        }


    }

    @FXML
    private void PlacesButtonAction() {

        GooglePlaces client = new GooglePlaces("AIzaSyALbXTMU7FfHrYpokHmOYpvJBsXUioQYlg");
        ArrayList<Place> places = (ArrayList<Place>) client.getPlacesByQuery("Euromast", GooglePlaces.MAXIMUM_RESULTS);
        int review = 0;
        List<Review> l1 = null;
        outputTextArea.setText(" "); // cleanup

        for (int i = 0; i < places.size(); i++) {
            Place me = places.get(i);
            outputTextArea.appendText(String.valueOf(me.getRating()) + "\n\r");
            // moet if omheen om de -1.0 (geen rating) verwijderen.
        }

    }

    @FXML
    private void FacebookButtonAction() throws FacebookException {

        Facebook facebook = new FacebookFactory().getInstance();
        ResponseList<Post> feeds = facebook.getFeed("313850611958467",/* < dit
        verwijst naar een OPENBARE  facebook pagina van rotterdam centraal */
                new Reading().limit(50));
        outputTextArea.setText(" "); // cleanup

        for (int i = 0; i < feeds.size(); i++) {
            Post post = feeds.get(i);
            String message = post.getMessage();
            // Print console test
            outputTextArea.appendText(message + "\n\r");
        }


    }

    @FXML
    private void WeerButtonAction() throws IOException {
        new weerInfo();
        weerInfo info = new weerInfo();
        outputTempArea.appendText(String.valueOf(info.getGemid()) + "'C" + String.valueOf(info.getDescrip()));

    }
    @FXML
    PieChart Piechart;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Executed", 11),
                        new PieChart.Data("Town Wins", 23),
                        new PieChart.Data("Jester Kills", 3),
                        new PieChart.Data("Maffia Wins", 14),
                        new PieChart.Data("SK Wins", 24),
                        new PieChart.Data("Arson Wins", 0),
                        new PieChart.Data("WW Wins", 0));

        Piechart.setData(pieChartData);
    }
}


