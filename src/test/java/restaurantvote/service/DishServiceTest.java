package restaurantvote.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import restaurantvote.AbstractServiceTest;
import restaurantvote.RestaurantTestData;
import restaurantvote.model.Dish;
import restaurantvote.util.NotFoundException;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static restaurantvote.DishTestData.*;
import static restaurantvote.RestaurantTestData.MC_REST;

public class DishServiceTest extends AbstractServiceTest {

    @Autowired
    private DishService service;

    @Test
    public void create() {
        Dish newDish = new Dish(DISH_BK_ID_1, "vopper", 28050, MC_REST, true);
        Dish created = service.create(newDish);
        Integer newId = created.getId();
        newDish.setId(newId);
        assertMatch(created, newDish);
        assertMatch(service.get(newId), newDish);
    }

    @Test
    public void update() {
        Dish updated = getUpdated();
        updated.setRestaurant(RestaurantTestData.BK_REST);
        service.update(new Dish(updated));
        Dish updatedFromDB = service.get(DISH_BK_ID_1);
        assertMatch(updatedFromDB, updated);
    }

    @Test
    public void delete() throws Exception {
        Dish disabled = new Dish(DISH_BK_2);
        disabled.setEnabled(false);
        service.delete(DISH_BK_ID_2);
        assertMatch(service.get(DISH_BK_ID_2), disabled);
    }

    @Test
    public void deleteNoFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(1));
    }

    @Test
    public void get() {
        assertMatch(service.get(DISH_BK_ID_1), DISH_BK_1);
    }

    @Test
    public void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->  service.get(1));
    }

    @Test
    public void getAll() {
        List<Dish> expected = Arrays.asList(DISH_MC_1, DISH_BK_1, DISH_BK_2, DISH_BK_3, DISH_MC_2, DISH_MC_3);
        List<Dish> actual = service.getAll();
        assertMatch(expected, actual);
    }

    @Test
    public void getAllEnabledForRestaurant() {
        List<Dish> expected = Arrays.asList(DISH_BK_1, DISH_BK_3);
        List<Dish> actual = service.getAllEnabledForRestaurant(RestaurantTestData.RESTAURANT_ID);
        assertMatch(expected, actual);
    }
}
