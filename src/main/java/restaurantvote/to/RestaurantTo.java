package restaurantvote.to;

import restaurantvote.model.Dish;
import restaurantvote.model.Vote;

import java.util.Arrays;
import java.util.Set;

public class RestaurantTo {

    private final Integer id;

    private final String name;

    private final Set<Vote> ratings;

    private final Set<Dish> menu;

    private final double averageRating;

    public RestaurantTo(Integer id, String name, Set<Vote> ratings, Set<Dish> menu, double averageRating) {
        this.id = id;
        this.name = name;
        this.ratings = ratings;
        this.menu = menu;
        this.averageRating = averageRating;
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

    public Set<Dish> getMenu() {
        return menu;
    }

    public double getAverageRating() {
        return averageRating;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", name=" + name +
                ", menu='" + Arrays.deepToString(menu.toArray()) + '\'' +
                ", averageRating=" + averageRating +
                '}';
    }
}
