package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="site.counrty")
public class Country {

    @Id
    public Integer id;
    public String name;

    @OneToMany(mappedBy = "country")
    public List<CountryRegionCity> countryRegionCityList;
}
