package restaurantvote.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "user_votes")
@NamedQueries({
        @NamedQuery(name = Vote.GET_ALL, query="select v from Vote v where v.user.id = :userId order by v.voteDate desc"),
        @NamedQuery(name = Vote.DELETE, query="delete from Vote v where v.id = :id and v.user.id = :userId")
})
public class Vote extends AbstractBaseEntity {
    public static final String GET_ALL = "Vote.getAll";
    public static final String DELETE = "Vote.delete";

    @Column(name = "vote_date", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private Date voteDate;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    public Vote(){}

    public Vote(Vote v) {
        this.id = v.id;
        this.restaurant = v.restaurant;
        this.voteDate = v.voteDate;
    }

    public Vote(Integer id, Date voteDate) {
        this.id = id;
        this.voteDate = voteDate;
    }

    public Vote(Integer id) {
        this(id, new Date());
    }

    public Date getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(Date voteDate) {
        this.voteDate = voteDate;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
