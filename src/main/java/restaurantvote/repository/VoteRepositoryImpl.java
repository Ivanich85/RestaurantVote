package restaurantvote.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import restaurantvote.model.Vote;

import java.util.List;

@Repository
@Transactional
public class VoteRepositoryImpl extends AbstractRepository implements VoteRepository {

    @Override
    @Transactional(readOnly = true)
    public Vote get(int userId, int id) {
        Vote vote = manager.find(Vote.class, id);
        return vote.getUser().getId() == userId ? vote : null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vote> getAll(int userId) {
        return manager.createNamedQuery(Vote.GET_ALL).setParameter("userId", userId).getResultList();
    }

    @Override
    public Vote save(Vote vote) {
        if (vote.isNew()) {
            manager.persist(vote);
            return vote;
        }
        return manager.merge(vote);
    }

    @Override
    public boolean delete(int id, int userId) {
        return manager.createNamedQuery(Vote.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }
}
