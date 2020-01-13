package restaurantvote.web.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import restaurantvote.TestUtil;
import restaurantvote.UserTestData;
import restaurantvote.model.User;
import restaurantvote.service.UserService;
import restaurantvote.to.UserTo;
import restaurantvote.web.json.JsonUtil;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static restaurantvote.UserTestData.*;

public class AdminControllerTest extends AbstractControllerTest {
    private final static String REST_URL = AdminController.ADMIN_REST_URL + "/";

    @Autowired
    private UserService service;

    @Test
    public void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + ADMIN_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_TO_MATCHERS.contentJson(UserTo.createTo(ADMIN)));
    }

    @Test
    public void getByEmail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + "/by?email=admin@gmail.com"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_TO_MATCHERS.contentJson(UserTo.createTo(ADMIN)));
    }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_TO_MATCHERS.contentJson(UserTo.createTos(Arrays.asList(ADMIN, USER))));
    }

    @Test
    public void create() throws Exception {
        User expected = new User(null, "new", "newEmail@mail.ru", "pass1", userRoles, userVotes);
        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());
        User returned = TestUtil.readFromJson(action, User.class);
        expected.setId(returned.getId());
        assertMatch(returned, expected);
        assertMatch(service.getAll(), ADMIN, USER, expected);
    }

    @Test
    public void update() throws Exception {
        User updated = UserTestData.getUpdated();
        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL + USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
        assertMatch(service.get(USER_ID), updated);
    }

    @Test
    public void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + USER_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        User deletedUser = getDeleted();
        assertMatch(service.get(USER_ID), deletedUser);
    }

}