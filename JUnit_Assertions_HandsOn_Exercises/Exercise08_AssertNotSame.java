import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class Exercise08_AssertNotSame {

    @Test
    public void test() {
        String s1=new String("Java"); String s2=new String("Java"); assertNotSame(s1,s2);
    }
}
