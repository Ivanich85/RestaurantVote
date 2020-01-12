package restaurantvote.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import restaurantvote.model.Dish;

import java.util.List;
import java.util.Objects;

@Repository
public class DishRepositoryImpl extends AbstractRepository implements DishRepository {

    @Override
    public Dish save(Dish dish) {
        if (dish.isNew()) {
            manager.persist(dish);
            return dish;
        } else {
            return manager.merge(dish);
        }
    }

    @Override
    public Dish delete(int id) {
        Dish currentDish = get(id);
        if (Objects.isNull(currentDish)) {
            return null;
        }
        currentDish.setEnabled(false);
        return save(currentDish);
    }

    @Override
    @Transactional(readOnly = true)
    public Dish get(int id) {
        return manager.find(Dish.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Dish> getAll() {
        return manager.createNamedQuery(Dish.GET_ALL)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Dish> getAllEnabledForRestaurant(int restaurantId) {
        return manager.createNamedQuery(Dish.GET_ALL_ENABLED_FOR_RESTAURANT)
                .setParameter("restaurantId", restaurantId)
                .getResultList();
    }
}
