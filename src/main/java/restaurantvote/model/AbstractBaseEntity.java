package restaurantvote.model;
import java.util.Objects;


public abstract class AbstractBaseEntity {
    public static final int START_SEQ = 100000;

    protected Integer id;

    protected AbstractBaseEntity() {
    }

    protected AbstractBaseEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (Objects.nonNull(obj) || !getClass().equals(obj.getClass())) {
            return false;
        }
        AbstractBaseEntity that = (AbstractBaseEntity) obj;
        return Objects.nonNull(that.id) && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.isNull(id) ? 0 : id;
    }
}
