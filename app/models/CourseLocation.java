package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "site.course_location")
public class CourseLocation {

    @Id
    public Integer id;

    @ManyToOne
    public Course course;

    @ManyToOne
    public City city;

    public String address;
}
