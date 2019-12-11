package restaurantvote.model;

import org.apache.commons.collections.CollectionUtils;
import restaurantvote.model.values.Role;
import restaurantvote.model.values.Vote;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id = :id"),
        @NamedQuery(name = User.BY_EMAIL, query = "SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email=:email"),
        @NamedQuery(name = User.ALL_SORTED, query = "SELECT u FROM User u LEFT JOIN FETCH u.roles ORDER BY u.name, u.email")
})
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class User extends AbstractNamedEntity {
    public static final String DELETE = "User.delete";
    public static final String BY_EMAIL = "User.getByEmail";
    public static final String ALL_SORTED = "User.getAllSorted";

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 100)
    private String password;

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private Date registered;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_votes", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "vote")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Vote> votes;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private Boolean enabled;

    public User() {}

    public User(Integer id, String name, String email, String password, Set<Role> roles, Set<Vote> votes) {
        this(id, name, email, password, true, new Date(), roles,  votes);
    }

    public User(User u) {
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.isEnabled(), u.getRegistered(), u.getRoles(), u.getVotes());
    }

    public User(Integer id, String name, String email, String password, boolean enabled, Date registered, Set<Role> roles, Set<Vote> votes ) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        setRoles(roles);
        setVotes(votes);
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return CollectionUtils.isNotEmpty(roles) && roles.contains(Role.ROLE_ADMIN);
    }
}
