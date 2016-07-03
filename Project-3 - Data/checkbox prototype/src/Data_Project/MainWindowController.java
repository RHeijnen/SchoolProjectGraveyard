package Data_Project;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class MainWindowController extends Application{
    public static Stage theStage ;
    public static  Parent root;
@FXML
public void nextbutton(){

    fxmlController logout = new fxmlController();
    logout.setLogin("new", "TestWindow.fxml");

}
    @Override
    public void start(Stage primaryStage) throws Exception{
        theStage = primaryStage;
        root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        theStage.setTitle("Log in");
        theStage.setScene(new Scene(root, GetScreenWorkingWidth()/2 , GetScreenWorkingHeight()/2 ));
        theStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
    public static double GetScreenWorkingWidth() {
        return Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    }

    public static double GetScreenWorkingHeight() {
        return Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    }
}
