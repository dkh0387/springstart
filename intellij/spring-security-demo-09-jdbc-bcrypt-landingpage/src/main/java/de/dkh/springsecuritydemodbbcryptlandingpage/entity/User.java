package de.dkh.springsecuritydemodbbcryptlandingpage.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "users")
@NoArgsConstructor
@ToString
public class User extends PersistentObject {

    @Getter
    @Setter
    @Column
    private String username;

    @Getter
    @Setter
    @Column
    private String password;

    @Getter
    @Setter
    @Column
    private boolean enabled;

    @OneToOne(mappedBy = "user")
    @Getter
    @Setter
    private Authority authority;

    /**
     * NOTE: we need to set the attribute as {@linkplain Transient},
     * because otherwise hibernate will try to persist it.
     * We want it as a container for input of roles by a new user registration
     * see: {@code new-user.jsp}.
     */
    @Transient
    @Getter
    @Setter
    @Convert(converter = Set.class)
    private String[] roles;

    public Set<UserRoles> getUserRoles() {
        return Stream.of(UserRoles.values()).collect(Collectors.toSet());
    }

    enum UserRoles {
        EMPLOYER, MANAGER, ADMIN;

        @Getter
        private String label = this.name();

    }
}
