package restaurantvote.repository;

import restaurantvote.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);
    boolean delete(int id);
    Restaurant get(int id);
    Restaurant getWithDishes(int id);
    List<Restaurant> getAll();
}
