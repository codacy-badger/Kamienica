package kamienica.model;


import kamienica.core.enums.Status;
import kamienica.core.enums.UserRole;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "USER_DETAILS")
public class UserDetails {

    @Id
    @GeneratedValue
    private Long id;
    private UserRole role;
    private Status status;
}
