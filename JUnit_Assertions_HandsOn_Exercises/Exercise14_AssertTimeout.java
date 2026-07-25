import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class Exercise14_AssertTimeout {

    @Test
    public void test() {
        assertTimeout(java.time.Duration.ofSeconds(2),()->Thread.sleep(1000));
    }
}
