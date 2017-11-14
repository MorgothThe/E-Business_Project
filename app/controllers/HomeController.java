package controllers;

import models.CourseCategory;
import models.User;
import play.mvc.*;

import java.util.List;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {

//        List<CourseCategory> courseCategories = CourseCategory.find.all();
//        List<User> users = User.find.all();


        return ok(views.html.index.render());
    }



}
