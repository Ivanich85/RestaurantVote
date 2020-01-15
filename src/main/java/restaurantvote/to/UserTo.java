package restaurantvote.to;

import org.apache.commons.collections.CollectionUtils;
import restaurantvote.model.User;
import restaurantvote.model.values.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static restaurantvote.to.TpUtils.fromSetToList;

public class UserTo {
    private Integer id;

    @NotEmpty
    private String name;

    @Size(min = 5, max = 15, message = " must between 5 and 15 characters")
    private String password;

    @NotEmpty
    @Email
    private String email;
    private LocalDateTime registered;
    private List<Role> roles;
    private List<VoteTo> voteTos;
    private Boolean enabled;

    public static UserTo createTo(User user) {
        UserTo to = new UserTo();
        to.id = user.getId();
        to.name = user.getName();
        to.password = user.getPassword();
        to.email = user.getEmail();
        to.registered = user.getRegistered();
        to.roles = fromSetToList(user.getRoles());
        to.voteTos = VoteTo.createTos(user.getVotes());
        to.enabled = user.isEnabled();
        return to;
    }

    public static List<UserTo> createTos(Collection<User> users) {
        return CollectionUtils.isEmpty(users) ?
                new ArrayList<>() : users.stream().map(u -> createTo(u)).collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public List<VoteTo> getVoteTos() {
        return voteTos;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("UserTo:{");
        builder
                .append("id=" + id)
                .append(", name=" + name)
                .append(", password=" + password)
                .append(", email=" + email)
                .append(", registered=" + registered)
                .append(", roles=[" + roles.stream().map(Role::toString))
                .append("]")
                .append(", voteTos=[" + voteTos)
                .append("]")
                .append(", enabled=" + enabled)
                .append("}");
        return builder.toString();
    }
}
