package Data_Project;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Bart on 5/27/2015.
 */
public class UserManagementController implements Initializable {

    @FXML
    private Label currentAmount;
    @FXML
    private ComboBox money_amount;
    @FXML
    private TextField input_amount;
    @FXML
    private Label characterAmount;
    @FXML
    private TextField character_amount;
    @FXML
    private Label subscriptionlabel;

    private fxmlController controller = new fxmlController();
    private ApplicationMenuController acc = new ApplicationMenuController();
    private HashMap<String, Integer[]> subscription = new HashMap<String, Integer[]>();
    private Users currentUser = Users.getInstance();
    private Date date = new Date();
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("groupId_Project_Maven_FX_jar_1.0-SNAPSHOTPU");
    private EntityManager em = emf.createEntityManager();

    @FXML
    public void Back() {
        em.close();
        controller.setLogin("Log In", "/ApplicationMenuWindow.fxml");
    }

    @FXML
    public void goToUserWindow() {
        controller.setMainStage("User window", "/UserWindow.fxml");
    }

    @FXML
    public void goToCharacterWindow() {
        controller.setMainStage("Character window", "/CharacterWindow.fxml");

    }

    /**
     * get The value of the Singleton User object Update the value on the based
     * selection in the ComboBox Only updates the balance if the balance is
     * greater than selected ComboBox or greater than 0
     */
    @FXML
    public void addSubscription() {
        Integer[] selected = subscription.get(money_amount.getValue());
        if (currentUser.getBalance() >= 0 && currentUser.getBalance() >= selected[0]) {
            try {
                updateLastPayed(selected[1]); //Second value from <String, Array Int> Hashmap
                removeFromBalance(selected[0]);
            } catch (NullPointerException e) {
                System.out.println("Choose subscription");
            }
        } else {
            System.out.println("Add balance to ur account to buy subscription!");
        }
    }

    public void updateLastPayed(int monthspayed) {
        currentUser.setLastPayment(date);
        currentUser.setMonthsPayed(currentUser.getMonthsPayed() + monthspayed);
        setSubscriptionLabel();
    }

    /**
     * Remove value from the currentBalance and set the Label
     *
     * @param i inputted value which has to be removed from the current balance
     */
    public void removeFromBalance(int i) {
        int balance = currentUser.getBalance();
        if (hasCredit()) {
            currentUser.setBalance(balance - i);
            updateObjectData(currentUser);
            setBalanceLabel();
        } else {
            System.out.println("Not enough balance!");
        }
    }

    //Balance Label
    public void setBalanceLabel() {
        currentAmount.setText(String.valueOf(currentUser.getBalance()));
    }

    //Subscription Label
    public void setSubscriptionLabel() {
        subscriptionlabel.setText(String.valueOf(currentUser.getMonthsPayed()));
    }

    //CharacterSlotslabel
    public void setCharacterSlotsLabel() {
        characterAmount.setText(String.valueOf(currentUser.getCharacterSlots()));
    }

    /**
     * if the User has enough credit, remove X Euro from balance based on the
     * amount of character slots inputted set the label
     */
    @FXML
    public void addCharacterSlot() {
        int amount = 0;
        try {
            amount = Integer.parseInt(character_amount.getText());
        } catch (NumberFormatException e) {
            System.out.println("Insert whole number!");
        }
        if (hasCredit() && amount <= currentUser.getBalance()) {
            currentUser.setCharacterSlots(currentUser.getCharacterSlots() + amount);
            removeFromBalance(amount);
            setCharacterSlotsLabel();
            System.out.println("Add balance to ur account to buy Character slots!");
        }
    }

    public boolean hasCredit() {
        return currentUser.getBalance() > 0;
    }

    /**
     * Parse the inputted value to Integer get the CurrentUser object and update
     * the balance with the setBalance if integer cannot be parsed exception
     * will be raised Label in FXML will be updated after the transaction of the
     * database is closed
     */
    @FXML
    public void addBalance() {
        int amount = 0;
        try {
            amount = Integer.parseInt(input_amount.getText());
        } catch (NumberFormatException e) {
            System.out.println("No legit value!");
        }
        currentUser.setBalance(currentUser.getBalance() + amount); //Current balance + the Balance from the inputted amount
        updateObjectData(currentUser); //Update object with new Balance
        currentAmount.setText(String.valueOf(currentUser.getBalance())); //Update Label with new Balance
    }

    /**
     * Method Updates the object which get passed in!
     *
     * @param currentObject object which passed in to update
     */
    private void updateObjectData(Object currentObject) {
        em.getTransaction().begin();
        em.merge(currentObject);
        em.getTransaction().commit();
    }

    public void initialize(URL location, ResourceBundle resources) {
        setBalanceLabel();
        setSubscriptionLabel();
        setCharacterSlotsLabel();

        money_amount.getItems().addAll(
                "One Month Subscription €5",
                "Two Month Subscription €8",
                "Three Month Subscription €10",
                "Subscription for a Whole year €35"
        );

        //Value and subscription Types Hashmap in String(Subscription type) Int(Price)
        subscription.put((String) money_amount.getItems().get(0), new Integer[]{5, 1});
        subscription.put((String) money_amount.getItems().get(1), new Integer[]{8, 2});
        subscription.put((String) money_amount.getItems().get(2), new Integer[]{10, 3});
        subscription.put((String) money_amount.getItems().get(3), new Integer[]{35, 12});

    }
}
