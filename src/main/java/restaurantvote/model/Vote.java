package restaurantvote.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_votes")
@NamedQueries({
        @NamedQuery(name = Vote.DELETE, query="delete from Vote v where v.id = :id and v.user.id = :userId"),
})
public class Vote extends AbstractBaseEntity {
    public static final String DELETE = "Vote.delete";

    @Column(name = "vote_date", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDateTime creationDate;

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
        this.creationDate = v.creationDate;
    }

    public Vote(Integer id, LocalDateTime creationDate, Restaurant restaurant) {
        this.id = id;
        this.restaurant = restaurant;
        this.creationDate = creationDate;
    }

    public Vote(Integer id, Restaurant restaurant) {
        this(id, LocalDateTime.now(), restaurant);
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
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
