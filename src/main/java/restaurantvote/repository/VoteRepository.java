package restaurantvote.repository;

import restaurantvote.model.Vote;

public interface VoteRepository {
    Vote get(int id, int userId);
    Vote save(Vote vote);
    boolean delete(int id, int userId);
}
