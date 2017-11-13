package models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name="site.review")
public class Review {

    @ManyToOne
    @JoinColumn(name="site.user")
    public User reviewer;

    @ManyToOne
    @JoinColumn(name="id")
    public Course course;

    public BigDecimal rating;
    public String content;
    public Timestamp created_at;

}
