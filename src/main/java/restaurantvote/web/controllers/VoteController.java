package restaurantvote.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import restaurantvote.model.User;
import restaurantvote.model.Vote;
import restaurantvote.service.VoteService;
import restaurantvote.to.VoteTo;
import restaurantvote.web.SecurityUtil;

import java.net.URI;
import java.util.List;

import static restaurantvote.to.VoteTo.createTo;
import static restaurantvote.to.VoteTo.createTos;
import static restaurantvote.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = VoteController.VOTE_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    public static final String VOTE_REST_URL = "/votes";
    private User authUser = SecurityUtil.getAuthUser();

    @Autowired
    private VoteService service;

    @GetMapping("/{id}")
    public VoteTo get(@PathVariable int id) {
        return createTo(service.get(id, authUser.getId()));
    }

    @GetMapping("/user")
    public List<VoteTo> getByUser() {
        return createTos(service.getAllByUser(authUser.getId()));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Vote> create(@RequestBody Vote vote) {
        Vote created = service.create(vote);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(VOTE_REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Vote vote, @PathVariable int id) {
        assureIdConsistent(vote, id);
        service.update(vote);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id, authUser.getId());
    }

}
