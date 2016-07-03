package Data_Project;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

    @FXML Button submitButton;
    @FXML TextField userField;
    @FXML PasswordField passField;
    @FXML Label errorLabel;
    public void submitForm(ActionEvent actionEvent) {
        authorizedUser user = new authorizedUser();
        if(!user.checkCredentials(userField.getText(), passField.getText()))
        {
            errorLabel.setText("Invalid credentials");
        }
        else{
            fxmlController x = new fxmlController();
            String role = user.getDBRole();
            System.out.println(role);
            switch(role){
                case "Administrator":
                    x.setMainStage("Adminenviroment", "AdminWindow.fxml");
                    break;
                case "Analist":
                    x.setMainStage("Analist", "AnalistWindow.fxml");
                    break;
            }


        }
    }
}
