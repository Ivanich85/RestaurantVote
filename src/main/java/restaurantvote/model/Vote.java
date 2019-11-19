package restaurantvote.model;

import restaurantvote.model.values.VoteValue;

import java.time.LocalDateTime;

public class Vote extends AbstractBaseEntity {
    private LocalDateTime ratedDate;

    private VoteValue voteValue;

    private User votedUser;

    private Restaurant ratedRestaurant;

    public LocalDateTime getRatedDate() {
        return ratedDate;
    }

    public void setRatedDate(LocalDateTime ratedDate) {
        this.ratedDate = ratedDate;
    }

    public VoteValue getVoteValue() {
        return voteValue;
    }

    public void setVoteValue(VoteValue voteValue) {
        this.voteValue = voteValue;
    }

    public User getVotedUser() {
        return votedUser;
    }

    public void setVotedUser(User votedUser) {
        this.votedUser = votedUser;
    }

    public Restaurant getRatedRestaurant() {
        return ratedRestaurant;
    }

    public void setRatedRestaurant(Restaurant ratedRestaurant) {
        this.ratedRestaurant = ratedRestaurant;
    }
}
