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
import java.math.BigDecimal;
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
        String accountID = session().get("accountID");
        boolean isSignedUp = false;
        if(accountID != null){
            Integer participantID = Integer.parseInt(session().get("accountID"));
            isSignedUp = courseRepository.isSignedUp(participantID, id);
        }
        return ok(views.html.course.render(course, isSignedUp));
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

    public Result signForCourse(Integer courseID){
        Integer participantID = Integer.parseInt(session().get("accountID"));
        User user = userRepository.findByID(participantID);
        courseRepository.addParticipant(courseID, user);
        flash("signed-up-for-course", "You have successfully sign up for this course");
        return redirect("/");
    }

    public Result newCourse(){
        List<Category> categories = categoryRepository.getAll();
        return ok(views.html.create_course.render(categories));
    }

    public Result createNewCourse(){
        DynamicForm dynamicForm = formFactory.form().bindFromRequest();
        String courseName = dynamicForm.get("courseName");
        String courseDescription = dynamicForm.get("courseDescription");
        String category = dynamicForm.get("category");
        String stringPrice = dynamicForm.get("price");

        BigDecimal price = new BigDecimal(stringPrice.replaceAll(",", ""));

        Integer maxParticipants = Integer.parseInt(dynamicForm.get("maxParticipant"));
        Integer numberOfMeetings = Integer.parseInt(dynamicForm.get("numberOfMeetings"));

        Integer teacherID = Integer.parseInt(session().get("accountID"));
        User teacher = userRepository.findByID(teacherID);

        courseRepository.createNewCourse(courseName, courseDescription, teacher, price, maxParticipants, numberOfMeetings);

        //currency
        //mettings

        flash("course-created", "New course was successfully created");
        return redirect("/");
    }


}
