package restaurantvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restaurantvote.model.Restaurant;
import restaurantvote.repository.RestaurantRepository;
import restaurantvote.util.NotFoundException;
import restaurantvote.util.ValidationUtil;

import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository repository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.repository = restaurantRepository;
    }

    public Restaurant create(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public Restaurant update(Restaurant restaurant) throws NotFoundException {
        return ValidationUtil.checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    public void delete(int id) throws NotFoundException {
        ValidationUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    public Restaurant get(int id) throws NotFoundException {
        return ValidationUtil.checkNotFoundWithId(repository.get(id), id);
    }

    public Restaurant getWithDishes(int id) {
        return ValidationUtil.checkNotFoundWithId(repository.getWithDishes(id), id);
    }

    public List<Restaurant> getAll() {
        return repository.getAll();
    }
}
