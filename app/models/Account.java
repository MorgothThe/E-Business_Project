package models;

import io.ebean.Finder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "security.account")
public class Account{

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

    public static Finder<Integer, Account> find = new Finder<>(Account.class);

    public static Account findByID(Integer id)
    {
        return find.byId(id);
    }

}