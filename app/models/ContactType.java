package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "site.contact_type")
public class ContactType {

    @Id
    public Integer id;
    public String name;

    @OneToMany(mappedBy = "contactType")
    public List<Contact> contactList;
}
