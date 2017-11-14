package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "security.role")
public class Role {

    @Id
    public Integer id;

    @OneToOne
    @JoinColumn(name = "id")
    public Role parentId;

    public String roleName;

    @OneToMany(mappedBy = "role")
    public List<AccountRole> accountRoleList;

}
