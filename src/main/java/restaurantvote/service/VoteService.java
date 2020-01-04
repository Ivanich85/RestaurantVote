package restaurantvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restaurantvote.model.Vote;
import restaurantvote.repository.VoteRepository;
import restaurantvote.util.NotFoundException;
import restaurantvote.util.ValidationUtil;

@Service
public class VoteService {
    private final VoteRepository voteRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Vote create(Vote vote) {
        return voteRepository.save(vote);
    }

    public void delete(int id, int userId) throws NotFoundException {
        ValidationUtil.checkNotFoundWithId(voteRepository.delete(id, userId), id);
    }

    public void update(Vote vote) throws NotFoundException {
        ValidationUtil.checkNotFoundWithId(voteRepository.save(vote), vote.getId());
    }

    public Vote get(int id, int userId) throws NotFoundException {
        return ValidationUtil.checkNotFoundWithId(voteRepository.get(id, userId), id);
    }
}
