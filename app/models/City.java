package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "site.city")
public class City {

    @Id
    public Integer id;

    public String name;

    @OneToMany(mappedBy = "city")
    public List<CountryRegionCity> countryRegionCityList;

    @OneToMany(mappedBy = "city")
    public List<CourseLocation> courseLocationList;
}
