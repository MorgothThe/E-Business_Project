package repositories;

import io.ebean.Ebean;
import models.Course;

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
}
