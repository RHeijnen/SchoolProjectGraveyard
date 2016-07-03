package Data_Project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;

/**
 * Created by mark&bart on 9-3-15.
 */
public class fxmlController extends Main {

    public void setMainStage(String Title, String fxmlURL) {
        try {
            root = FXMLLoader.load(getClass().getResource(fxmlURL));
            theStage.setTitle(Title);
            theStage.setScene(new Scene(root, GetScreenWorkingWidth()/1.1 , GetScreenWorkingHeight()/1.1));
            theStage.centerOnScreen();
            theStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLogin(String Title, String fxmlURL){
        try {
            root = FXMLLoader.load(getClass().getResource(fxmlURL));
            theStage.setTitle(Title);
            theStage.setScene(new Scene(root, GetScreenWorkingWidth()/2 , GetScreenWorkingHeight()/2));
            theStage.centerOnScreen();
            theStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
