package repositories;

import io.ebean.Ebean;
import models.User;

public class UserRepository extends GenericRepository<User> {

    public UserRepository() { super(User.class);}


    public void createUser(Integer id){
        User user = new User();

        user.id = id;

        Ebean.save(user);
    }

    public User getUserWithContact(Integer userID){
        User user = Ebean.find(typeParameterClass)
                .fetch("contactList")
                .fetch("contactList.contactType")
                .where()
                .eq("id", userID)
                .findUnique();
        return user;
    }

}