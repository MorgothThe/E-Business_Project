package repositories;

import io.ebean.Ebean;
import models.Course;
import models.CourseParticipant;
import models.User;

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
}
