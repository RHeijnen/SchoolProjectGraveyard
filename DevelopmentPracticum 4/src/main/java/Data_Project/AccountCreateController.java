package Data_Project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author bart
 */
public class AccountCreateController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField iban;
    @FXML
    private ComboBox regionselecter;
    @FXML
    int defaultCurrency = 0;

    fxmlController controller = new fxmlController();
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("groupId_Project_Maven_FX_jar_1.0-SNAPSHOTPU");
    private EntityManager em = emf.createEntityManager();

    /**
     * Action event for button in Create Account window Method takes the value
     * of the TextFields and Create a new Object of User User object get
     * persisted to the database
     */
    @FXML
    public void addAccount() {
        try {
            String user = username.getText();
            String pass = password.getText();
            Users newUser = Users.getInstance();
            newUser.setUserName(user);
            newUser.setPassword(pass);
            newUser.setBalance(defaultCurrency);
            newUser.setIban(pass);
            newUser.setFirstName(pass);
            newUser.setLastName(pass);
            newUser.setCharacterSlots(5);
            newUser.setBanned(Boolean.FALSE);
            newUser.setLastPayment(null);
            newUser.setMonthsPayed(0);

            newUser.persist(newUser);

            UpdateStores(regionselecter.getValue().toString(), user);
        } catch (Exception e) {
            System.out.println("Username already exists!");
        }
    }

    public void UpdateStores(String Region, String Username) {
        String RegionChecker = Region;
        if (RegionChecker.contains("Europe")) {
            RegionChecker = "201.201.201.201";
        } else {
            RegionChecker = "202.202.202.202";

        }
        em.getTransaction().begin();
        try {
            em.createNativeQuery("INSERT INTO stores(address, user_name) VALUES (?,?)")
                    .setParameter(1, RegionChecker)
                    .setParameter(2, Username)
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @FXML
    public void Back() {
        
        controller.setLogin("Log In", "/ApplicationMenuWindow.fxml");
    }

    public void initialize(URL location, ResourceBundle resources) {
        regionselecter.getItems().addAll(
                "Europe",
                "America"
        );
    }
}
