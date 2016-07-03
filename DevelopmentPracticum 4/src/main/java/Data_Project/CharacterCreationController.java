package Data_Project;

import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

/**
 * Created by Indi on 5/27/2015.
 */
public class CharacterCreationController implements Initializable {

    @FXML
    ComboBox inputClass;
    @FXML
    ComboBox inputRace;
    @FXML
    TextField inputName;
    @FXML
    Label nNameError;
    Collection nCharacters = Users.getInstance().getCharactersCollection();
    String Username = Users.getInstance().getUserName();
    private fxmlController controller = new fxmlController();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("groupId_Project_Maven_FX_jar_1.0-SNAPSHOTPU");
    EntityManager em = emf.createEntityManager();

 @FXML
    public void CreateCharacter(){

        try {
        Characters newCharacter = new Characters();
        
        newCharacter.setName(inputName.getText());
        newCharacter.setLevel(1);
        newCharacter.setRace(inputRace.getValue().toString());
        newCharacter.setClass1(inputClass.getValue().toString());
        
        em.getTransaction().begin();
        em.persist(newCharacter);
        em.getTransaction().commit();
       
                System.out.println("New character : "+inputName.getText() + "Lvl. 1 "+inputRace.getValue().toString() + " " +inputClass.getValue().toString() );

        setOwnsTable(inputName.getText());
        }catch (PersistenceException e) {
            System.out.println("Username bestaat al");
        }catch (Exception e){
            System.out.println("Zorg dat alle velden ingevuld zijn.");
    }
        // maak aan in database TODO
        // maak name checker error
        // nNameError.setText("Naam Bestaat al");
    }
    public void setOwnsTable(String Charname){
        
        try {
            em.getTransaction().begin();
            em.createNativeQuery("INSERT INTO owns(name, user_name) VALUES (?,?)")
                    .setParameter(1, Charname)
                    .setParameter(2, Username)
                    .executeUpdate();
            em.getTransaction().commit();
       
        } catch (Exception b){
            b.printStackTrace();
            em.getTransaction().rollback();
            System.out.println("Voer alle velden in");

        }
        finally {
                
            em.close();
        }
    }

    @FXML
    public void Back() {
        // Back to character management window
        controller.setMainStage("Log In", "/CharacterWindow.fxml");
    }

    public void initialize(URL location, ResourceBundle resources) {

        // fills comboboxes -
        inputClass.getItems().addAll(
                "Warrior",
                "Mage",
                "Paladin",
                "Cleric",
                "Archer"
        );

        inputRace.getItems().addAll(
                "Human",
                "Orc",
                "Elf",
                "Undead",
                "Giant"
        );
    }

}
