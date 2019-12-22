package restaurantvote.repository;

import restaurantvote.model.User;
import restaurantvote.model.Vote;

import java.util.List;

public interface UserRepository {
    User save(User user);

    User delete(int id);

    User get(int id);

    User getWithVotes(int id);

    User getByEmail(String email);

    List<User> getAll();

    Vote addVote(int restaurantId);
}
