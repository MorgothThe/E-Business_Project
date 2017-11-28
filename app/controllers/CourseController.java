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
        Course course = courseRepository.findByID(id);
        return ok("COURSE DETAILS ID: " + id.toString());
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
}
