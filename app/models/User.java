package models;

import io.ebean.Finder;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "site.user")
public class User {

    //not sure

    @PrimaryKeyJoinColumn(name = "id")
    public Account id;

    public String firstName;
    public String middleName;
    public String lastName;
    public Date birthDate;
    public Integer age;
    public String description;

//    @OneToMany(mappedBy = "user")
//    public List<Contact> contactList;


    public static final Finder<Long, User> find = new Finder<>(User.class);

}
