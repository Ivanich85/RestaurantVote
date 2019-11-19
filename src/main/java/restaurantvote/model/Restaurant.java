package restaurantvote.model;

import java.util.List;

public class Restaurant extends AbstractNamedEntity {

    private List<Vote> ratings;

    private List<Dish> menu;

    public List<Vote> getRatings() {
        return ratings;
    }

    public void setRatings(List<Vote> ratings) {
        this.ratings = ratings;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }
}
