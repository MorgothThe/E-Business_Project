package controllers;

import common.PasswordManager;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.AccountRepository;
import repositories.UserRepository;

import javax.inject.Inject;

public class LoginController extends Controller {

    @Inject
    private FormFactory formFactory;

    @Inject
    private AccountRepository accountRepository;

    @Inject
    private UserRepository userRepository;

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
                session("username", username);
                Integer accountID = accountRepository.getID(username);
                session("accountID", accountID.toString());
                flash("logged-in", "User has been succesfully logged in!");
                return redirect("/");
            }
            else
            {
                flash("password-incorect", "The password is not correct");
                return redirect("/");
            }
        }
        else
        {
            flash("no-user", "The user does not exist");
            return redirect("/");
        }
    }

    public Result logout(){

        flash("logged-out", "You have successfully logged out");
        session().remove("username");
        session().remove("accountID");

        return redirect("/");
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
            Integer accountID = accountRepository.getID(username);
            userRepository.createUser(accountID);
            return ok("NEW USER CREATED");

        }
        else
        {
            return ok("USER ALREADY EXISTS");
        }
    }
}
