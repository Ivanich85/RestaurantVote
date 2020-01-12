package restaurantvote.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import restaurantvote.model.Dish;
import restaurantvote.service.DishService;
import restaurantvote.to.DishTo;

import java.net.URI;
import java.util.List;

import static restaurantvote.to.DishTo.createTo;
import static restaurantvote.to.DishTo.createTos;
import static restaurantvote.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = DishController.DISH_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishController {
    public static final String DISH_REST_URL = "/dishes";

    @Autowired
    private DishService service;

    @GetMapping("/{id}")
    public DishTo get(@PathVariable int id) {
        return createTo(service.get(id));
    }

    @GetMapping("/restaurant")
    public List<DishTo> getAllEnabledForRestaurant(@RequestParam int id) {
        return createTos(service.getAllEnabledForRestaurant(id));
    }

    @GetMapping()
    public List<DishTo> getAll() {
        return createTos(service.getAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Dish> create(@RequestBody Dish dish) {
        Dish created = service.create(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(DISH_REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish, @PathVariable int id) {
        assureIdConsistent(dish, id);
        service.update(dish);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
