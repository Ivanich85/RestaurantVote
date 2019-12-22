package restaurantvote.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Vote> ratings;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Dish> dishes;

    public Restaurant(){
        super();
    }

    public Restaurant(int id, String name) {
        super(id, name);
    }

    public Set<Vote> getRatings() {
        if (Objects.isNull(ratings)) {
            ratings = new HashSet<>();
        }
        return ratings;
    }

    public void setRatings(Set<Vote> ratings) {
        this.ratings = ratings;
    }

    public Set<Dish> getDishes() {
        if (Objects.isNull(dishes)) {
            dishes = new HashSet<>();
        }
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }
}
