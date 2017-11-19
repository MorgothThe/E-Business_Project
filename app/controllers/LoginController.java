package controllers;

import common.PasswordManager;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.AccountRepository;

import javax.inject.Inject;

public class LoginController extends Controller {

    @Inject
    private FormFactory formFactory;

    @Inject
    private AccountRepository accountRepository;

    public Result index(){
        return ok(views.html.login.render());
    }

    public Result login(){
        DynamicForm dynamicForm = formFactory.form().bindFromRequest();
        String username = dynamicForm.get("loginUsername");
        String password = dynamicForm.get("loginPassword");
        PasswordManager passwordManager = new PasswordManager();
        if(accountRepository.usernameExist(username))
        {
            String salt = accountRepository.getSalt(username);
            String hash = accountRepository.getHash(username);
            if(passwordManager.isPasswordCorrect(password, salt, hash))
            {
                return ok("LOGGED IN");
            }
            else
            {
                return ok("PASSWORD INCORRECT");
            }
        }
        else
        {
            return ok("USER DOES NOT EXIST");
        }
    }

    public Result register(){
        DynamicForm dynamicForm = formFactory.form().bindFromRequest();
        String username = dynamicForm.get("registerUsername");
        String password = dynamicForm.get("registerPassword");
        String email = dynamicForm.get("registerEmail");
        if(!accountRepository.usernameExist(username) && !accountRepository.emailExist(email))
        {
            PasswordManager passwordManager = new PasswordManager();
            String salt = passwordManager.generateSalt();
            String hashedPassword = passwordManager.hashPassword(password, salt);
            accountRepository.createAccount(username, hashedPassword, salt, email);
            return ok("NEW USER CREATED");

        }
        else
        {
            return ok("USER ALREADY EXISTS");
        }
    }
}