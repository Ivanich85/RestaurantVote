package restaurantvote.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import restaurantvote.model.User;
import restaurantvote.model.values.Role;
import restaurantvote.util.NotFoundException;

import javax.persistence.PersistenceException;
import java.time.LocalDateTime;
import java.util.*;

import static restaurantvote.UserTestData.*;

@ContextConfiguration(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db-test.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = {"classpath:db/initDB_hsql.sql", "classpath:db/populateDB.sql"}, config = @SqlConfig(encoding = "UTF-8"))
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void create() {
        User newUser = new User(null, "New", "new@gmail.com", "newPass", false,
                LocalDateTime.now(), Collections.singleton(Role.ROLE_USER), userVotes);
        User created = service.create(newUser);
        Integer newId = created.getId();
        newUser.setId(newId);
        assertMatch(created, newUser);
        assertMatch(service.get(newId), newUser);
    }

    @Test(expected = PersistenceException.class)
    public void duplicateMailCreate() throws Exception {
        service.create(new User(null, "Duplicate", "admin@gmail.com", "newPass", false,
                LocalDateTime.now(), Collections.singleton(Role.ROLE_USER), userVotes));
        service.getAll();
    }

    @Test
    public void update() {
        User updated = getUpdated();
        service.update(new User(updated));
        assertMatch(service.get(USER_ID), updated);
    }

    @Test
    public void delete() {
        User deletedUser = getDeleted();
        service.delete(USER_ID);
        assertMatch(service.get(USER_ID), deletedUser);
    }

    @Test(expected = NotFoundException.class)
    public void deletedNotFound() throws Exception {
        service.delete(1);
    }

    @Test
    public void get() {
        User user = service.get(USER_ID);
        assertMatch(user, USER);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void getByEmail() {
        User user = service.getByEmail("user@yandex.ru");
        assertMatch(user, USER);
    }

    @Test(expected = NotFoundException.class)
    public void getByNotExistEmail() throws Exception {
        service.getByEmail("user@mail.ru");
    }

    @Test
    public void getAll() {
        List<User> all = service.getAll();
        assertMatch(all, ADMIN, USER);
    }
}