package repositories;

import models.Course;

public class CourseRepository extends GenericRepository<Course> {


    public CourseRepository() {
        super(Course.class);
    }
}
