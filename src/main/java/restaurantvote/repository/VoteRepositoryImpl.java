package restaurantvote.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import restaurantvote.model.Vote;

import java.util.List;
import java.util.Objects;

@Repository
public class VoteRepositoryImpl extends AbstractRepository implements VoteRepository {

    /**
     * Create or delete states only
     */
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

    @Override
    @Transactional(readOnly = true)
    public Vote get(int id, int userId) {
        Vote vote = manager.find(Vote.class, id);
        return Objects.nonNull(vote) && vote.getUser().getId() == userId ? vote : null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vote> getByUser(int userId) {
        return manager.createNamedQuery(Vote.GET_BY_USER)
                .setParameter("userId", userId)
                .getResultList();
    }
}
