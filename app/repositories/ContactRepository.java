package repositories;

import models.Contact;

public class ContactRepository extends GenericRepository<Contact> {

    public ContactRepository(){
        super(Contact.class);
    }

}
