package Data_Project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Lappie on 3/28/2015.
 */
public class TestWindowController implements Initializable {
    public CheckBox FCB1;
    public CheckBox FCB2;
    public CheckBox FCB3;
    public CheckBox FCB4;
    public CheckBox FCB5;
    public CheckBox FCB6;
    public CheckBox FCB7;
    public CheckBox FCB8;
    public CheckBox FCB9;
    public CheckBox FCB10;
    public CheckBox FCB11;
    public CheckBox FCB12;
    public CheckBox FCB13;
    public CheckBox FCB14;
@FXML
public void testbutton() {
    String SQLSTRING = "";
    // Social media
    if (FCB1.isSelected() && FCB2.isSelected() && FCB3.isSelected()) {
        System.out.println("Social media : Twitter + Facebook + Google Places");
        SQLSTRING +="SELECT \n" +
                "    COUNT(IF(socialmedia='Twitter',1,null)) \"twitter\",\n" +
                "    COUNT(IF(socialmedia='Facebook',1,null)) \"facebook\",\n" +
                "\tCOUNT(IF(socialmedia='Google',1,null)) \"google\"\n";

    } else if (FCB1.isSelected() && FCB2.isSelected()) {
        System.out.println("Social media : Twitter + Facebook");
        SQLSTRING +="SELECT \n" +
                "    COUNT(IF(socialmedia='Twitter',1,null)) \"twitter\",\n" +
                "    COUNT(IF(socialmedia='Facebook',1,null)) \"facebook\"\n";
    } else if (FCB2.isSelected() && FCB3.isSelected()) {
        System.out.println("Social media : Facebook + Google Places");
    //   System.out.println("0,1,1");
        SQLSTRING +="SELECT \n" +
                "    COUNT(IF(socialmedia='Facebook',1,null)) \"facebook\",\n" +
                "\tCOUNT(IF(socialmedia='Google',1,null)) \"google\"\n";
    } else if (FCB1.isSelected() && FCB3.isSelected()) {
        System.out.println("Social media : Twitter + Google Places");
   //     System.out.println("1,0,1");
        SQLSTRING +="SELECT \n" +
                "    COUNT(IF(socialmedia='Twitter',1,null)) \"twitter\",\n" +
                "\tCOUNT(IF(socialmedia='Google',1,null)) \"google\"\n";

    } else if(FCB1.isSelected()) {
        System.out.println("Social media : Twitter");
    //    System.out.println("1,0,0");
        SQLSTRING +="SELECT \n" +
                "    COUNT(IF(socialmedia='Twitter',1,null)) \"twitter\",\n";

    }else if (FCB2.isSelected()) {
        System.out.println("Social media : Facebook");
     //   System.out.println("0,1,0");
        SQLSTRING +="SELECT \n" +
                "    COUNT(IF(socialmedia='Facebook',1,null)) \"facebook\",\n";

    } else if (FCB3.isSelected()){
        System.out.println("Social media : Google Places");
     //   System.out.println("0,0,1");
        SQLSTRING +="SELECT \n" +
                "\tCOUNT(IF(socialmedia='Google',1,null)) \"google\"\n";

    } else {
        System.out.println("geen social media geslecteerd");
        //TODO//////////////////error!///////////////////TODO////////////
    } //RATING
    if (FCB4.isSelected() && FCB5.isSelected()) {
        System.out.println("met positieve en negatieve berichten");
        SQLSTRING +=  "COUNT(IF(positief >  -10,1,null))" + "\"positief\" \n";

    } else if (FCB4.isSelected()) {
        System.out.println("met positieve berichten");
        SQLSTRING +=  "COUNT(IF(positief >  25,1,null))" + "\"positief\" \n";

    } else if (FCB5.isSelected()) {
        System.out.println("met negatieve berichten");
        SQLSTRING +=  "COUNT(IF(positief < 25,1,null))" + "\"positief\"\n";

    } else {
        System.out.println("zonder berichten rating");
        SQLSTRING += "FROM Bericht;";

    } // TEMP
    if (FCB6.isSelected() && FCB7.isSelected() && FCB8.isSelected()) {
        System.out.println("alle temperaturen");
        SQLSTRING += "    Weersituatie as weer,\n" +
                "    Temperatuur as temp\n" +
                "FROM Bericht inner join Weersvoorspelling on Weersvoorspelling.Datum = Bericht.Datum\n" +
                "where Temperatuur > -50;";

    } else if (FCB6.isSelected() && FCB7.isSelected()) {
        System.out.println("temp <0 tot 15");
        SQLSTRING += "    Weersituatie as weer,\n" +
                "    Temperatuur as temp\n" +
                "FROM Bericht inner join Weersvoorspelling on Weersvoorspelling.Datum = Bericht.Datum\n" +
                "where Temperatuur < 15;";

    } else if (FCB6.isSelected() && FCB8.isSelected()) {
        System.out.println("temp <0 en > 15");
        SQLSTRING += "    Weersituatie as weer,\n" +
                "    Temperatuur as temp\n" +
                "FROM Bericht inner join Weersvoorspelling on Weersvoorspelling.Datum = Bericht.Datum\n" +
                "where Temperatuur < 0 OR Temperatuur > 15;";

    } else if (FCB7.isSelected() && FCB8.isSelected()) {
        System.out.println("temp 0 en hoger");
        SQLSTRING += "    Weersituatie as weer,\n" +
                "    Temperatuur as temp\n" +
                "FROM Bericht inner join Weersvoorspelling on Weersvoorspelling.Datum = Bericht.Datum\n" +
                "where Temperatuur > 0;";

    } else if (FCB6.isSelected()) {
            System.out.println("<0 graden");
        SQLSTRING += "    Weersituatie as weer,\n" +
                "    Temperatuur as temp\n" +
                "FROM Bericht inner join Weersvoorspelling on Weersvoorspelling.Datum = Bericht.Datum\n" +
                "where Temperatuur < 0;";

    } else if (FCB7.isSelected()) {
        System.out.println("0 tot 15 graden");
        SQLSTRING +="    Weersituatie as weer,\n" +
                "    Temperatuur as temp\n" +
                "FROM Bericht inner join Weersvoorspelling on Weersvoorspelling.Datum = Bericht.Datum\n" +
                "where Temperatuur > 0 AND Temperatuur < 15;";

    } else if (FCB8.isSelected()) {
        System.out.println("15> graden");
        SQLSTRING += "    Weersituatie as weer,\n" +
                "    Temperatuur as temp\n" +
                "FROM Bericht inner join Weersvoorspelling on Weersvoorspelling.Datum = Bericht.Datum\n" +
                "where Temperatuur > 15;";


    } else {
        System.out.println("geen temp geselecteerd");
        SQLSTRING += "" ;

    }//Weeromstandiggheden
    System.out.println("Weersomstandigheden :");
    if (FCB9.isSelected() && FCB10.isSelected() && FCB11.isSelected() && FCB12.isSelected() && FCB13.isSelected() && FCB14.isSelected()){
        System.out.println("1,1,1,1,1,1");
        System.out.println("alle");
      //  SQLSTRING +=

        // 1x0
    }else if(FCB9.isSelected() && FCB10.isSelected() && FCB11.isSelected() && FCB12.isSelected() && FCB13.isSelected()) {
        System.out.println("1,1,1,1,1,0");
        System.out.println("zon regen mist hagel sneeuw");

    }else if(FCB9.isSelected() && FCB10.isSelected() && FCB11.isSelected() && FCB12.isSelected() && FCB14.isSelected()){
        System.out.println("1,1,1,1,0,1");
        System.out.println("zon regen mist hagel storm");

    }else if(FCB9.isSelected() && FCB10.isSelected() && FCB11.isSelected() && FCB13.isSelected() && FCB14.isSelected()){
        System.out.println("1,1,1,0,1,1");
        System.out.println("zon regen mist sneeuw storm");
    }else if(FCB9.isSelected() && FCB10.isSelected() && FCB12.isSelected() && FCB13.isSelected() && FCB14.isSelected()){
        System.out.println("1,1,0,1,1,1");
        System.out.println("zon regen hagel sneeuw storm");
    }else if(FCB9.isSelected() && FCB11.isSelected() && FCB12.isSelected() && FCB13.isSelected() && FCB14.isSelected()){
        System.out.println("1,0,1,1,1,1");
        System.out.println("zon mist hagel sneeuw storm");
    }else if(FCB10.isSelected() && FCB11.isSelected() && FCB12.isSelected() && FCB13.isSelected() && FCB14.isSelected()){
        System.out.println("0,1,1,1,1,1");
        System.out.println("regen mist hagel sneeuw storm");
        // 2x0
    }else if(FCB11.isSelected() && FCB12.isSelected() && FCB13.isSelected() && FCB14.isSelected()){
        System.out.println("0,0,1,1,1,1");
        System.out.println("mist hagel sneeuw storm");
    }else if(FCB10.isSelected() && FCB12.isSelected() && FCB13.isSelected() && FCB14.isSelected()){
        System.out.println("0,1,0,1,1,0");
        System.out.println(" regen hagel sneeuw storm");
    }else if(FCB10.isSelected() && FCB11.isSelected() && FCB13.isSelected() && FCB14.isSelected()){
        System.out.println("0,1,1,0,1,1");
        System.out.println("regen mist sneeuw storm");

    }else if(FCB10.isSelected() && FCB11.isSelected() && FCB12.isSelected() && FCB14.isSelected()){
        System.out.println("0,1,1,1,0,1");
        System.out.println("regen mist hagel storm");
    }else if(FCB10.isSelected() && FCB11.isSelected() && FCB12.isSelected() && FCB13.isSelected()){
        System.out.println("0,1,1,1,1,0");
        System.out.println("regen mist hagel sneeuw");
    }else if(FCB9.isSelected() && FCB12.isSelected() && FCB13.isSelected() && FCB14.isSelected()){
        System.out.println("1,0,0,1,1,1");
        System.out.println("zon hagel sneeuw storm");

    }else if(FCB9.isSelected() && FCB11.isSelected() && FCB13.isSelected() && FCB14.isSelected()){
        System.out.println("1,0,1,0,1,1");
        System.out.println("zon mist sneeuw storn");

    }else if(FCB9.isSelected() && FCB11.isSelected() && FCB12.isSelected() && FCB14.isSelected()){
        System.out.println("1,0,1,1,0,1");
        System.out.println("zon mist hagel storm");
    }else if(FCB9.isSelected() && FCB11.isSelected() && FCB12.isSelected() && FCB13.isSelected()){
        System.out.println("1,0,1,1,1,0");
        System.out.println("zon mist hagel sneeuw");

    }else if(FCB9.isSelected() && FCB10.isSelected() && FCB13.isSelected() && FCB14.isSelected()){
        System.out.println("1,1,0,0,1,1");
        System.out.println("zonn regen sneeuw storm");

    }else if(FCB9.isSelected() && FCB10.isSelected() && FCB12.isSelected() && FCB14.isSelected()){
        System.out.println("1,1,0,1,0,1");
        System.out.println("zon regen hagel storm");

    }else if(FCB9.isSelected() && FCB10.isSelected() && FCB12.isSelected() && FCB13.isSelected()){
        System.out.println("1,1,0,1,1,0");
        System.out.println("zon regen hagel sneeuw");

    }else if(FCB9.isSelected() && FCB10.isSelected() && FCB11.isSelected() && FCB14.isSelected()){
        System.out.println("1,1,1,0,0,1");
        System.out.println("zon regen mist storm");

    }else if(FCB9.isSelected() && FCB10.isSelected() && FCB11.isSelected() && FCB13.isSelected()){
        System.out.println("1,1,1,0,1,0");
        System.out.println("zon regen mist sneeuw");

    }else if(FCB9.isSelected() && FCB10.isSelected() && FCB11.isSelected() && FCB12.isSelected()){
        System.out.println("1,1,1,1,0,0");
        System.out.println("zon regen mist hagel");

        //3x0
    }else if(FCB12.isSelected() && FCB13.isSelected() && FCB14.isSelected()){
        System.out.println("0,0,0,1,1,1");
        System.out.println("hagel sneeuw storm");

    }else if(FCB11.isSelected() && FCB13.isSelected() && FCB14.isSelected()){
        System.out.println("0,0,1,0,1,1");
        System.out.println("mist sneeuw storm");

    }else if(FCB11.isSelected() && FCB12.isSelected() && FCB14.isSelected()){
        System.out.println("0,0,1,1,0,1");
        System.out.println("mist hagel storm");

    }else if(FCB11.isSelected() && FCB12.isSelected() && FCB13.isSelected()){
        System.out.println("0,0,1,1,1,0");
        System.out.println("mist hagel sneeuuw");

    }else if(FCB10.isSelected() && FCB13.isSelected() && FCB14.isSelected()){
        System.out.println("0,1,0,0,1,1");
        System.out.println("regen sneeuw storm");

    }else if(FCB10.isSelected() && FCB11.isSelected() && FCB14.isSelected()){
        System.out.println("0,1,1,0,0,1");
        System.out.println("regen mist storm");

    }else if(FCB10.isSelected() && FCB11.isSelected() && FCB12.isSelected()){
        System.out.println("0,1,1,1,0,0");
        System.out.println("regen mist hagel");

    }else if(FCB9.isSelected() && FCB13.isSelected() && FCB14.isSelected()){
        System.out.println("1,0,0,0,1,1");
        System.out.println("zon sneeuw storm");

    }else if(FCB9.isSelected() && FCB10.isSelected() && FCB14.isSelected()){
        System.out.println("1,1,0,0,0,1");
        System.out.println("zon regen storm");

    }else if(FCB9.isSelected() && FCB10.isSelected() && FCB11.isSelected()){
        System.out.println("1,1,1,0,0,0");
        System.out.println("zon regen mist");

    }else if(FCB9.isSelected() && FCB11.isSelected() && FCB13.isSelected()){
        System.out.println("1,0,1,0,1,0");
        System.out.println("zon mist sneeuw");

    }else if(FCB10.isSelected() && FCB12.isSelected() && FCB14.isSelected()){
        System.out.println("0,1,0,1,0,1");
        System.out.println("regen hagel storm");

    }else if(FCB10.isSelected() && FCB11.isSelected() && FCB13.isSelected()) {
        System.out.println("0,1,1,0,1,0");
        System.out.println("regen mist sneeuw");

        //woopsies
    }else if(FCB9.isSelected() && FCB11.isSelected() && FCB12.isSelected()) {
        System.out.println("1,0,1,1,0,0");
        System.out.println("zon mist hagel");


        //4x0
    }else if(FCB9.isSelected() && FCB10.isSelected()){
        System.out.println("1,1,0,0,0,0");
        System.out.println("zon regen");

    }else if(FCB9.isSelected() && FCB11.isSelected()){
        System.out.println("1,0,1,0,0,0");
        System.out.println("zon mist");

    }else if(FCB9.isSelected() && FCB12.isSelected()){
        System.out.println("1,0,0,1,0,0");
        System.out.println("zon hagel");

    }else if(FCB9.isSelected() && FCB13.isSelected()){
        System.out.println("1,0,0,0,1,0");
        System.out.println("zon sneeuw");

    }else if(FCB9.isSelected() && FCB14.isSelected()){
        System.out.println("1,0,0,0,0,1");
        System.out.println("zon storm");

    }else if(FCB10.isSelected() && FCB11.isSelected()){
        System.out.println("0,1,1,0,0,0");
        System.out.println("regen mist");

    }else if(FCB10.isSelected() && FCB12.isSelected()){
        System.out.println("0,1,0,1,0,0");
        System.out.println("regen hagel");

    }else if(FCB10.isSelected() && FCB13.isSelected()){
        System.out.println("0,1,0,0,1,0");
        System.out.println("regen sneeuw");

    }else if(FCB10.isSelected() && FCB14.isSelected()){
        System.out.println("0,1,0,0,0,1");
        System.out.println("regen storm");

    }else if(FCB11.isSelected() && FCB12.isSelected()){
        System.out.println("0,0,1,1,0,0");
        System.out.println("mist hagel");

    }else if(FCB11.isSelected() && FCB13.isSelected()){
        System.out.println("0,0,1,0,1,0");
        System.out.println("mist sneeuw");

    }else if(FCB11.isSelected() && FCB14.isSelected()){
        System.out.println("0,0,1,0,0,1");
        System.out.println("mist storm");

    }else if(FCB12.isSelected() && FCB13.isSelected()){
        System.out.println("0,0,0,1,1,0");
        System.out.println("hagel sneeuw");

    }else if(FCB12.isSelected() && FCB14.isSelected()){
        System.out.println("0,0,0,1,0,1");
        System.out.println("hagel storm");

    }else if(FCB13.isSelected() && FCB14.isSelected()){
        System.out.println("0,0,0,0,1,1");
        System.out.println("sneeuw storm");

        //5x0
    }else if(FCB9.isSelected()){
        System.out.println("1,0,0,0,0,0");
        System.out.println("zon");

    }else if(FCB10.isSelected()){
        System.out.println("0,1,0,0,0,0");
        System.out.println("regen");

    }else if(FCB11.isSelected()){
        System.out.println("0,0,1,0,0,0");
        System.out.println("mist");

    }else if(FCB12.isSelected()){
        System.out.println("0,0,0,1,0,0");
        System.out.println("hagel");

    }else if(FCB13.isSelected()){
        System.out.println("0,0,0,0,1,0");
        System.out.println("sneeuw");

    }else if(FCB14.isSelected()){
        System.out.println("0,0,0,0,0,1");
        System.out.println("storm");


    }else {
        System.out.println("geen");
        System.out.println("");
        System.out.println("============SQL===========");
        System.out.println(SQLSTRING);
    }
    System.out.println("\n\r");
    System.out.println("============SQL===========");
    System.out.println(SQLSTRING);
}
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Test Window is open!");
        System.out.println("\n\r");

    }
}
