package controllers;

import models.Category;
import models.Course;
import play.mvc.*;
import repositories.CategoryRepository;
import repositories.CourseRepository;

import javax.inject.Inject;
import java.util.List;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    @Inject
    private CourseRepository courseRepository;

    @Inject
    private CategoryRepository categoryRepository;


    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {

        List<Course> courseList = courseRepository.getAll();
        List<Category> categoryList = categoryRepository.getAll();

        return ok(views.html.index.render(courseList, categoryList));
    }


}
