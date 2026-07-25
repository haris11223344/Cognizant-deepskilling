import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class Exercise05_AssertNull {

    @Test
    public void test() {
        String name=null;
                assertNull(name);
    }
}
