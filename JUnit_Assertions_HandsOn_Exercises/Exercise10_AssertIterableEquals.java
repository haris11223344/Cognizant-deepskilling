import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class Exercise10_AssertIterableEquals {

    @Test
    public void test() {
        assertIterableEquals(java.util.Arrays.asList("A","B"),java.util.Arrays.asList("A","B"));
    }
}
