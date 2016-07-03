package Data_Project;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class DataChartController implements Initializable {

    @FXML
    PieChart Piechart;
    @FXML
    public LineChart<String, Integer> Linechart;
    @FXML
    public StackedAreaChart<Number, Number> Stackedchart;
    @FXML
    public ScatterChart<Number, Number> Scatterchart;
    @FXML
    public  StackedBarChart<String,Number> Stackedbarchart;
    @FXML
    public StackedBarChart<Date,Integer> barChart2;
    //referenties
    String sqldone = AnalistController.SQLresult;
    private static AnalistController a;


    public DataChartController(AnalistController a) throws Exception {
        DataChartController.a = a;

    }
    public DataChartController() {

    }
    private int sizechecker = ResultController.Socialmedialist.size();
    public void BackButton(){
        fxmlController UD = new fxmlController();
        UD.setMainStage("Analyse", "AnalistWindow.fxml");
    }
    public void setPiechartvisual(){
        // pie chart die de resultaten laat zien van de query builder!
        if (sqldone.contains("Twitter") && sqldone.contains("Google") && sqldone.contains("Facebook")){
            ObservableList<PieChart.Data> pieChartData = null;
            try {
                pieChartData = FXCollections.observableArrayList(
                        new PieChart.Data(ResultController.Socialnaamlist.get(0).toString(), 15),
                        new PieChart.Data(ResultController.Socialnaamlist.get(1).toString(), 15),
                        new PieChart.Data(ResultController.Socialnaamlist.get(2).toString(), 23));
                Piechart.setData(pieChartData);

            } catch (Exception e) {
                System.out.println("woops");
            }
        }else{
            ObservableList<PieChart.Data> pieChartData = null;
            try {
                Piechart.setTitle("Positiviteit vandaag");
                pieChartData = FXCollections.observableArrayList(
                        new PieChart.Data("Positief", 45),
                        new PieChart.Data("Negatief", 12));
                Piechart.setData(pieChartData);

            } catch (Exception e) {
                System.out.println("woops");
            }

        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Ratinghistorie rat = null;
        Socialmediacount SocMed = null;
        weerhistorie WH = null;
        try {
            rat = new Ratinghistorie();
            WH = new weerhistorie();
            SocMed = new Socialmediacount();


        } catch (Exception e) {
            e.printStackTrace();
        }


/**        // working piechart op basis van social media aantal
 ObservableList<PieChart.Data> pieChartData =
 FXCollections.observableArrayList(
 new PieChart.Data("Google", SocMed.getGoogle()),
 new PieChart.Data("Twitter", SocMed.getTwitter()),
 new PieChart.Data("Facebook", SocMed.getFacebook()));

 Piechart.setData(pieChartData);*/

        // PAGE 1
        // pie chart
        setPiechartvisual();
        // stacked bar chart

        //Series 1
        XYChart.Series y = new XYChart.Series<>();
        XYChart.Series x = new XYChart.Series<>();
        Stackedbarchart.setTitle("Positiviteit afgelopen maand");
        y.setName("Positief");
        x.setName("Negatief");
        for(int i =0; i < sizechecker; i++) {

            try {
                y.getData().add(new XYChart.Data<>(ResultController.Socialnaamlist.get(i), ResultController.Socialratinglist.get(i)));
                x.getData().add(new XYChart.Data<>(ResultController.Socialnaamlist.get(i), 234));


            }catch (Exception e) {
                System.out.println("Woops");
            }
        } // set data
        Stackedbarchart.getData().addAll(y, x);


        // PAGE 2
        //  working line chart
        Linechart.getXAxis().setAutoRanging(true);
        Linechart.getYAxis().setAutoRanging(true);

        XYChart.Series series = new XYChart.Series<>();
        XYChart.Series rating = new XYChart.Series<>();

        rating.setName("Positief");
        series.setName("Temp");
        Linechart.setTitle("Temperatuur/positiviteit Rotterdam");
        Linechart.getYAxis().setLabel("Temperatuur");
        Linechart.getXAxis().setLabel("Dag");

        series.getData().add(new XYChart.Data<>("Vandaag", WH.getToday()));
        series.getData().add(new XYChart.Data<>("dag-1", WH.getminD1()));
        series.getData().add(new XYChart.Data<>("dag-2", WH.getminD2()));
        series.getData().add(new XYChart.Data<>("dag-3", WH.getminD3()));
        series.getData().add(new XYChart.Data<>("dag-4", WH.getminD4()));
        series.getData().add(new XYChart.Data<>("dag-5", WH.getminD5()));
        series.getData().add(new XYChart.Data<>("dag-6", WH.getminD6()));

        rating.getData().add(new XYChart.Data<>("Vandaag", rat.getRtoday()));
        rating.getData().add(new XYChart.Data<>("dag-1", rat.getRmin1()));
        rating.getData().add(new XYChart.Data<>("dag-2", rat.getRmin2()));
        rating.getData().add(new XYChart.Data<>("dag-3", rat.getRmin3()));
        rating.getData().add(new XYChart.Data<>("dag-4", rat.getRmin4()));
        rating.getData().add(new XYChart.Data<>("dag-5", rat.getRmin5()));
        rating.getData().add(new XYChart.Data<>("dag-6", rat.getRmin6()));

        Linechart.getData().addAll(series, rating);

        // Stackedchart
        Stackedchart.getXAxis().setAutoRanging(true);
        Stackedchart.getYAxis().setAutoRanging(true);

        XYChart.Series xseries = new XYChart.Series<>();
        xseries.setName("Facebook");
        Stackedchart.setTitle("Like Count");
        Stackedchart.getYAxis().setLabel("likes");
        Stackedchart.getXAxis().setLabel("Dag");
        xseries.setName("XYChart.Series 1");
        xseries.getData().add(new XYChart.Data<>(0, 2227));
        xseries.getData().add(new XYChart.Data<>(1, 2231));
        xseries.getData().add(new XYChart.Data<>(2, 2247));
        xseries.getData().add(new XYChart.Data<>(3, 2253));
        xseries.getData().add(new XYChart.Data<>(4, 2273));


        Stackedchart.getData().add(xseries);
        /**       // Scatterchart TODO
         Scatterchart.getXAxis().setAutoRanging(true);
         Scatterchart.getYAxis().setAutoRanging(true);

         XYChart.Series series1 = new XYChart.Series();
         series1.setName("Scatterstuff");
         Scatterchart.setTitle("teesstt");
         Scatterchart.getYAxis().setLabel("test");
         Scatterchart.getXAxis().setLabel("test");
         series1.setName("XYChart.Series 5");
         series1.getData().add(new XYChart.Data(4.2, 193.2));
         series1.getData().add(new XYChart.Data(2.8, 33.6));
         series1.getData().add(new XYChart.Data(23.8, 13.6));


         Scatterchart.getData().addAll(series1);*/

        try {
            XYChart.Series x1 = new XYChart.Series<>();
            barChart2.setTitle("Posts/Datum");
            x1.setName("Geposte Social Media");
            try {
            for (int h = 0; h < a.getAmount().size(); h++)
            {
                x1.getData().add(new XYChart.Data<>(a.getDate().get(h).toString(), a.getAmount().get(h)));
            }

            } catch (Exception e) {
                    System.out.println("Woops");
            }
             // set data
            barChart2.getData().addAll(x1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logoutButtonAction(ActionEvent actionEvent) {
    }
}

