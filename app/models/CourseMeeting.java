package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "site.course_meeting")
public class CourseMeeting {

    @Id
    public Integer id;

    @ManyToOne
    public Course course;

    public Timestamp time;
}
