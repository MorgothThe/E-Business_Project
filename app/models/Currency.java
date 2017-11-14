package models;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "site.currency")
public class Currency extends Model{

    @Id
    public Integer id;
    public String name;
    public String symbol;

    @OneToMany(mappedBy = "currency")
    public List<Course> courseList;

}
