import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import restaurantvote.model.Dish;
import restaurantvote.service.DishService;

import java.util.Arrays;

public class RestaurantVote {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        System.out.println(Arrays.toString(ctx.getBeanDefinitionNames()));
        ((ClassPathXmlApplicationContext) ctx).refresh();

        DishService dishService = (DishService) ctx.getBean("dishService");
        dishService.delete(100009);
        Dish dish = dishService.get(100009);
        ((ClassPathXmlApplicationContext) ctx).close();
    }
}
