package models;

import io.ebean.Model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Table(name = "site.course")
@Entity
public class Course extends Model{

    @Id
    public Integer id;

    @ManyToOne
    public Currency currency;

    public String name;
    public String description;
    public Integer participants;
    public Integer maxParticipants;
    public BigDecimal price;
    public Integer meetings;
    public Timestamp createdAt;

    @OneToMany(mappedBy = "course")
    public List<CourseCategory> courseCategoryList;

    @OneToMany(mappedBy = "course")
    public List<CourseMeeting> courseMeetingList;

    @OneToMany(mappedBy = "course")
    public List<CourseParticipant> courseParticipantList;

//    @OneToMany(mappedBy = "course")
//    public List<CourseLocation> courseLocationList;
}
