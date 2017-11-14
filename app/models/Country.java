package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "site.country")
public class Country {

    @Id
    public Integer id;

    public String name;

    @OneToMany(mappedBy = "country")
    public List<CountryRegionCity> countryRegionCityList;
}
