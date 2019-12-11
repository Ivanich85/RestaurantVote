package restaurantvote.model;

import restaurantvote.model.values.Vote;

import java.util.Set;

public class Restaurant extends AbstractNamedEntity {

    private Set<Vote> ratings;

    private Set<Dish> menu;

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
