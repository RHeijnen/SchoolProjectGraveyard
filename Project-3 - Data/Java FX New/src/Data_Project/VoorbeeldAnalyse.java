package Data_Project;

import javafx.fxml.Initializable;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by bart on 8-4-2015.
 */
public class VoorbeeldAnalyse implements Initializable {
    
    private static AnalistController a;
    public StackedBarChart barChart2;

    public VoorbeeldAnalyse(AnalistController a){
        VoorbeeldAnalyse.a = a;
    }

    public VoorbeeldAnalyse() {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            XYChart.Series x1 = new XYChart.Series<>();
            barChart2.setTitle("Posts/Datum");
            x1.setName("Geposte Social Media");
            for (int h = 0; h < a.getAmount().size(); h++) {

                try {
                    x1.getData().add(new XYChart.Data<>(a.getDate().get(h).toString(), a.getAmount().get(h)));


                } catch (Exception e) {
                    System.out.println("Woops");
                }
            } // set data
            barChart2.getData().addAll(x1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

