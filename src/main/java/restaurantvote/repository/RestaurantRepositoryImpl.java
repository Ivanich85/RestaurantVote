package restaurantvote.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import restaurantvote.model.Restaurant;

import java.util.List;

@Repository
public class RestaurantRepositoryImpl extends AbstractRepository implements RestaurantRepository {
    @Override
    public Restaurant save(Restaurant restaurant) {
        if (restaurant.isNew()) {
            manager.persist(restaurant);
            return restaurant;
        }
        return manager.merge(restaurant);
    }

    @Override
    public boolean delete(int id) {
        return manager.createNamedQuery(Restaurant.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    @Transactional(readOnly = true)
    public Restaurant get(int id) {
        return manager.find(Restaurant.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Restaurant getWithDishes(int id) {
        return manager.createNamedQuery(Restaurant.GET_WITH_DISHES, Restaurant.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Restaurant> getAll() {
        return manager.createNamedQuery(Restaurant.ALL_SORTED, Restaurant.class).getResultList();
    }
}
