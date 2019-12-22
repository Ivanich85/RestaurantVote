import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import restaurantvote.model.User;
import restaurantvote.repository.UserRepository;
import restaurantvote.repository.UserRepositoryImpl;

import java.util.Arrays;

public class RestaurantVote {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        System.out.println(Arrays.toString(ctx.getBeanDefinitionNames()));
        ((ClassPathXmlApplicationContext) ctx).refresh();

        UserRepository userRepository = (UserRepositoryImpl) ctx.getBean("userRepositoryImpl");
        User user = userRepository.getWithVotes(100000);
        ((ClassPathXmlApplicationContext) ctx).close();
    }
}
