package models;

import io.ebean.Model;

import javax.persistence.*;

@Entity
@Table(name="site.contact")
public class Contact extends Model{

    @Id
    public Integer id;

    @ManyToOne
    @JoinColumn(name="user_id")
    public User user;

    @ManyToOne
    @JoinColumn(name="contact_type_id")
    public ContactType contactType;

    public String contact;
}
