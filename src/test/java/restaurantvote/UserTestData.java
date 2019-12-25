package restaurantvote;

import restaurantvote.model.User;
import restaurantvote.model.values.Role;
import restaurantvote.model.Vote;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static restaurantvote.VoteTestData.*;
import static restaurantvote.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final Set<Role> userRoles = Stream.of( Role.ROLE_USER).collect(Collectors.toSet());
    public static final Set<Role> adminRoles = Stream.of( Role.ROLE_ADMIN).collect(Collectors.toSet());

    public static final Set<Vote> userVotes = Stream.of(VOTE_USER_1, VOTE_USER_2, VOTE_USER_3, VOTE_USER_4).collect(Collectors.toSet());
    public static final Set<Vote> adminVotes = Stream.of(VOTE_ADMIN_1).collect(Collectors.toSet());

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", userRoles, userVotes);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", adminRoles, adminVotes);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "votes");
    }

    public static void assertMatchWithVotes(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "votes").isEqualTo(expected);
    }

    public static User getUpdated() {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        updated.setRoles(adminRoles);
        return updated;
    }

    public static User getDeleted() {
        User updated = new User(USER);
        updated.setEnabled(false);
        return updated;
    }
}
