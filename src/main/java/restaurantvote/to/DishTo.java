package restaurantvote.to;

import org.apache.commons.collections.CollectionUtils;
import restaurantvote.model.Dish;
import restaurantvote.model.Restaurant;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DishTo {
    private Integer id;
    private String name;
    private LocalDateTime creationDate;
    /**
     * Стоимость в рублях
     */
    private Double price;
    private String restaurantName;
    private Boolean enabled;

    public static DishTo createTo(Dish dish) {
        DishTo to = new DishTo();
        to.id = dish.getId();
        to.name = dish.getName();
        to.creationDate = dish.getCreationDate();
        to.price = dish.getPrice() / 100.0;
        Restaurant restaurant = dish.getRestaurant();
        to.restaurantName = Objects.nonNull(restaurant) ? restaurant.getName() : null;
        to.enabled = dish.getEnabled();
        return to;
    }

    public static List<DishTo> createTos(Collection<Dish> dishes) {
        return CollectionUtils.isEmpty(dishes) ?
                new ArrayList<>() : dishes.stream().map(d -> createTo(d)).collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Double getPrice() {
        return price;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("DishTo:{");
        builder
                .append("id=" + id)
                .append(", name=" + name)
                .append(", creationDate=" + creationDate)
                .append(", price=" + price)
                .append(", restaurantName=" + restaurantName)
                .append(", enabled=" + enabled)
                .append("}");
        return builder.toString();
    }
}
