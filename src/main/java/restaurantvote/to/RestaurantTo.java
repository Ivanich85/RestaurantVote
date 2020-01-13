package restaurantvote.to;

import org.apache.commons.collections.CollectionUtils;
import restaurantvote.model.Restaurant;
import restaurantvote.model.Vote;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RestaurantTo {
    private Integer id;
    private String name;
    private Integer rating;
    private List<DishTo> dishTos;

    public static RestaurantTo createTo(Restaurant restaurant) {
        RestaurantTo to = new RestaurantTo();
        to.id = restaurant.getId();
        to.name = restaurant.getName();
        Set<Vote> ratings = restaurant.getRatings();
        to.rating = CollectionUtils.isNotEmpty(ratings) ? ratings.size() : 0;
        to.dishTos = DishTo.createTos(restaurant.getDishes());
        return to;
    }

    public static List<RestaurantTo> createTos(Collection<Restaurant> restaurants) {
        return CollectionUtils.isEmpty(restaurants) ?
                new ArrayList<>() : restaurants.stream().map(r -> createTo(r)).collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getRating() {
        return rating;
    }

    public List<DishTo> getDishTos() {
        return dishTos;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("RestaurantTo: {");
        builder
                .append("id=" + id)
                .append(", name=" + name)
                .append(", rating=" + rating)
                .append(", dishTos=[" + dishTos)
                .append("]")
                .append("}");
        return builder.toString();
    }

}
