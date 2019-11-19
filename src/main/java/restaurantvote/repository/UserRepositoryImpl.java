package restaurantvote.repository;

import org.springframework.stereotype.Repository;
import restaurantvote.model.User;
import restaurantvote.model.Vote;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public Vote addVote(int restaurantId) {
        return null;
    }
}
