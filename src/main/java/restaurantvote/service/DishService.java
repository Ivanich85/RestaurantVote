package restaurantvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restaurantvote.model.Dish;
import restaurantvote.repository.DishRepository;
import restaurantvote.util.NotFoundException;
import restaurantvote.util.ValidationUtil;

import java.util.List;

@Service
public class DishService {
    private DishRepository repository;

    @Autowired
    public DishService(DishRepository repository) {
        this.repository = repository;
    }

    public Dish create(Dish dish) {
        return repository.save(dish);
    }

    public void update(Dish dish) throws NotFoundException {
        ValidationUtil.checkNotFoundWithId(repository.save(dish), dish.getId());
    }

    public void delete(int id) throws NotFoundException {
        ValidationUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    public Dish get(int id) throws NotFoundException {
        return ValidationUtil.checkNotFoundWithId(repository.get(id), id);
    }

    public List<Dish> getAllEnabledForRestaurant(int restaurantId) {
        return repository.getAllEnabledForRestaurant(restaurantId);
    }
}
