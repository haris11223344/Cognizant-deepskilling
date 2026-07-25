import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class Exercise02{
 interface Calculator{int add(int a,int b);}
 @Test void demo(){
  Calculator c=mock(Calculator.class);
  when(c.add(2,3)).thenReturn(5);
  assertEquals(5,c.add(2,3));
  verify(c).add(2,3);
 }
}