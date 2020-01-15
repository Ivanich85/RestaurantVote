package restaurantvote.web.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import restaurantvote.TestUtil;
import restaurantvote.model.Vote;
import restaurantvote.service.VoteService;
import restaurantvote.to.VoteTo;
import restaurantvote.web.json.JsonUtil;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static restaurantvote.UserTestData.USER;
import static restaurantvote.VoteTestData.*;

class VoteControllerTest extends AbstractControllerTest {
    private final static String REST_URL = VoteController.VOTE_REST_URL + "/";

    @Autowired
    private VoteService service;

    @Test
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + VOTE_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_TO_MATCHERS.contentJson(VoteTo.createTo(VOTE_USER_1)));
    }

    @Test
    void getByUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + "/user"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_TO_MATCHERS.contentJson(VoteTo.createTos(Arrays.asList(VOTE_USER_4, VOTE_USER_3, VOTE_USER_2, VOTE_USER_1))));
    }

    @Test
    void update() throws Exception {
        Vote updated = getUpdated();
        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL + VOTE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
        assertMatch(service.get(VOTE_ID, USER.getId()), updated);
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + VOTE_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertMatch(service.getAllByUser(USER.getId()), Arrays.asList(VOTE_USER_4, VOTE_USER_3, VOTE_USER_2));
    }
}