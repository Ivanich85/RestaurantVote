package restaurantvote.web;


import restaurantvote.model.Restaurant;
import restaurantvote.model.User;
import restaurantvote.model.Vote;
import restaurantvote.model.values.Role;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static restaurantvote.model.AbstractBaseEntity.START_SEQ;

public class SecurityUtil {
    private static final int USER_ID = START_SEQ;
    private static final int VOTE_ID = START_SEQ + 4;
    private static final Restaurant BK_REST = new Restaurant(START_SEQ + 2, "Burger King");

    private static final Vote VOTE_USER_1 = new Vote(VOTE_ID, BK_REST);
    private static final Vote VOTE_USER_2 = new Vote(VOTE_ID + 1, BK_REST);
    private static final Vote VOTE_USER_3 = new Vote(VOTE_ID + 2, BK_REST);

    private static final Set<Role> userRoles = Stream.of( Role.ROLE_USER).collect(Collectors.toSet());
    private static final Set<Vote> userVotes = Stream.of(VOTE_USER_1, VOTE_USER_2, VOTE_USER_3).collect(Collectors.toSet());
    private static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", userRoles, userVotes);

    public static User getAuthUser() {
        return USER;
    }
}