package restaurantvote;

import restaurantvote.model.Restaurant;

import static restaurantvote.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final int RESTAURANT_ID = START_SEQ + 2;
    public static final Restaurant BK_REST = new Restaurant(RESTAURANT_ID, "Burger King");
    public static final Restaurant MC_REST = new Restaurant(RESTAURANT_ID + 1, "Mc Donalds");

}
