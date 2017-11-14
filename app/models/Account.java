package models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "security.account")
public class Account {

    @Id
    public Integer id;

    public String username;
    public String email;
    public String passwordHash;
    public String passwordSalt;
    public Boolean isConfirmed;
    public Date lastLogin;
    public Date passwordChanged;
    public Date createdAt;

    @OneToMany(mappedBy = "account")
    public List<AccountRole> accountRoleList;

}
