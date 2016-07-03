package Data_Project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by MarkGame on 18-3-2015.
 */
public class Rating {
    public int getRating(String x) {
        try {
            BufferedReader bfr = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("positief.txt")));
            while (bfr.readLine() != null) {
                if (x.contains(bfr.readLine())) {
                    return 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return -1;

    }
}
