package controllers;


import models.Account;
import models.Course;
import models.Role;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.AccountRepository;
import repositories.CourseRepository;
import repositories.UserRepository;

import javax.inject.Inject;
import java.util.List;


public class ProfileController extends Controller{

    @Inject
    private UserRepository userRepository;

    @Inject
    private AccountRepository accountRepository;



    public Result index() {

        String usernameID = session().get("accountID");
        if(usernameID == null){
            return redirect("/");
        }
        else{
            Integer participantID = Integer.parseInt(session().get("accountID"));

            User user = userRepository.findByID(participantID);
            Account account = accountRepository.findByID(participantID);
            Role role = accountRepository.getRole(account.username);

            return ok(views.html.profile_page.render(user, account, role));

        }

    }
}
