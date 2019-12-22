package restaurantvote;

import restaurantvote.model.Dish;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static restaurantvote.model.AbstractBaseEntity.START_SEQ;

public class DishTestData {
    private static final int dishSeq = START_SEQ + 9;
    public static final int DISH_BK_ID_1 = dishSeq;
    public static final int DISH_BK_ID_2 = dishSeq + 1;
    public static final int DISH_BK_ID_3 = dishSeq + 2;
    public static final int DISH_MC_ID_1 = dishSeq + 3;
    public static final int DISH_MC_ID_2 = dishSeq + 4;
    public static final int DISH_MC_ID_3 = dishSeq + 5;

    public static final Dish DISH_BK_1 = new Dish(DISH_BK_ID_1, "vopper", BigDecimal.valueOf(28050));
    public static final Dish DISH_BK_2 = new Dish(DISH_BK_ID_2, "nuggets", BigDecimal.valueOf(20000));
    public static final Dish DISH_BK_3 = new Dish(DISH_BK_ID_3, "big king", BigDecimal.valueOf(35000));
    public static final Dish DISH_MC_1 = new Dish(DISH_MC_ID_1, "mac chicken", BigDecimal.valueOf(25000));
    public static final Dish DISH_MC_2 = new Dish(DISH_MC_ID_2, "mac free", BigDecimal.valueOf(10000));
    public static final Dish DISH_MC_3 = new Dish(DISH_MC_ID_3, "happy meal", BigDecimal.valueOf(29000));

    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "creationDate", "restaurant");
    }

    public static Dish getUpdated() {
        Dish updated = new Dish(DISH_BK_1);
        updated.setName("Super vopper");
        updated.setPrice(BigDecimal.valueOf(28050));
        return updated;
    }

}
