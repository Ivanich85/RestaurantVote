package restaurantvote.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import restaurantvote.model.Restaurant;
import restaurantvote.model.User;
import restaurantvote.model.Vote;
import restaurantvote.service.RestaurantService;
import restaurantvote.service.UserService;
import restaurantvote.to.VoteTo;
import restaurantvote.web.SecurityUtil;

import java.net.URI;
import java.time.LocalDateTime;

import static restaurantvote.web.controllers.VoteController.VOTE_REST_URL;

@RestController
@RequestMapping(value = UserController.USER_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private User authUser = SecurityUtil.getAuthUser();

    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;

    public static final String USER_REST_URL = "/rest/user";

    @PutMapping(value = "/vote", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VoteTo> vote(@RequestParam int restaurantId) {
        Restaurant restaurant = restaurantService.get(restaurantId);
        Vote created = userService.vote(authUser, restaurant, LocalDateTime.now());
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(VOTE_REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(VoteTo.createTo(created));
    }

}
