package controllers;


import models.Course;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.CourseRepository;
import repositories.UserRepository;

import javax.inject.Inject;


public class ProfileController extends Controller{

    @Inject
    private UserRepository userRepository;



    public Result index() {

        String usernameID = session().get("accountID");
        if(usernameID == null){
            return redirect("/");
        }
        else{
            Integer participantID = Integer.parseInt(session().get("accountID"));

            User user = userRepository.findByID(participantID);

            return ok(views.html.profile_page.render(user));

        }

    }
}
