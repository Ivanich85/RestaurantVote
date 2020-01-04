package restaurantvote.repository;

import restaurantvote.model.Restaurant;
import restaurantvote.model.User;
import restaurantvote.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository {
    User save(User user);

    User delete(int id);

    User get(int id);

    User getWithVotes(int id);

    User getByEmail(String email);

    List<User> getAll();

    Vote vote(User user, Restaurant restaurant, LocalDateTime voteTime);
}
