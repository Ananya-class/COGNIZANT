
public class SingletonPatternSystem {

    public static class LoggerSingleton {

        private LoggerSingleton() {
            System.out.println(">>> Logger Instance Initialized for the first time. <<<");
        }

        private static class Holder {

            private static final LoggerSingleton INSTANCE = new LoggerSingleton();
        }

        public static LoggerSingleton getInstance() {
            return Holder.INSTANCE;
        }

        // Action method
        public void log(String message) {
            System.out.println("[LOG]: " + message);
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Executing Singleton Pattern Verification ---");

        LoggerSingleton firstReference = LoggerSingleton.getInstance();
        LoggerSingleton secondReference = LoggerSingleton.getInstance();

        firstReference.log("Processing batch transactions...");
        secondReference.log("System idle.");

        System.out.println("\nVerification Check:");
        System.out.println("Do both variables reference the exact same object? " + (firstReference == secondReference));
    }
}
