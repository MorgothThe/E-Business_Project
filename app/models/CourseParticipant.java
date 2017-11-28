package models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="site.course_participant")
public class CourseParticipant {

    @ManyToOne
    @JoinColumn(name="course_id")
    public Course course;

    @ManyToOne
    @JoinColumn(name="participant_id")
    public User participant;

    //TODO primary key
}
