package models;

import io.ebean.Model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="site.course")
public class Course extends Model{

    @Id
    public Integer id;

    @ManyToOne
    @JoinColumn(name="currency_id")
    public Currency currency;

    @ManyToOne
    @JoinColumn(name="teacher_id")
    public User teacher;

    public String name;
    public String description;
    public Integer participants;
    public Integer max_participants;
    public BigDecimal price;
    public Integer meetings;
    public Timestamp created_at;


    @OneToMany(mappedBy = "pk.course")
    public List<CourseCategory> courseCategoryList;

    @OneToMany(mappedBy = "course")
    public List<CourseParticipant> courseParticipantList;
}
