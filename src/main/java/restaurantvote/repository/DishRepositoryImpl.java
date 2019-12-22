package restaurantvote.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import restaurantvote.model.Dish;

@Repository
@Transactional
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
    public boolean delete(int id) {
        return manager.createNamedQuery(Dish.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    @Transactional(readOnly = true)
    public Dish get(int id) {
        return manager.find(Dish.class, id);
    }
}
