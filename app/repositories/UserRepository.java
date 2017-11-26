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
}