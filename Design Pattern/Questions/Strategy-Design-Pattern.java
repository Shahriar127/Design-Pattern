// Strategy Interface
interface PaymentStrategy {
    void pay(double amount);
}

// Concrete Strategy: Credit Card
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolder;

    public CreditCardPayment(String cardNumber, String cardHolder) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
    }

    @Override
    public void pay(double amount) {
        System.out.println("💳 Paid $" + amount + " using Credit Card [****" + 
            cardNumber.substring(cardNumber.length() - 4) + "] - " + cardHolder);
    }
}

// Concrete Strategy: PayPal
class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println("📧 Paid $" + amount + " via PayPal (" + email + ")");
    }
}

// Concrete Strategy: Cryptocurrency
class CryptoPayment implements PaymentStrategy {
    private String walletAddress;

    public CryptoPayment(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    @Override
    public void pay(double amount) {
        System.out.println("🪙 Paid $" + amount + " using Crypto Wallet [" + 
            walletAddress.substring(0, 6) + "...]");
    }
}

// Context Class
class PaymentProcessor {
    private PaymentStrategy strategy;

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void processPayment(double amount) {
        if (strategy == null) {
            System.out.println("❌ No payment method selected.");
        } else {
            strategy.pay(amount);
        }
    }
}

// Main Class to run the example
public class PaymentDemo {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();

        // Use PayPal
        processor.setStrategy(new PayPalPayment("user@example.com"));
        processor.processPayment(150.0);

        // Switch to Credit Card
        processor.setStrategy(new CreditCardPayment("1234567890123456", "John Doe"));
        processor.processPayment(300.0);

        // Switch to Crypto
        processor.setStrategy(new CryptoPayment("0xAB1234CD5678EF90"));
        processor.processPayment(500.0);
    }
}
