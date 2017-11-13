package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "site.currency")
public class Currency {

    @Id
    public Integer id;

    public String name;
    public String symbol;

    @OneToMany(mappedBy = "course")
    public List<Course> courseList;

}
