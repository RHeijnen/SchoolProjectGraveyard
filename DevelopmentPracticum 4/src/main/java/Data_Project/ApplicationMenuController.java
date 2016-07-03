package Data_Project;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author bart
 */
public class ApplicationMenuController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label label;
    private String error;
    private static Users loggingUser = null;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("groupId_Project_Maven_FX_jar_1.0-SNAPSHOTPU");
    private EntityManager em = emf.createEntityManager();
    private fxmlController controller = new fxmlController();

    @FXML
    public void Login() {
        validate();
        setLabel(error);
    }

    public EntityManager getEntityManager() {
        return em;
    }

    @FXML
    public void CreateAccount() {

        controller.setLogin("New Account", "/NewAccountWindow.fxml");
    }

    @FXML
    public void setLoggedInUser() {
        controller.setMainStage("Character window ", "/CharacterWindow.fxml");
    }

    /**
     *
     * @param text is the input String
     * @return false if the the String is empty
     */
    public boolean isEmpty(String text) {
        return !(text.isEmpty() || text.length() == 0);
    }

    public void validate() {
        String us = usernameField.getText();
        String ps = passwordField.getText();
        if (isEmpty(us) && isEmpty(ps)) {
            processLogin(us, ps);
        } else {
            error = "Input all fields!";
        }
    }



    private void processLogin(String user, String pass) {
        Users.getInstance().setUserObject(em.getReference(Users.class, user));
        List results = em.createNamedQuery("Users.findByUserName")
                .setParameter("userName", user)
                .getResultList();
        if (!results.isEmpty()) {
            loggingUser = (Users) results.get(0);
            if (String.valueOf(pass).equals(loggingUser.getPassword())) {
                setLoggedInUser();
            } else {
                error = "Wrong Username & Password combination";
            }
        } else {
            error = "Username not found in Database";
        }
    }

    private void setLabel(String text) {
        label.setText(text);
    }

    public Users getUser(String user) {
        return Users.getInstance();
    }

}
