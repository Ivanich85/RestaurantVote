package restaurantvote.util;

import org.apache.commons.collections.CollectionUtils;
import restaurantvote.model.Restaurant;
import restaurantvote.model.values.Vote;
import restaurantvote.to.RestaurantTo;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RestaurantUtil {
    public static List<RestaurantTo> getAllTos(Collection<Restaurant> restaurants) {
        return createTos(restaurants, restaurant -> true);
    }

    public static List<RestaurantTo> getFilteredTos(Collection<Restaurant> restaurants, double ratingFrom, double ratingTo) {
        return createTos(restaurants,
                restaurant -> getAverageRating(restaurant.getRatings()) >= ratingFrom
                        && getAverageRating(restaurant.getRatings()) <= ratingTo);
    }

    private static List<RestaurantTo> createTos(Collection<Restaurant> restaurants, Predicate<Restaurant> filter) {
        return restaurants
                .stream()
                .filter(filter)
                .map(restaurant -> createTo(restaurant, getAverageRating(restaurant.getRatings())))
                .collect(Collectors.toList());
    }

    private static RestaurantTo createTo(Restaurant restaurant, double averageRating) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName(), restaurant.getRatings(), restaurant.getMenu(), averageRating);
    }

    /**
     * Method used into another stream, so using stream here lacks performance
     */
    private static Double getAverageRating(Set<Vote> votes) {
        double sum = 0d;
        if (CollectionUtils.isEmpty(votes)) {
            return sum;
        }
        for (Vote vote : votes) {
            sum += vote.getValue();
        }
        return sum / votes.size();
    }

}
