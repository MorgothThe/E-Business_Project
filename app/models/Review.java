package models;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "site.review")
public class Review {

    @EmbeddedId
    public ReviewPk pk;

    @ManyToOne
    public Course id;

    @ManyToOne
    public User reviewer;

    public BigDecimal rating;

    public String content;

    public Date createdAt;

    @Embeddable
    public static class ReviewPk implements Serializable{

        @Column(name = "id")
        public Course id;

        @Column(name = "reviewer_id")
        public User reviewer;

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
