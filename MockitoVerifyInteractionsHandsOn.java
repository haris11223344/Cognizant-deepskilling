import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;
import org.junit.jupiter.api.Test;

public class MockitoVerifyInteractionsHandsOn {

    interface NotificationService { void sendEmail(String email); }

    @Test
    void exercise01_verify() {
        NotificationService service = mock(NotificationService.class);
        service.sendEmail("abc@gmail.com");
        verify(service).sendEmail("abc@gmail.com");
    }

    @Test
    void exercise02_times() {
        NotificationService service = mock(NotificationService.class);
        service.sendEmail("a"); service.sendEmail("a");
        verify(service, times(2)).sendEmail("a");
    }

    @Test
    void exercise03_never() {
        NotificationService service = mock(NotificationService.class);
        verify(service, never()).sendEmail("x");
    }

    @Test
    void exercise04_atLeast() {
        NotificationService service = mock(NotificationService.class);
        service.sendEmail("a"); service.sendEmail("a");
        verify(service, atLeast(2)).sendEmail("a");
    }

    @Test
    void exercise05_atMost() {
        NotificationService service = mock(NotificationService.class);
        service.sendEmail("a");
        verify(service, atMost(3)).sendEmail("a");
    }

    @Test
    void exercise06_noInteractions() {
        NotificationService service = mock(NotificationService.class);
        verifyNoInteractions(service);
    }

    @Test
    void exercise07_noMoreInteractions() {
        NotificationService service = mock(NotificationService.class);
        service.sendEmail("a");
        verify(service).sendEmail("a");
        verifyNoMoreInteractions(service);
    }

    @Test
    void exercise08_inOrder() {
        NotificationService service = mock(NotificationService.class);
        service.sendEmail("A");
        service.sendEmail("B");
        var order = inOrder(service);
        order.verify(service).sendEmail("A");
        order.verify(service).sendEmail("B");
    }

    @Test
    void exercise09_anyString() {
        NotificationService service = mock(NotificationService.class);
        service.sendEmail("hello");
        verify(service).sendEmail(anyString());
    }

    @Test
    void exercise10_eq() {
        NotificationService service = mock(NotificationService.class);
        service.sendEmail("admin@gmail.com");
        verify(service).sendEmail(eq("admin@gmail.com"));
    }
}
