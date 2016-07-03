package Data_Project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main extends Application{
    public static Stage theStage ;
    public static  Parent root;
    @FXML TextField username;
    @FXML PasswordField password;
    

    //starts the Application window
    @Override
    public void start(Stage primaryStage) throws Exception{
        theStage = primaryStage;
        root = FXMLLoader.load(getClass().getResource("/ApplicationMenuWindow.fxml"));
        theStage.setTitle("Log In");
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
    
    public void checkIfEmpty(){
        
    }


}
