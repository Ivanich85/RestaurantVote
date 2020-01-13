package restaurantvote.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import restaurantvote.model.Restaurant;
import restaurantvote.model.User;
import restaurantvote.model.Vote;
import restaurantvote.util.SecondVoteDeniedException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static restaurantvote.util.DateTimeUtil.*;

@Repository
public class UserRepositoryImpl extends AbstractRepository implements UserRepository {

    private final VoteRepository voteRepository;

    @Autowired
    public UserRepositoryImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

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
    public Vote vote(User user, Restaurant restaurant, LocalDateTime voteTime) {
        List<Vote> userVotes = voteRepository.getAllByUser(user.getId());
        Vote previousVote = userVotes.stream()
                        .filter(v -> v.getCreationDate().toLocalDate().equals(voteTime.toLocalDate()))
                        .findFirst()
                        .orElse(null);
        if (Objects.nonNull(previousVote) && voteTime.toLocalTime().isAfter(SECOND_VOTE_TIME_LIMIT)) {
            String exceptionMessage = String.format("Повторное голосование разрешено до %s", SECOND_VOTE_TIME_LIMIT.format(TIME_FORMATTER));
            throw new SecondVoteDeniedException(exceptionMessage);
        }
        Vote newVote = Objects.isNull(previousVote) ? new Vote(null, voteTime, restaurant, user) : new Vote(previousVote);
        newVote.setRestaurant(restaurant);
        newVote.setCreationDate(voteTime);
        newVote = voteRepository.save(newVote);
        return newVote;
    }

}
