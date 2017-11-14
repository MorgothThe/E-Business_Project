package models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "site.course_participant")
public class CourseParticipant {

    @EmbeddedId
    public CourseParticipantPk pk;

    @ManyToOne
    public Course course;

    @ManyToOne
    public User participant;

    @Embeddable
    public static class CourseParticipantPk implements Serializable{

        @Column(name = "course_id")
        public Course course;

        @Column(name = "participant_id")
        public User participant;

        public CourseParticipantPk(){

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
