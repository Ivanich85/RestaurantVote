package restaurantvote.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Vote> ratings;

    @Transient
    private Set<Dish> menu;

    public Restaurant(){
        super();
    }

    public Restaurant(int id, String name) {
        super(id, name);
    }

    public Set<Vote> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Vote> ratings) {
        this.ratings = ratings;
    }

    public Set<Dish> getMenu() {
        return menu;
    }

    public void setMenu(Set<Dish> menu) {
        this.menu = menu;
    }
}
