package restaurantvote.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "restaurants")
@NamedQueries({
    @NamedQuery(name = Restaurant.DELETE, query = "delete from Restaurant r where r.id = :id"),
    @NamedQuery(name = Restaurant.GET_WITH_DISHES,
            query = "select r from Restaurant r left join fetch r.dishes where r.id = :id"),
    @NamedQuery(name = Restaurant.ALL_SORTED, query = "select r from Restaurant r order by r.name")
})
public class Restaurant extends AbstractNamedEntity {
    public static final String DELETE = "Restaurant.delete";
    public static final String GET_WITH_DISHES = "Restaurant.getWithDishes";
    public static final String ALL_SORTED = "Restaurant.allSorted";

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Vote> ratings;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Dish> dishes;

    public Restaurant(){
        super();
    }

    public Restaurant(int id, String name) {
        super(id, name);
        this.ratings = null;
        this.dishes = null;
    }

    public Restaurant(int id, String name, Set<Vote> ratings, Set<Dish> dishes) {
        super(id, name);
        this.ratings = ratings;
        this.dishes = dishes;
    }

    public Restaurant(Restaurant restaurant) {
        this(restaurant.getId(), restaurant.getName(), restaurant.getRatings(), restaurant.getDishes());
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
