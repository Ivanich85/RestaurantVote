package restaurantvote.to;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TpUtils {
    public static <T> List<T> fromSetToList(Set<T> set) {
        return CollectionUtils.isNotEmpty(set) ? new ArrayList(set) : new ArrayList<>();
    }
}
