package restaurantvote.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import restaurantvote.AbstractTest;
import restaurantvote.VoteTestData;
import restaurantvote.model.Vote;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static restaurantvote.UserTestData.USER_ID;
import static restaurantvote.VoteTestData.*;

public class VoteRepositoryImplTest extends AbstractTest {

    @Autowired
    private VoteRepository repository;

    @Test
    public void getByUserAndRestaurant() {
        List<Vote> actual = repository.getByUser(USER_ID);
        List<Vote> expected = Stream.of(VOTE_USER_4, VOTE_USER_3, VOTE_USER_2, VOTE_USER_1).collect(Collectors.toList());
        VoteTestData.assertMatch(actual, expected);
    }

    @Test
    public void getByUserAndRestaurantNotFoundUser() {
        List<Vote> actual = repository.getByUser(10);
        List<Vote> expected = new ArrayList<>();
        VoteTestData.assertMatch(actual, expected);
    }

}