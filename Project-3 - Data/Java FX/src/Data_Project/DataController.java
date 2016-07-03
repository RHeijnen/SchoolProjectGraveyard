package Data_Project;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.*;

import java.net.URL;
import java.util.ResourceBundle;

public class DataController implements Initializable {

    @FXML
    PieChart Piechart;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Executed", 60),
                        new PieChart.Data("Passed", 25),
                        new PieChart.Data("Fails", 15));

        Piechart.setData(pieChartData);

    }
}

