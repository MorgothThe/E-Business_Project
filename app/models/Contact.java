package models;

import javax.persistence.*;

@Entity
@Table(name = "site.contact")
public class Contact {

    @Id
    public Integer id;

//    @ManyToOne
//    public User user;

    public String contact;
}
