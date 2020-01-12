package restaurantvote.to;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.LazyInitializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import restaurantvote.model.User;
import restaurantvote.model.Vote;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class VoteTo {
    private Integer id;
    private LocalDateTime creationDate;
    private String restaurantName;
    private String userName;
    private Integer userId;

    private static final Logger LOG = LoggerFactory.getLogger(VoteTo.class);

    public static VoteTo createTo(Vote vote) {
        VoteTo to = new VoteTo();
        to.id = vote.getId();
        to.creationDate = vote.getCreationDate();
        to.restaurantName = vote.getRestaurant().getName();
        User user = vote.getUser();
        to.userName = Objects.nonNull(user) ? user.getName() : null;
        to.userId = Objects.nonNull(user) ? user.getId() : null;
        return to;
    }

    public static List<VoteTo> createTos(Collection<Vote> votes) {
        List<VoteTo> result = new ArrayList<>();
        try {
            result = CollectionUtils.isEmpty(votes) ?
                    new ArrayList<>() : votes.stream().map(v -> createTo(v)).collect(Collectors.toList());
        } catch (LazyInitializationException e) {
            // Так себе решение, но пока не разобрался, как перехватить ленивую инициализацию
        } catch (Exception e) {
            LOG.info(e.getMessage());
        }
        return result;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("VoteTo:{");
        builder
                .append("id=" + id)
                .append(", creationDate=" + creationDate)
                .append(", restaurantName=" + restaurantName)
                .append(", userName=" + userName)
                .append(", userId=" + userId)
                .append("}");
        return builder.toString();
    }
}
