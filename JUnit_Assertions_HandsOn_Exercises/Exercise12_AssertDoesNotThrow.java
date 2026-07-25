import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class Exercise12_AssertDoesNotThrow {

    @Test
    public void test() {
        assertDoesNotThrow(()->{int x=10/2;});
    }
}
