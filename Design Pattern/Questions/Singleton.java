// Singleton.java

class Logger {
    // Step 1: private static instance
    private static Logger instance;

    // Step 2: private constructor
    private Logger() {
        System.out.println("Logger initialized ✅");
    }

    // Step 3: public method to get the single instance (Lazy Initialization)
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // Step 4: example method
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}

public class Singleton {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        logger1.log("Starting the system...");

        Logger logger2 = Logger.getInstance();
        logger2.log("User logged in.");

        System.out.println("logger1 == logger2: " + (logger1 == logger2));
    }
}
