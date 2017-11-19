package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "site.course_category")
public class CourseCategory extends Model {

    @EmbeddedId
    public CourseCategoryPk pk;

    @ManyToOne
    public Course course;

    @ManyToOne
    public Category category;

    @Embeddable
    public static class CourseCategoryPk implements Serializable{

        @Column(name = "category_id")
        public Category category;

        @Column(name = "course_id")
        public Course course;


        public CourseCategoryPk(){

        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }


}
