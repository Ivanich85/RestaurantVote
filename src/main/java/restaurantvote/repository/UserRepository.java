package restaurantvote.repository;

import restaurantvote.model.User;
import restaurantvote.model.values.Vote;

import java.util.List;

public interface UserRepository {
    User save(User user);

    boolean delete(int id);

    User get(int id);

    User getByEmail(String email);

    List<User> getAll();

    Vote addVote(int restaurantId);
}
