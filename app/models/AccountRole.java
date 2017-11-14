package models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "security.account_role")
public class AccountRole {

    @EmbeddedId
    public AccountRolePk pk;

    @ManyToOne
    public Account account;

    @ManyToOne
    public Role role;

    @Embeddable
    public static class AccountRolePk implements Serializable{

        @Column(name = "account_id")
        public Account account;

        @Column(name = "role_id")
        public Role role;

        public AccountRolePk(){

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
