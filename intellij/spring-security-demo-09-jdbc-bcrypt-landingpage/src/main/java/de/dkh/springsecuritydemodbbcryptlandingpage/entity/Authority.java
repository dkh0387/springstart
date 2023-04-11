package de.dkh.springsecuritydemodbbcryptlandingpage.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "authorities"
/*        , uniqueConstraints = {@UniqueConstraint(name = "authorities_idx_1",
        columnNames = {"username", "authority"})}*/
)
@NoArgsConstructor
public class Authority extends PersistentObject {

    public static final String ROLE_PREFIX = "ROLE_";
    @OneToOne
    @JoinColumn(name = "user_id")
    @Getter
    @Setter
    private User user;

    @Column
    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String authority;

    public Authority(User user, int index) {
        this.setUser(user);
        this.username = user.getUsername();
        this.setAuthority(ROLE_PREFIX + user.getRoles()[index]);
    }

}
