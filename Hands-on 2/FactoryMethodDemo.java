public class FactoryMethodDemo {
    public static void main(String[] args) {
        NotificationFactory factory;

        factory = new EmailFactory();
        factory.notifyUser();

        factory = new SMSFactory();
        factory.notifyUser();
    }
}
