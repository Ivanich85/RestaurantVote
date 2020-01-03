package restaurantvote.util;

import restaurantvote.model.Restaurant;
import restaurantvote.to.RestaurantTo;

public class RestaurantUtil {

    // TODO - добавить только актуальные оценки и блюда
    public static RestaurantTo createTo(Restaurant restaurant) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName(), restaurant.getRatings(), restaurant.getDishes());
    }

}
