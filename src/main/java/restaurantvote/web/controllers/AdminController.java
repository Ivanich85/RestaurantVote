package restaurantvote.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import restaurantvote.model.User;
import restaurantvote.service.UserService;
import restaurantvote.to.UserTo;

import java.net.URI;
import java.util.List;

import static restaurantvote.to.UserTo.*;
import static restaurantvote.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = AdminController.ADMIN_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    public static final String ADMIN_REST_URL = "/rest/admin/users";

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserTo get(@PathVariable int id) {
        return createTo(userService.get(id));
    }

    @GetMapping("/by")
    public UserTo getByEmail(@RequestParam String email) {
        return createTo(userService.getByEmail(email));
    }

    @GetMapping
    public List<UserTo> getAll() {
        return createTos(userService.getAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> create(@RequestBody User user) {
        User created = userService.create(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody User user, @PathVariable int id) {
        assureIdConsistent(user, id);
        userService.update(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        userService.delete(id);
    }

}
