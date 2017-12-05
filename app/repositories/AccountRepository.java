package repositories;

import io.ebean.Ebean;
import models.Account;
import models.AccountRole;
import models.Role;
import models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountRepository extends GenericRepository<Account> {


    public AccountRepository() {
        super(Account.class);
    }

    public boolean usernameExist(String usernameValue){
        Account account = Ebean.find(typeParameterClass)
                .where()
                .eq("username", usernameValue)
                .findUnique();
        if(account == null){
            return false;
        }
        else{
            return true;
        }
    }


    public boolean emailExist(String emailValue){
        Account account = Ebean.find(typeParameterClass)
                .where()
                .eq("email", emailValue)
                .findUnique();
        if(account == null){
            return false;
        }
        else{
            return true;
        }
    }

    public int getID(String username){
        Account account = Ebean.find(typeParameterClass)
                .where()
                .eq("username", username)
                .findUnique();
        return account.id;
    }

    public Account getByUsername(String usernameValue){
        Account account = Ebean.find(typeParameterClass)
                .where()
                .eq("username", usernameValue)
                .findUnique();
        return account;
    }

    public String getHash(String usernameValue){
        Account account = Ebean.find(typeParameterClass)
                .where()
                .eq("username", usernameValue)
                .findUnique();
        return account.passwordHash;
    }

    public String getSalt(String usernameValue){
        Account account = Ebean.find(typeParameterClass)
                .where()
                .eq("username", usernameValue)
                .findUnique();
        return account.passwordSalt;
    }

    public List<Role> getRoles(String username){
        Account fetchedAccount = Ebean.find(typeParameterClass)
                .fetch("accountRoleList")
                .fetch("accountRoleList.role")
                .where()
                .eq("username", username)
                .findUnique();

        List<Role> roles = new ArrayList<Role>();
        for (AccountRole accountRole : fetchedAccount.accountRoleList) {
            roles.add(accountRole.role);
        }
        return roles;
    }

    public Role getRole(String username){
        Account fetchedAccount = Ebean.find(typeParameterClass)
                .fetch("accountRoleList")
                .fetch("accountRoleList.role")
                .where()
                .eq("username", username)
                .findUnique();

        Role role = null;
        for (AccountRole accountRole : fetchedAccount.accountRoleList) {
            role = accountRole.role;
        }
        return role;
    }



    public void createAccount(String username, String passwordHash, String passwordSalt, String email){
        Account account = new Account();
        account.username = username;
        account.passwordHash = passwordHash;
        account.passwordSalt = passwordSalt;
        account.email = email;
        account.isConfirmed = false;

        Date date = new Date();
        account.lastLogin = date;
        account.passwordChanged = date;
        account.createdAt = date;

        Ebean.save(account);
    }

    public List<Account> findAll(){
        return finder.all();
    }
}
