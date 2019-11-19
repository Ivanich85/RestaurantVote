package restaurantvote.model;

import org.apache.commons.collections.CollectionUtils;
import restaurantvote.model.values.Role;

import java.util.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class User extends AbstractNamedEntity {
    private String password;

    private Date registered;

    private String email;

    private Set<Role> roles;

    private Boolean enabled;

    public User() {}

    public User(Integer id, String name, String email, String password, Role role, Role... roles) {
        this(id, name, email, password, true, new Date(), EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, boolean enabled, Date registered, Set<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        setRoles(roles);
    }

    public Boolean getEnabled() {
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
        this.roles = CollectionUtils.isEmpty(roles) ? new HashSet<>() : roles;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return CollectionUtils.isNotEmpty(roles) && roles.contains(Role.ADMIN_ROLE);
    }
}
