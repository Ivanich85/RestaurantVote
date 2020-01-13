package restaurantvote.web.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import restaurantvote.TestUtil;
import restaurantvote.model.Restaurant;
import restaurantvote.service.RestaurantService;
import restaurantvote.to.RestaurantTo;
import restaurantvote.web.json.JsonUtil;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static restaurantvote.DishTestData.assertMatchTo;
import static restaurantvote.RestaurantTestData.*;

class RestaurantControllerTest extends AbstractControllerTest {
    private final static String REST_URL = RestaurantController.RESTAURANT_REST_URL + "/";

    @Autowired
    private RestaurantService service;

    @Test
    void getAll() {
    }

    @Test
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_TO_MATCHERS.contentJson(RestaurantTo.createTo(BK_REST)));
    }

    @Test
    void getWithDishes() throws Exception {
        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + "/dishes?id=100002"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_TO_MATCHERS.contentJson(RestaurantTo.createTo(BK_REST)));
        RestaurantTo actual = TestUtil.readFromJson(action, RestaurantTo.class);
        RestaurantTo expected = RestaurantTo.createTo(BK_REST);
        assertMatchTo(actual.getDishTos(), expected.getDishTos());
    }

    @Test
    void create() throws Exception {
        Restaurant expected = new Restaurant(null, "KFC");
        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());
        Restaurant returned = TestUtil.readFromJson(action, Restaurant.class);
        expected.setId(returned.getId());
        assertMatch(returned, expected);
        assertMatch(service.getAll(), Arrays.asList(BK_REST, expected, MC_REST));
    }

    @Test
    void update() throws Exception {
        Restaurant updated = getUpdated();
        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL + RESTAURANT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
        assertMatch(service.get(RESTAURANT_ID), updated);
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + RESTAURANT_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertMatch(service.getAll(), Arrays.asList(MC_REST));
    }
}