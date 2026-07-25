import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class Exercise09_AssertArrayEquals {

    @Test
    public void test() {
        assertArrayEquals(new int[]{1,2},new int[]{1,2});
    }
}
