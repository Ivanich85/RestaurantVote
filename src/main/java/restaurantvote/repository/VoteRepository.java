package restaurantvote.repository;

import restaurantvote.model.Vote;

import java.util.List;

public interface VoteRepository {
    Vote get(int id, int userId);
    List<Vote> getByUser(int userId);
    Vote save(Vote vote);
    boolean delete(int id, int userId);
}
