package restaurantvote.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import restaurantvote.model.Vote;

import java.util.Objects;

@Repository
@Transactional
public class VoteRepositoryImpl extends AbstractRepository implements VoteRepository {

    /**
     * Create or delete states only
     */
    @Override
    public Vote save(Vote vote) {
        manager.persist(vote);
        return vote;
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
}
