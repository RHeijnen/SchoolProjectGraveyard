package Data_Project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by MarkGame on 18-3-2015.
 */
public class Rating {

    public int getRating(String x) {
        int score  = 0;
        try {
            BufferedReader bfrP = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("positief.txt")));
            BufferedReader bfrN= new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("negatief.txt")));
            while (bfrP.readLine() != null) {

                if (x.contains(bfrP.readLine())) {
                    score++;
                }
            }
            while(bfrN.readLine() != null && bfrN != null)
            {

                if (x.contains(bfrN.readLine())) {
                    score--;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
        return score;
    }

}
