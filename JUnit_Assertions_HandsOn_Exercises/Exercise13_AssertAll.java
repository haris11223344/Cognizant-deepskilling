import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class Exercise13_AssertAll {

    @Test
    public void test() {
        assertAll(() -> assertEquals(5,2+3), () -> assertTrue(true));
    }
}
