package restaurantvote.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import restaurantvote.UserTestData;
import restaurantvote.model.User;
import restaurantvote.model.values.Role;

import javax.persistence.PersistenceException;
import java.util.Collections;
import java.util.Date;

@ContextConfiguration(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
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
                new Date(), Collections.singleton(Role.ROLE_USER));
        User created = service.create(newUser);
        Integer newId = created.getId();
        newUser.setId(newId);
        UserTestData.assertMatch(created, newUser);
        UserTestData.assertMatch(service.get(newId), newUser);
    }

    @Test(expected = PersistenceException.class)
    public void duplicateMailCreate() {
        User created = service.create(new User(null, "Duplicate", "admin@gmail.com", "newPass", false,
                new Date(), Collections.singleton(Role.ROLE_USER)));
        service.getAll();
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void get() {
    }

    @Test
    public void getByEmail() {
    }

    @Test
    public void getAll() {
    }
}