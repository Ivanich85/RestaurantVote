package restaurantvote.repository;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import restaurantvote.model.User;
import restaurantvote.model.Vote;

import java.util.List;
import java.util.Objects;

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
    public User delete(int id) {
        User currentUser = get(id);
        if (Objects.isNull(currentUser)) {
            return null;
        }
        currentUser.setEnabled(false);
        return save(currentUser);
    }

    @Override
    @Transactional(readOnly = true)
    public User get(int id) {
        return manager.find(User.class, id);
    }

    @Override
    public User getWithVotes(int id) {
        return (User) manager.createNamedQuery(User.WITH_VOTES)
                .setParameter("id", id)
                .getSingleResult();
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
        return manager.createNamedQuery(User.ALL_SORTED, User.class).getResultList();
    }

    @Override
    public Vote addVote(int restaurantId) {
        return null;
    }
}
