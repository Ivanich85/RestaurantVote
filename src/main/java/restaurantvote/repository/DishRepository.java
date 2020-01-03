package restaurantvote.repository;

import restaurantvote.model.Dish;

import java.util.List;

public interface DishRepository {
    Dish save(Dish dish);
    Dish delete(int id);
    Dish get(int id);
    List<Dish> getAllEnabledForRestaurant(int restaurantId);
}
