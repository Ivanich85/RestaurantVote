package restaurantvote;

import org.springframework.test.web.servlet.ResultMatcher;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static restaurantvote.TestUtil.readListFromJsonMvcResult;

public class TestMatchers<T> {
    private final Class<T> clazz;
    private final String[] fieldsToIgnore;
    private final boolean useEquals;

    public static <T> TestMatchers<T> useFieldsComparator(Class<T> clazz, String... fieldsToIgnore) {
        return new TestMatchers<>(clazz, false, fieldsToIgnore);
    }

    public static <T> TestMatchers<T> useEquals(Class<T> clazz) {
        return new TestMatchers<>(clazz, true);
    }

    private TestMatchers(Class<T> clazz, boolean useEquals, String... fieldsToIgnore) {
        this.clazz = clazz;
        this.useEquals = useEquals;
        this.fieldsToIgnore = fieldsToIgnore;
    }

    public void assertMatch(T actual, T expected) {
        if (useEquals) {
            assertThat(actual).isEqualTo(expected);
        } else {
            assertThat(actual).isEqualToIgnoringGivenFields(expected, fieldsToIgnore);
        }
    }

    public void assertMatch(Iterable<T> actual, T... expected) {
        assertMatch(actual, Stream.of(expected).collect(Collectors.toList()));
    }

    public void assertMatch(Iterable<T> actual, Iterable<T> expected) {
        if (useEquals) {
            assertThat(actual).isEqualTo(expected);
        } else {
            assertThat(actual).usingElementComparatorIgnoringFields(fieldsToIgnore).isEqualTo(expected);
        }
    }

    public ResultMatcher contentJson(T expected) {
        return result -> assertMatch(TestUtil.readFromJsonMvcResult(result, clazz), expected);
    }

    public ResultMatcher contentJson(T... expected) {
        return contentJson(Stream.of(expected).collect(Collectors.toList()));
    }

    public ResultMatcher contentJson(Iterable<T> expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, clazz), expected);
    }
}
