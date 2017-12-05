package models;


import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "security.account")
public class Account extends Model{

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