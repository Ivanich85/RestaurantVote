package restaurantvote.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import restaurantvote.model.Dish;
import restaurantvote.util.NotFoundException;

import java.math.BigDecimal;

import static restaurantvote.DishTestData.*;
import static restaurantvote.RestaurantTestData.MC_REST;

public class DishServiceTest extends AbstractServiceTest {

    @Autowired
    private DishService service;

    @Test
    public void create() {
        Dish newDish = new Dish(DISH_BK_ID_1, "vopper", BigDecimal.valueOf(28050), MC_REST);
        Dish created = service.create(newDish);
        Integer newId = created.getId();
        newDish.setId(newId);
        assertMatch(created, newDish);
        assertMatch(service.get(newId), newDish);
    }

    @Test
    public void update() {
        Dish updated = getUpdated();
        service.update(new Dish(updated));
        assertMatch(service.get(DISH_BK_ID_1), updated);
    }

    @Test(expected = NotFoundException.class)
    public void delete() throws Exception {
        service.delete(DISH_BK_ID_1);
        service.get(DISH_BK_ID_1);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNoFound() throws Exception {
        service.delete(1);
    }

    @Test
    public void get() {
        assertMatch(service.get(DISH_BK_ID_1), DISH_BK_1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.delete(1);
    }
}
