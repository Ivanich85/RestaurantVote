package restaurantvote.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import restaurantvote.AbstractServiceTest;
import restaurantvote.UserTestData;
import restaurantvote.VoteTestData;
import restaurantvote.model.Vote;
import restaurantvote.util.NotFoundException;

import java.time.Month;

import static java.time.LocalDateTime.of;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static restaurantvote.RestaurantTestData.*;
import static restaurantvote.UserTestData.*;
import static restaurantvote.VoteTestData.*;

public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Test
    public void create() {
        Vote newVote = new Vote(null, of(2019, Month.DECEMBER, 22, 15, 20), BK_REST);
        newVote.setUser(UserTestData.USER);
        Vote savedVote = service.create(newVote);
        int newId = savedVote.getId();
        newVote.setId(newId);
        assertMatch(newVote, savedVote);
        assertMatch(service.get(newVote.getId(), newVote.getUser().getId()), newVote);
    }

    @Test
    public void update() {
        Vote updated = VoteTestData.getUpdated();
        updated.setUser(USER);
        service.update(new Vote(updated));
        assertMatch(service.get(VOTE_ID, USER_ID), updated);
    }

    @Test
    public void updateNotAuth() {
        Vote updated = VoteTestData.getUpdated();
        updated.setUser(ADMIN);
        service.update(new Vote(updated));
        assertThrows(NotFoundException.class, () -> assertMatch(service.get(VOTE_ID, USER_ID), updated));
    }

    @Test
    public void delete() throws Exception {
        service.delete(VOTE_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(VOTE_ID, USER_ID));
    }

    @Test
    public void deleteNotAuth() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(VOTE_ID, 1));
    }

    @Test
    public void get() {
        Vote actual = service.get(VOTE_ID, USER_ID);
        Vote expectedVote = new Vote(VOTE_USER_1);
        expectedVote.setUser(USER);
        expectedVote.setRestaurant(BK_REST);
        assertMatch(actual, expectedVote);
    }

    @Test
    public void getNotAuth() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(VOTE_ID, 1));
    }
}