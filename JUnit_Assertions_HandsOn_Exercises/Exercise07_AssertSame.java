import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class Exercise07_AssertSame {

    @Test
    public void test() {
        String s1="JUnit"; String s2=s1; assertSame(s1,s2);
    }
}
