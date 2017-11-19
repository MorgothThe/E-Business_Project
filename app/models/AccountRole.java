package models;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="security.account_role")
public class AccountRole {

    //TODO id

    @ManyToOne
    @JoinColumn(name="account_id")
    public Account account;

    @ManyToOne
    @JoinColumn(name="role_id")
    public Role role;
}
