package models;

import io.ebean.Model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="site.course_category")
public class CourseCategory extends Model{

    @EmbeddedId
    public CourseCategoryPK pk;

    @ManyToOne
    @JoinColumn(name="category_id", insertable = false, updatable = false)
    public Category category;

    @ManyToOne
    @JoinColumn(name="course_id", insertable = false, updatable = false)
    public Course course;

    @Embeddable
    public class CourseCategoryPK implements Serializable{

        @Column(name="course_id")
        public Course course;
        @Column(name="category_id")
        public Category category;
    }

}
