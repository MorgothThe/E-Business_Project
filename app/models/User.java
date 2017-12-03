package models;

import io.ebean.Model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="site.user")
public class User extends Model{

    @Id
    public Integer id;


    @ManyToOne
    @JoinColumn(name="city_id")
    public City city;

    public String firstName;
    public String middleName;
    public String lastName;

    public Timestamp birthDate;
    public Integer age;
    public String description;

    @OneToMany(mappedBy = "user")
    public List<Contact> contactList;

    @OneToMany(mappedBy = "teacher")
    public List<Course> courseList;

    @OneToMany(mappedBy = "participant")
    public List<CourseParticipant> courseParticipantList;

    @OneToMany(mappedBy = "reviewer")
    public List<Review> reviewList;
}
