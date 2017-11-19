package models;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

public class CourseLocation {

    @Id
    public Integer id;

    @ManyToOne
    public Course course;

    @ManyToOne
    public City city;

    public String address;
}
