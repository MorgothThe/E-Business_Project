package repositories;

import io.ebean.Ebean;
import models.Course;
import models.CourseParticipant;
import models.User;

import java.math.BigDecimal;
import java.util.List;

public class CourseRepository extends GenericRepository<Course> {


    public CourseRepository() {
        super(Course.class);
    }

    public List<Course> search(String searchTerm){

        List<Course> courses = Ebean
                .find(typeParameterClass)
                .where()
                .ilike("name", searchTerm)
                .findList();

        return courses;
    }

    public List<Course> getByTeacherID(Integer id){
        List<Course> courses = Ebean
                .find(typeParameterClass)
                .where()
                .eq("teacherID", id)
                .findList();
        return courses;
    }

    public List<Course> getByStudentID(Integer id){
        List<Course> courses = Ebean
                .find(typeParameterClass)
                .fetch("courseParticipantList")
                .where()
                .eq("courseParticipantList.participant.id", id)
                .findList();
        return courses;
    }

    public List<Course> getByCategoryID(Integer id){
        List<Course> beforeQuery = getAll();
        List<Course> courses = Ebean
                .find(typeParameterClass)
                .fetch("courseCategoryList")
                .where()
                .eq("courseCategoryList.category.id", id)
                .findList();
        return courses;
    }

    public void addParticipant(Integer id, User user){
        Course course = findByID(id);
        course.participants += 1;
        Ebean.update(course);
        CourseParticipant courseParticipant = new CourseParticipant();
        courseParticipant.course = course;
        courseParticipant.participant = user;
        Ebean.save(courseParticipant);
    }

    public void createNewCourse(String courseName, String courseDescription, User teacher, BigDecimal price, Integer maxParticipants){
        Course course = new Course();
        course.name = courseName;
        course.description = courseDescription;
        course.teacher = teacher;
        course.price = price;
        course.participants = 0;
        course.max_participants = maxParticipants;
        Ebean.save(course);
    }

    public boolean isSignedUp(Integer userID, Integer courseID){
        CourseParticipant courseParticipant = Ebean.find(CourseParticipant.class)
                .where()
                .eq("participant.id", userID)
                .eq("course.id", courseID)
                .findUnique();
        if(courseParticipant == null){
            return false;
        }
        else{
            return true;
        }
    }
}
