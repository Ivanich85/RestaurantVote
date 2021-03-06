package restaurantvote;

import restaurantvote.model.Dish;
import restaurantvote.to.DishTo;
import java.time.Month;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static restaurantvote.RestaurantTestData.BK_REST;
import static restaurantvote.RestaurantTestData.MC_REST;
import static restaurantvote.model.AbstractBaseEntity.START_SEQ;

public class DishTestData {
    private static final int dishSeq = START_SEQ + 9;
    public static final int DISH_BK_ID_1 = dishSeq;
    public static final int DISH_BK_ID_2 = dishSeq + 1;
    public static final int DISH_BK_ID_3 = dishSeq + 2;
    public static final int DISH_MC_ID_1 = dishSeq + 3;
    public static final int DISH_MC_ID_2 = dishSeq + 4;
    public static final int DISH_MC_ID_3 = dishSeq + 5;

    public static final Dish DISH_BK_1 = new Dish(DISH_BK_ID_1, of(2019, Month.DECEMBER, 22, 15, 20),"vopper", 28050, BK_REST, true);
    public static final Dish DISH_BK_2 = new Dish(DISH_BK_ID_2, of(2019, Month.DECEMBER, 22, 16, 20),"nuggets", 20000, BK_REST, false);
    public static final Dish DISH_BK_3 = new Dish(DISH_BK_ID_3, of(2019, Month.DECEMBER, 23, 11, 20),"big king", 35000, BK_REST, true);
    public static final Dish DISH_MC_1 = new Dish(DISH_MC_ID_1, of(2019, Month.DECEMBER, 22, 12, 20),"mac chicken", 25000, MC_REST, true);
    public static final Dish DISH_MC_2 = new Dish(DISH_MC_ID_2, of(2019, Month.DECEMBER, 23, 15, 20),"mac free", 10000, MC_REST, true);
    public static final Dish DISH_MC_3 = new Dish(DISH_MC_ID_3, of(2019, Month.DECEMBER, 23, 16, 20),"happy meal", 29000, MC_REST, true);

    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant");
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
    }

    public static void assertMatchTo(Iterable<DishTo> actual, Iterable<DishTo> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurantName").isEqualTo(expected);
    }

    public static Dish getUpdated() {
        Dish updated = new Dish(DISH_BK_1);
        updated.setName("Super vopper");
        updated.setPrice(30050);
        return updated;
    }

    public static TestMatchers<DishTo> DISH_TO_MATCHERS = TestMatchers.useFieldsComparator(DishTo.class, "creationDate", "restaurantName");

}
