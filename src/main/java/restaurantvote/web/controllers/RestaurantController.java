package restaurantvote.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import restaurantvote.model.Restaurant;
import restaurantvote.service.RestaurantService;
import restaurantvote.to.RestaurantTo;

import java.net.URI;
import java.util.List;

import static restaurantvote.to.RestaurantTo.createTo;
import static restaurantvote.to.RestaurantTo.createTos;
import static restaurantvote.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = RestaurantController.RESTAURANT_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {
    public static final String RESTAURANT_REST_URL = "/rest/restaurants";

    @Autowired
    private RestaurantService service;

    @GetMapping()
    public List<RestaurantTo> getAll() {
        return createTos(service.getAll());
    }

    @GetMapping("/{id}")
    public RestaurantTo get(@PathVariable int id) {
        return createTo(service.get(id));
    }

    @GetMapping("/dishes")
    public RestaurantTo getWithDishes(@RequestParam int id) {
        return createTo(service.getWithDishes(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        Restaurant created = service.create(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(RESTAURANT_REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant restaurant, @PathVariable int id) {
        assureIdConsistent(restaurant, id);
        service.update(restaurant);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

}
