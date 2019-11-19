import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class RestaurantVote {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println(Arrays.toString(ctx.getBeanDefinitionNames()));
        ((ClassPathXmlApplicationContext) ctx).close();
    }
}
