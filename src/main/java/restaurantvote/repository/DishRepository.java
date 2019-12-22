package restaurantvote.repository;

import restaurantvote.model.Dish;

public interface DishRepository {
    Dish save(Dish dish);
    boolean delete(int id);
    Dish get(int id);
}
