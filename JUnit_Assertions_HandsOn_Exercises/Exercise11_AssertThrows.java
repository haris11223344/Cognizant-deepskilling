import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class Exercise11_AssertThrows {

    @Test
    public void test() {
        assertThrows(ArithmeticException.class,()->{int x=10/0;});
    }
}
