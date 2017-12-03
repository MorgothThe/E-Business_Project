package controllers;


import models.Course;
import models.User;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import models.Category;
import models.Course;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.CategoryRepository;

import repositories.CourseRepository;
import repositories.UserRepository;

import javax.inject.Inject;
import java.util.List;

public class CourseController extends Controller {



    @Inject
    private CategoryRepository categoryRepository;

    @Inject
    private CourseRepository courseRepository;

    @Inject
    private UserRepository userRepository;


    @Inject
    private FormFactory formFactory;


    public Result search(){
        DynamicForm requestData = formFactory.form().bindFromRequest();
        String searchTerm = requestData.get("search");
        searchTerm = "%" + searchTerm + "%";
        List<Course> courseList = courseRepository.search(searchTerm);
        List<Category> categoryList = categoryRepository.getAll();

        return ok(views.html.index.render(courseList, categoryList));
    }


    public Result searchByCategory(Integer categoryID){
        List<Course> courseList = courseRepository.getByCategoryID(categoryID);
        List<Category> categoryList = categoryRepository.getAll();
        return ok(views.html.index.render(courseList, categoryList));
    }


    public Result index(Integer id){
        Course course = courseRepository.findByID(id);
        return ok(views.html.course.render(course));
    }

    public Result myTeacherCourses(){
        Integer teacherID = Integer.parseInt(session().get("accountID"));
        List<Course> courseList = courseRepository.getByTeacherID(teacherID);
        return ok("COURSES FOR TEACHER ID: " + teacherID.toString());
    }

    public Result myStudentCourses(){
        Integer participantID = Integer.parseInt(session().get("accountID"));
        List<Course> courseList = courseRepository.getByStudentID(participantID);
        return ok("COURSES FOR STUDENT ID: " + participantID.toString());
    }

    public void signForCourse(Integer courseID){
        Integer participantID = Integer.parseInt(session().get("accountID"));
        User user = userRepository.findByID(participantID);
        courseRepository.addParticipant(courseID, user);
    }


}
