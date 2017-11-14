package models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "site.country_region_city")
public class CountryRegionCity {

    @EmbeddedId
    public CountryRegionCityPk pk;

    @ManyToOne
    public City city;

    @ManyToOne
    public Region region;

    @ManyToOne
    public Country country;

    @Embeddable
    public static class CountryRegionCityPk implements Serializable{

        @Column(name = "city_id")
        public City city;

        @Column(name = "region_id")
        public Region region;

        public CountryRegionCityPk(){

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
