package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="site.category")
public class Category extends Model{

    @Id
    public Integer id;

    @OneToOne
    @JoinColumn(name="id")
    public Category parent_id;

    public String name;
    public String description;

    @OneToMany(mappedBy = "pk.category")
    public List<CourseCategory> courseCategoryList;
}
