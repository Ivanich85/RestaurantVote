package restaurantvote.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import restaurantvote.RestaurantTestData;
import restaurantvote.model.Restaurant;
import restaurantvote.util.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static restaurantvote.RestaurantTestData.*;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    public void create() {
        Restaurant newRestaurant = new Restaurant(RestaurantTestData.MC_REST);
        Restaurant created = service.create(newRestaurant);
        Integer newId = created.getId();
        newRestaurant.setId(newId);
        assertMatch(created, newRestaurant);
        assertMatch(service.get(newId), newRestaurant);
    }

    @Test
    public void update() {
        Restaurant updated = getUpdated();
        service.update(new Restaurant(updated));
        assertMatch(service.get(RESTAURANT_ID), updated);
    }

    @Test(expected = NotFoundException.class)
    public void delete() throws Exception {
        service.delete(RESTAURANT_ID);
        service.get(RESTAURANT_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        service.delete(1);
    }

    @Test
    public void get() {
        Restaurant expected = new Restaurant(BK_REST);
        assertMatch(service.get(RESTAURANT_ID), expected);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(5);
    }

    @Test
    public void getWithDishes() {
        Restaurant expected = new Restaurant(BK_REST);
        assertMatchAllFields(service.getWithDishes(RESTAURANT_ID), expected);
    }

    @Test
    public void getAll() {
        List<Restaurant> expected = Arrays.asList(BK_REST, MC_REST);
        assertMatch(service.getAll() ,expected);
    }
}