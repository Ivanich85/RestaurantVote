package restaurantvote.to;

import restaurantvote.model.Dish;
import restaurantvote.model.Vote;

import java.util.Arrays;
import java.util.Set;

public class RestaurantTo {

    private final Integer id;
    private final String name;
    private final Set<Vote> ratings;
    private final Set<Dish> dishes;

    public RestaurantTo(Integer id, String name, Set<Vote> ratings, Set<Dish> dishes) {
        this.id = id;
        this.name = name;
        this.ratings = ratings;
        this.dishes = dishes;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Vote> getRatings() {
        return ratings;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    @Override
    public String toString() {
        return "RestaurantTo {" +
                "id=" + id +
                ", name=" + name +
                ", rating='" + ratings +
                ", dishes='" + Arrays.deepToString(dishes.toArray()) + '\'' +
                '}';
    }
}
