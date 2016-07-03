package Data_Project;

import Data_Project.Characters;
import Data_Project.Servers;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-09T13:18:07")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile CollectionAttribute<Users, Characters> charactersCollection;
    public static volatile SingularAttribute<Users, Integer> characterSlots;
    public static volatile SingularAttribute<Users, String> lastName;
    public static volatile SingularAttribute<Users, String> firstName;
    public static volatile SingularAttribute<Users, String> password;
    public static volatile CollectionAttribute<Users, Servers> serversCollection;
    public static volatile SingularAttribute<Users, Integer> balance;
    public static volatile SingularAttribute<Users, Integer> monthsPayed;
    public static volatile SingularAttribute<Users, String> iban;
    public static volatile SingularAttribute<Users, Date> lastPayment;
    public static volatile SingularAttribute<Users, Boolean> banned;
    public static volatile SingularAttribute<Users, String> userName;

}