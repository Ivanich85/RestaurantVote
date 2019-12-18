package restaurantvote.repository;

import restaurantvote.model.Vote;

import java.util.List;

public interface VoteRepository {
    Vote get(int userId, int id);
    List<Vote> getAll(int userId);
    Vote save(Vote vote);
    boolean delete(int userId, int voteId);
}
