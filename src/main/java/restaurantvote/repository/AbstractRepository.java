package restaurantvote.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public abstract class AbstractRepository {

    @PersistenceContext
    protected EntityManager manager;
}
