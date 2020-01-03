package restaurantvote.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "dishes")
@NamedNativeQueries({
        @NamedNativeQuery(name = Dish.DELETE, query = "UPDATE dishes set enabled = 0 where id = :id")
})
@NamedQueries({
        @NamedQuery(name = Dish.GET_ALL_ENABLED_FOR_RESTAURANT,
                query = "select d from Dish d where d.restaurant.id = :restaurantId and d.enabled = true")
})
public class Dish extends AbstractNamedEntity {
    public final static String DELETE = "Dish.delete";
    public final static String GET_ALL_ENABLED_FOR_RESTAURANT = "Dish.getAllEnabledForRestaurant";

    @Column(name = "creation_date", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDateTime creationDate;

    @Column(name = "price", nullable = false)
    @NotNull
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    @Column(name = "enabled", nullable = false)
    @NotNull
    private Boolean enabled;

    public Dish(){}

    public Dish(Integer id, String name, BigDecimal price, Restaurant restaurant, Boolean enabled) {
        this(id, LocalDateTime.now(), name, price, restaurant, enabled);
    }

    public Dish(Integer id, LocalDateTime creationDate, String name, BigDecimal price, Restaurant restaurant, Boolean enabled) {
        super(id, name);
        this.creationDate = creationDate;
        this.price = price;
        this.restaurant = restaurant;
        this.enabled = enabled;
    }

    public Dish(Dish dish) {
        super(dish.id, dish.name);
        this.creationDate = dish.creationDate;
        this.price = dish.price;
        this.restaurant = dish.restaurant;
        this.enabled = dish.enabled;
    }

    public BigDecimal getPrice() {
        return BigDecimal.valueOf(price.ROUND_HALF_EVEN);
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
