package restaurantvote;

import restaurantvote.model.Vote;

import java.time.Month;

import static java.time.LocalDateTime.of;

import static org.assertj.core.api.Assertions.assertThat;
import static restaurantvote.RestaurantTestData.BK_REST;
import static restaurantvote.RestaurantTestData.MC_REST;
import static restaurantvote.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {
    public static final int VOTE_ID = START_SEQ + 4;

    public static final Vote VOTE_USER_1 = new Vote(VOTE_ID, of(2019, Month.DECEMBER, 22, 15, 20), BK_REST);
    public static final Vote VOTE_USER_2 = new Vote(VOTE_ID + 1, of(2019, Month.DECEMBER, 23, 16, 20), MC_REST);
    public static final Vote VOTE_USER_3 = new Vote(VOTE_ID + 2, of(2019, Month.DECEMBER, 24, 11, 20), MC_REST);
    public static final Vote VOTE_USER_4 = new Vote(VOTE_ID + 3, of(2019, Month.DECEMBER, 25, 12, 20), MC_REST);

    public static final Vote VOTE_ADMIN_1 = new Vote(VOTE_ID + 4,of(2019, Month.DECEMBER, 23, 15, 20), BK_REST);

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected);
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user").isEqualTo(expected);
    }

    public static void assertMatchVoteId(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user", "creationDate", "restaurant").isEqualTo(expected);
    }

    public static Vote getUpdated() {
        Vote updated = new Vote(VOTE_USER_1);
        updated.setRestaurant(MC_REST);
        return updated;
    }
}
