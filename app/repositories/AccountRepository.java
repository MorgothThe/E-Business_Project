package repositories;

import io.ebean.Ebean;
import models.Account;

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
