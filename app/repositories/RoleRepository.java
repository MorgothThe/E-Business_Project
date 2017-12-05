package repositories;

import io.ebean.Ebean;
import models.Course;
import models.Role;

import java.util.List;

public class RoleRepository extends GenericRepository<Role> {

    public RoleRepository() { super(Role.class);}

    public Role getByRoleName(String role){
        Role roleclass = Ebean

                .find(typeParameterClass)
                .where()
                .eq("role_name", role)
                .findUnique();
        return roleclass;
    }
}
