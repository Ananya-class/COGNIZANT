
public class FactoryMethodSystem {

    public interface Notification {

        void sendNotification();
    }

    public static class EmailNotification implements Notification {

        @Override
        public void sendNotification() {
            System.out.println("Dispatching Email: Connection established with SMTP mail server.");
        }
    }

    public static class SmsNotification implements Notification {

        @Override
        public void sendNotification() {
            System.out.println("Dispatching SMS: Text payload sent successfully to cellular gateway.");
        }
    }

    public static class NotificationFactory {

        public Notification createNotification(String channelType) {
            if (channelType == null || channelType.trim().isEmpty()) {
                throw new IllegalArgumentException("Channel type cannot be null or empty.");
            }

            return switch (channelType.toUpperCase()) {
                case "EMAIL" ->
                    new EmailNotification();
                case "SMS" ->
                    new SmsNotification();
                default ->
                    throw new IllegalArgumentException("Unsupported delivery channel: " + channelType);
            };
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Executing Factory Method Pattern Verification ---");

        NotificationFactory factory = new NotificationFactory();

        try {
            // Decoupled object generation via factory method
            Notification email = factory.createNotification("EMAIL");
            email.sendNotification();

            Notification sms = factory.createNotification("SMS");
            sms.sendNotification();

            // Testing exception/error handling capabilities
            System.out.println("\nTesting error mitigation:");
            factory.createNotification("PUSH_NOTIFICATION");

        } catch (IllegalArgumentException e) {
            System.out.println("Caught Expected Safe Error: " + e.getMessage());
        }
    }
}
