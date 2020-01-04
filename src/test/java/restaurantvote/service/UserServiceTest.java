package restaurantvote.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import restaurantvote.AbstractTest;
import restaurantvote.UserTestData;
import restaurantvote.VoteTestData;
import restaurantvote.model.User;
import restaurantvote.model.Vote;
import restaurantvote.model.values.Role;
import restaurantvote.util.NotFoundException;
import restaurantvote.util.SecondVoteDeniedException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static restaurantvote.RestaurantTestData.*;
import static restaurantvote.UserTestData.*;
import static restaurantvote.VoteTestData.*;

public class UserServiceTest extends AbstractTest {

    @Autowired
    private UserService service;
    @Autowired
    private RestaurantService restaurantService;

    @Test
    public void create() {
        User newUser = new User(null, "New", "new@gmail.com", "newPass", false,
                LocalDateTime.now(), Collections.singleton(Role.ROLE_USER), userVotes);
        User created = service.create(newUser);
        Integer newId = created.getId();
        newUser.setId(newId);
        assertMatch(created, newUser);
        assertMatch(service.get(newId), newUser);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void duplicateMailCreate() throws Exception {
        service.create(new User(null, "Duplicate", "admin@gmail.com", "newPass", false,
                LocalDateTime.now(), Collections.singleton(Role.ROLE_USER), userVotes));
    }

    @Test
    public void update() {
        User updated = UserTestData.getUpdated();
        service.update(new User(updated));
        assertMatch(service.get(USER_ID), updated);
    }

    @Test
    public void delete() {
        User deletedUser = getDeleted();
        service.delete(USER_ID);
        assertMatch(service.get(USER_ID), deletedUser);
    }

    @Test(expected = NotFoundException.class)
    public void deletedNotFound() throws Exception {
        service.delete(1);
    }

    @Test
    public void get() {
        User user = service.get(USER_ID);
        assertMatch(user, USER);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void getWithVotes() {
        User user = service.getWithVotes(USER_ID);
        assertMatchWithVotes(user, USER);
    }

    @Test
    public void getByEmail() {
        User user = service.getByEmail("user@yandex.ru");
        assertMatch(user, USER);
    }

    @Test(expected = NotFoundException.class)
    public void getByNotExistEmail() throws Exception {
        service.getByEmail("user@mail.ru");
    }

    @Test
    public void getAll() {
        List<User> all = service.getAll();
        assertMatch(all, ADMIN, USER);
    }

    @Test
    public void voteFirstTimeBeforeLimit() {
        Vote newVote = service.vote(USER, MC_REST, FIRST_VOTE_TIME_BEFORE_LIMIT);

        Set<Vote> bkAfterVoteListActual = restaurantService.get(RESTAURANT_ID).getRatings();
        Set<Vote> bkAfterVoteListExpected = Stream.of(VOTE_USER_1, VOTE_ADMIN_1).collect(Collectors.toSet());

        Set<Vote> mcAfterVoteListActual = restaurantService.get(RESTAURANT_ID + 1).getRatings();
        Set<Vote> mcAfterVoteListExpected = Stream.of(VOTE_USER_2, VOTE_USER_4, VOTE_USER_3, newVote).collect(Collectors.toSet());

        VoteTestData.assertMatchVoteId(bkAfterVoteListActual, bkAfterVoteListExpected);
        VoteTestData.assertMatchVoteId(mcAfterVoteListActual, mcAfterVoteListExpected);
    }

    @Test
    public void voteFirstTimeAfterLimit() {
        Vote newVote = service.vote(USER, MC_REST, FIRST_VOTE_TIME_AFTER_LIMIT);

        Set<Vote> bkAfterVoteListActual = restaurantService.get(RESTAURANT_ID).getRatings();
        Set<Vote> bkAfterVoteListExpected = Stream.of(VOTE_USER_1, VOTE_ADMIN_1).collect(Collectors.toSet());

        Set<Vote> mcAfterVoteListActual = restaurantService.get(RESTAURANT_ID + 1).getRatings();
        Set<Vote> mcAfterVoteListExpected = Stream.of(VOTE_USER_2, VOTE_USER_4, VOTE_USER_3, newVote).collect(Collectors.toSet());

        VoteTestData.assertMatchVoteId(bkAfterVoteListActual, bkAfterVoteListExpected);
        VoteTestData.assertMatchVoteId(mcAfterVoteListActual, mcAfterVoteListExpected);
    }

    @Test
    public void voteSecondTimePermitted() {
        Set<Vote> bkBeforeVoteListActual = restaurantService.get(RESTAURANT_ID).getRatings();
        Set<Vote> bkBeforeVoteListExpected = Stream.of(VOTE_USER_1, VOTE_ADMIN_1).collect(Collectors.toSet());

        Set<Vote> mcBeforeVoteListActual = restaurantService.get(RESTAURANT_ID + 1).getRatings();
        Set<Vote> mcBeforeVoteListExpected = Stream.of(VOTE_USER_2, VOTE_USER_4, VOTE_USER_3).collect(Collectors.toSet());

        Vote changedVote = service.vote(USER, MC_REST, SECOND_VOTE_TIME_BEFORE_LIMIT);

        Set<Vote> bkAfterVoteListActual = restaurantService.get(RESTAURANT_ID).getRatings();
        Set<Vote> bkAfterVoteListExpected = Stream.of(VOTE_ADMIN_1).collect(Collectors.toSet());

        Set<Vote> mcAfterVoteListActual = restaurantService.get(RESTAURANT_ID + 1).getRatings();
        Set<Vote> mcAfterVoteListExpected = Stream.of(VOTE_USER_2, VOTE_USER_4, VOTE_USER_3, changedVote).collect(Collectors.toSet());

        VoteTestData.assertMatchVoteId(bkBeforeVoteListActual, bkBeforeVoteListExpected);
        VoteTestData.assertMatchVoteId(mcBeforeVoteListActual, mcBeforeVoteListExpected);
        VoteTestData.assertMatchVoteId(bkAfterVoteListActual, bkAfterVoteListExpected);
        VoteTestData.assertMatchVoteId(mcAfterVoteListActual, mcAfterVoteListExpected);
    }

    @Test(expected = SecondVoteDeniedException.class)
    public void voteSecondTimeDenied() throws Exception {
        service.vote(USER, MC_REST, SECOND_VOTE_TIME_AFTER_LIMIT);
    }
}