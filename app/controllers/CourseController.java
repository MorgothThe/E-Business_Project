package controllers;

import models.Course;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.CourseRepository;

import javax.inject.Inject;
import java.util.List;

public class CourseController extends Controller {

    @Inject
    private CourseRepository courseRepository;

    public Result search(String searchTerm){
        searchTerm = "%" + searchTerm + "%";
        List<Course> courseList = courseRepository.search(searchTerm);
        return ok("SEARCH TERM : " + searchTerm);
    }

    public Result index(Integer id){
        return ok("COURSE DETAILS ID: " + id.toString());
    }
}
