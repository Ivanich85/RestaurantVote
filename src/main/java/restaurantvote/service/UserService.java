package restaurantvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restaurantvote.model.Restaurant;
import restaurantvote.model.Vote;
import restaurantvote.repository.UserRepository;
import restaurantvote.model.User;
import restaurantvote.util.NotFoundException;
import restaurantvote.util.SecondVoteDeniedException;
import restaurantvote.util.ValidationUtil;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        return repository.save(user);
    }

    public void update(User user) throws NotFoundException {
        ValidationUtil.checkNotFoundWithId(repository.save(user), user.getId());
    }

    public void delete(int id) throws NotFoundException {
        ValidationUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    public User get(int id) throws NotFoundException {
        return ValidationUtil.checkNotFoundWithId(repository.get(id), id);
    }

    public User getWithVotes(int id) throws NotFoundException {
        return ValidationUtil.checkNotFoundWithId(repository.getWithVotes(id), id);
    }

    public User getByEmail(String email) throws NotFoundException {
        return ValidationUtil.checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    public List<User> getAll() {
        return repository.getAll();
    }

    public Vote vote(User user, Restaurant restaurant, LocalDateTime voteDate) throws SecondVoteDeniedException {
        return repository.vote(user, restaurant, voteDate);
    }

}
