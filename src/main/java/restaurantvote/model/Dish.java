package restaurantvote.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "dishes")
@NamedQueries({
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d where d.id = :id")
})
public class Dish extends AbstractNamedEntity {
    public final static String DELETE = "Dish.delete";

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

    public Dish(){}

    public Dish(Integer id, String name, BigDecimal price) {
        super(id, name);
        this.creationDate = LocalDateTime.now();
        this.name = name;
        this.price = price;
    }

    public Dish(Dish dish) {
        super(dish.id, dish.name);
        this.creationDate = dish.creationDate;
        this.name = dish.name;
        this.price = dish.price;
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
}
