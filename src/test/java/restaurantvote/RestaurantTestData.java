package restaurantvote;

import restaurantvote.model.Dish;
import restaurantvote.model.Restaurant;
import restaurantvote.model.Vote;
import restaurantvote.to.RestaurantTo;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static restaurantvote.DishTestData.*;
import static restaurantvote.VoteTestData.*;
import static restaurantvote.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static LocalDateTime FIRST_VOTE_TIME_BEFORE_LIMIT = LocalDateTime.of(2019, 12, 21, 10, 59);
    public static LocalDateTime FIRST_VOTE_TIME_AFTER_LIMIT = LocalDateTime.of(2019, 12, 21, 12, 59);
    public static LocalDateTime SECOND_VOTE_TIME_BEFORE_LIMIT = LocalDateTime.of(2019, 12, 22, 10, 59);
    public static LocalDateTime SECOND_VOTE_TIME_AFTER_LIMIT = LocalDateTime.of(2019, 12, 22, 11, 05);

    public static final int RESTAURANT_ID = START_SEQ + 2;

    public static final Set<Dish> BK_MENU = Stream.of(DISH_BK_1, DISH_BK_2, DISH_BK_3).collect(Collectors.toSet());
    public static final Set<Dish> MC_MENU = Stream.of(DISH_MC_1, DISH_MC_2, DISH_MC_3).collect(Collectors.toSet());
    public static final Set<Vote> BK_RATINGS = Stream.of(VOTE_USER_1, VOTE_ADMIN_1).collect(Collectors.toSet());
    public static final Set<Vote> MC_RATINGS = Stream.of(VOTE_USER_2, VOTE_USER_4, VOTE_USER_3).collect(Collectors.toSet());

    public static final Restaurant BK_REST = new Restaurant(RESTAURANT_ID, "Burger King", BK_RATINGS, BK_MENU);
    public static final Restaurant MC_REST = new Restaurant(RESTAURANT_ID + 1, "Mc Donalds", MC_RATINGS, MC_MENU);

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "dishes", "ratings");
    }

    public static void assertMatchWithDishes(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "ratings");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("dishes", "ratings").isEqualTo(expected);
    }

    public static Restaurant getUpdated() {
        Restaurant updated = new Restaurant(BK_REST);
        updated.setName("UpdatedName");
        return updated;
    }

    public static TestMatchers<RestaurantTo> RESTAURANT_TO_MATCHERS = TestMatchers.useFieldsComparator(RestaurantTo.class, "dishTos", "rating");

}
