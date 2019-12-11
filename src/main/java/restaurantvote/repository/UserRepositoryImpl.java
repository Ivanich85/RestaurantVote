package restaurantvote.repository;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import restaurantvote.model.User;
import restaurantvote.model.values.Vote;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public User save(User user) {
        if (user.isNew()) {
            manager.persist(user);
            return user;
        } else {
            return manager.merge(user);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return manager.createNamedQuery(User.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public User get(int id) {
        return manager.find(User.class, id);
    }

    @Override
    public User getByEmail(String email) {
        List<User> users = manager.createNamedQuery(User.BY_EMAIL, User.class)
                .setParameter("email", email)
                .getResultList();
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public List<User> getAll() {
        List<User> users = manager.createNamedQuery(User.ALL_SORTED, User.class).getResultList();
        return manager.createNamedQuery(User.ALL_SORTED, User.class).getResultList();
    }

    @Override
    public Vote addVote(int restaurantId) {
        return null;
    }
}
