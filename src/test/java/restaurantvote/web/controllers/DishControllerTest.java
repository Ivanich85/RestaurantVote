package restaurantvote.web.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import restaurantvote.DishTestData;
import restaurantvote.TestUtil;
import restaurantvote.model.Dish;
import restaurantvote.service.DishService;
import restaurantvote.to.DishTo;
import restaurantvote.web.json.JsonUtil;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static restaurantvote.DishTestData.*;
import static restaurantvote.RestaurantTestData.MC_REST;

class DishControllerTest extends AbstractControllerTest {

    private final static String REST_URL = DishController.DISH_REST_URL + "/";

    @Autowired
    private DishService service;

    @Test
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + DISH_BK_ID_1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_TO_MATCHERS.contentJson(DishTo.createTo(DISH_BK_1)));
    }

    @Test
    void getAllEnabledForRestaurant() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + "/restaurant?id=100002"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_TO_MATCHERS.contentJson(DishTo.createTos(Arrays.asList(DISH_BK_1, DISH_BK_3))));
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_TO_MATCHERS.contentJson(DishTo.createTos(Arrays.asList(DISH_MC_1, DISH_BK_1, DISH_BK_2, DISH_BK_3, DISH_MC_2, DISH_MC_3))));
    }

    @Test
    void create() throws Exception {
        Dish expected = new Dish(null, "Muffin", 18000, MC_REST, true);
        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());
        Dish returned = TestUtil.readFromJson(action, Dish.class);
        expected.setId(returned.getId());
        assertMatch(returned, expected);
        assertMatch(service.getAll(), Arrays.asList(DISH_MC_1, DISH_BK_1, DISH_BK_2, DISH_BK_3, DISH_MC_2, DISH_MC_3, expected));
    }

    @Test
    void update() throws Exception {
        Dish updated = DishTestData.getUpdated();
        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL + DISH_BK_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
        assertMatch(service.get(DISH_BK_ID_1), updated);
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + DISH_BK_ID_1))
                .andDo(print())
                .andExpect(status().isNoContent());
        Dish deletedDish = new Dish(DISH_BK_1);
        deletedDish.setEnabled(false);
        assertMatch(service.get(DISH_BK_ID_1), deletedDish);
    }
}