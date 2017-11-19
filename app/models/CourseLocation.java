package models;

import javax.persistence.*;

@Entity
@Table(name="site.course_location")
public class CourseLocation {

    @Id
    @JoinColumn(name="id")
    public Course course;

    @ManyToOne
    @JoinColumn(name="site.city")
    public City city;

    public String address;

    //TODO
}
