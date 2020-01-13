package restaurantvote.web.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import restaurantvote.TestUtil;
import restaurantvote.VoteTestData;
import restaurantvote.model.Vote;
import restaurantvote.service.VoteService;
import restaurantvote.to.VoteTo;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static restaurantvote.RestaurantTestData.*;
import static restaurantvote.UserTestData.USER;
import static restaurantvote.VoteTestData.*;
import static restaurantvote.VoteTestData.VOTE_USER_1;
import static restaurantvote.to.VoteTo.createTo;
import static restaurantvote.to.VoteTo.createTos;

class UserControllerTest extends AbstractControllerTest {

    @Autowired
    private VoteService voteService;

    private final static String REST_URL = UserController.USER_REST_URL + "/";

    @Test
    void vote() throws Exception {
        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.put(REST_URL + "/vote?restaurantId=" + RESTAURANT_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        VoteTo returned = TestUtil.readFromJson(action, VoteTo.class);
        VoteTo expected = createTo(new Vote(returned.getId(), LocalDateTime.now(), BK_REST, USER));
        List<VoteTo> expectedVoteTos = createTos(Arrays.asList(VOTE_USER_4, VOTE_USER_3, VOTE_USER_2, VOTE_USER_1));
        expectedVoteTos.add(0, expected);
        VoteTestData.assertMatchTo(returned, expected);
        assertMatchTosById(createTos(voteService.getAllByUser(USER.getId())), expectedVoteTos);
    }
}