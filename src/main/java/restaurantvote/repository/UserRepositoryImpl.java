package restaurantvote.repository;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import restaurantvote.model.User;
import restaurantvote.model.Vote;

import java.util.List;

@Repository
@Transactional
public class UserRepositoryImpl extends AbstractRepository implements UserRepository {

    @Override
    public User save(User user) {
        if (user.isNew()) {
            manager.persist(user);
            return user;
        }
        return manager.merge(user);
    }

    @Override
    public boolean delete(int id) {
        return manager.createNamedQuery(User.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    @Transactional(readOnly = true)
    public User get(int id) {
        return manager.find(User.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getByEmail(String email) {
        List<User> users = manager.createNamedQuery(User.BY_EMAIL, User.class)
                .setParameter("email", email)
                .getResultList();
        return DataAccessUtils.singleResult(users);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        List<User> users = manager.createNamedQuery(User.ALL_SORTED, User.class).getResultList();
        return manager.createNamedQuery(User.ALL_SORTED, User.class).getResultList();
    }

    @Override
    public Vote addVote(int restaurantId) {
        return null;
    }
}
