package models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="site.country_region_city")
public class CountryRegionCity {

/*    @ManyToOne
    public Integer country_id;

    @Embeddable
    public class CountryRegionCityKey {

        @ManyToOne
        public Integer city_id;
        @ManyToOne
        public Integer region_id;
    }*/

//    @EmbeddedId
//    public CountryRegionCityPK pk;

    @ManyToOne
    @JoinColumn(name = "city_id")
    public City city;

    @ManyToOne
    @JoinColumn(name = "region_id")
    public Region region;

    @ManyToOne
    @JoinColumn(name="country_id")
    public Country country;

    /*@Embeddable
    public class CountryRegionCityPK implements Serializable{
        @Column(name = "city_id")
        public City city_id;
        @Column(name = "region_id")
        public Region region_id;

        CountryRegionCityPK(City city, Region region){
            this.City
        }

    }*/

}



