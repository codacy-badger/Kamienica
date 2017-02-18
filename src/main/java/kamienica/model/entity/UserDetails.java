package kamienica.model.entity;


import kamienica.model.enums.Status;
import kamienica.model.enums.UserRole;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity(name = "USER_DETAILS")
public class UserDetails {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.TENANT;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
    @Length(min = 5, message = "Hasło musi mieć minimum 5 znaków")
    @Column(nullable = false)
    @NotEmpty(message = "Wprowadź hasło")
    private String password = "witaj";


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
