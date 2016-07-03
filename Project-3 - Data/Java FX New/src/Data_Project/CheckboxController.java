package Data_Project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Lappie on 4/3/2015.
 */
public class CheckboxController implements Initializable {
    public ToggleGroup raiting;
    public ToggleGroup temperatuur;
    public CheckBox F1;
    public CheckBox F2;
    public CheckBox F3;
    public CheckBox F4;
    public CheckBox W1;
    public CheckBox W2;
    public CheckBox W3;
    public CheckBox W4;
    public CheckBox W5;
    public CheckBox W6;
    private List<CheckBox> ChbTemp = new ArrayList<CheckBox>();
    private List<CheckBox> ChbWeather = new ArrayList<CheckBox>();
    @FXML
    private ToggleGroup socialmedia;
    private String sql = "";


    /**
     * Get the SocialMediaToggle
     * Bart
     */
    public void getSocialMediaGroup() {
        try {
            String toggle = ((RadioButton) socialmedia.selectedToggleProperty().getValue()).getText();
            if(toggle.equals("Alles")){
                sql = "";
                sql += "SELECT COUNT(IF(socialmedia='Twitter','1',null)) AS T, " +
                        "COUNT(IF(socialmedia='Facebook','1',null)) AS F, " +
                        "COUNT(IF(socialmedia='Google','1',null)) AS G FROM Bericht";
                getPositiveOrNegative();
            }
            else {
                sql = "";
                sql += "SELECT COUNT(IF(socialmedia='" + toggle +"','1',null)) FROM Bericht";
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


    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Test Window is open!");
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
    }

    public void testbutton(ActionEvent event) {
        getSocialMediaGroup();
        System.out.println("mkay");
        System.out.println(sql);
    }

}

/**
 * SELECT Weersituatie, Bericht.Datum , Temperatuur, COUNT(socialmedia) FROM Bericht
 LEFT OUTER JOIN Weersvoorspelling ON Weersvoorspelling.Datum = Bericht.Datum
 WHERE positief > -50
 GROUP BY DATUM;

 */



