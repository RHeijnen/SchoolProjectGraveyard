/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_Project;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author bart
 */
public class CharacterManagementController implements Initializable {

    @FXML
    private ComboBox<String> character_slotID;
    @FXML
    private Label characterCountLabel;
    @FXML
    private Label labelname;
    @FXML
    private Label labelclass;
    @FXML
    private Label labelrace;
    @FXML
    private Label labellevel;
    @FXML
    private Label adress, active, servernames;

    private HashMap<String, Characters> characterrefresh = new HashMap<String, Characters>();

    private int nCharacterAmmount = 0;
    //private Characters char = em.getReference(Character.class, //TODO FILL IN //);
    private fxmlController controller = new fxmlController();
    private ObservableList<Characters> characterlist = FXCollections.observableArrayList();
    private ObservableList<Characters> characterobjects = FXCollections.observableArrayList();
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("groupId_Project_Maven_FX_jar_1.0-SNAPSHOTPU");
    private EntityManager em = emf.createEntityManager();

    @FXML
    public void Back() {
        controller.setMainStage("Create Character", "/ApplicationMenuWindow.fxml");

    }

    @FXML
    public void createCharacter() {
        // Create new Character
        controller.setMainStage("Create Character", "/CharacterCreationWindow.fxml");

    }

    @FXML
    public void goToUserWindow() {
        // User management window
        controller.setMainStage("User window", "/UserWindow.fxml");
    }

    @FXML
    public void goToCharacterWindow() {
        // Character management Window ( Current )
        controller.setMainStage("Character window", "/CharacterWindow.fxml");

    }

    @FXML
    public void StartGame() {
        // starts game
        controller.setMainStage("World of Borecraft v0.1", "/ServerWindow.fxml");

    }

    @FXML
    public void CharacterInfoUpdate() {
        String name = character_slotID.getValue();
        Characters ch = characterrefresh.get(name);
        labelname.setText(ch.getName());
        labelclass.setText(ch.getClass1());
        labelrace.setText(ch.getRace());
        labellevel.setText(ch.getLevel().toString());
    }



    public void initialize(URL location, ResourceBundle resources) {
        try {
            for (Characters item : Users.getInstance().getCharactersCollection()) { // loop kijkt naar username > characters en pompt ze in een list
                characterrefresh.put(item.getName(), item);
            }
            character_slotID.getItems().addAll(characterrefresh.keySet());

            nCharacterAmmount = Users.getInstance().getCharactersCollection().size(); // kijkt hoeveel chars op username
            characterCountLabel.setText(String.valueOf(nCharacterAmmount) + " out of " + Users.getInstance().getCharacterSlots() + " slots used.");  // zet aantal in het label

        } catch (Exception e) {
            System.out.println("Geen Characters");
        }
    }

}
