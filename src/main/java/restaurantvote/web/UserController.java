package restaurantvote.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import restaurantvote.model.Restaurant;
import restaurantvote.model.User;
import restaurantvote.service.RestaurantService;
import restaurantvote.service.UserService;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = UserController.USER_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    public static final String USER_REST_URL = "/user";

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @PutMapping(value = "/vote/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vote(@RequestBody User user, @PathVariable int restaurantId) {
        Restaurant restaurant = restaurantService.get(restaurantId);
        userService.vote(user, restaurant, LocalDateTime.now());
    }

}
