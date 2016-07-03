package Data_Project;
import net.aksingh.owmjapis.AbstractWeather;
import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;
import org.json.JSONException;

import javax.xml.bind.SchemaOutputResolver;
import java.io.IOException;

public class weerInfo {

    private double gemid;
    private String Descriptionfinal;



    public weerInfo () throws IOException, JSONException {
        OpenWeatherMap owm = new OpenWeatherMap(""); // Open weather constructor
        CurrentWeather cwd = owm.currentWeatherByCityName("Rotterdam, NL"); // zoekt naar Rotterdam / pakt huidige temp info rotterdam
        //
        // Temperatuur API
        //
        if (cwd.getMainInstance().hasMaxTemperature() && cwd.getMainInstance().hasMinTemperature())
        { // als geldige max/min temp. bekend zijn :
            double max = cwd.getMainInstance().getMaxTemperature(); // max temp
            double min = cwd.getMainInstance().getMinTemperature(); // min temp
            double maxC = ((max - 32)*5)/9; // magic
            double minC = ((min - 32)*5)/9;// magic
            double mid = (maxC + minC) /2;// magic
            String description = cwd.getRawResponse();
            Descriptionfinal = cwd.getWeatherInstance(0).getWeatherDescription();

            //Descriptionfinal =  description.substring(89, 107); // dit moet anders

            gemid = (Math.floor((mid)*10)/10.0); // < klopt geen fuck van vlgens mij
           // System.out.println("Temperatuur:(min) " + Math.floor((minC)*10)/10.0 + "/ (max)" + Math.ceil((maxC)*10)/10.0  + "\'C");
            System.out.println(String.valueOf(Math.floor((gemid)*10)/10.0) + "\'C " + Descriptionfinal);
        }
    }
    public double getGemid() {
        return gemid;
    }
    public String getDescrip(){
        return Descriptionfinal;
    }

}