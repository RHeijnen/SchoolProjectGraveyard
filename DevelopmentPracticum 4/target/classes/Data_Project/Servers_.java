package Data_Project;

import Data_Project.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-09T13:18:07")
@StaticMetamodel(Servers.class)
public class Servers_ { 

    public static volatile SingularAttribute<Servers, Integer> maxUsers;
    public static volatile SingularAttribute<Servers, String> address;
    public static volatile SingularAttribute<Servers, String> name;
    public static volatile SingularAttribute<Servers, String> location;
    public static volatile SingularAttribute<Servers, Integer> connectedUsers;
    public static volatile CollectionAttribute<Servers, Users> usersCollection;

}