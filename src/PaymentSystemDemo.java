import java.util.Scanner;
import java.util.Random;
public class PaymentSystemDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter payment amount");
        double amount = sc.nextDouble();

        System.out.println("Enter currency (e.g., USD, EUR, GBP): ");
        String currrency = sc.next();

        System.out.println("Enter payment method (e.g., 1. Credit Card, 2. Bank Transfer, 3. Digital Wallet): ");
        int choice = sc.nextInt();

        PaymentMethod paymentMethod = null;

        switch(choice){
            case 1:
                System.out.println("Enter credit card details (cardNumber expirationDate cvv): ");
                paymentMethod = new CreditCard(amount, currrency, sc.next(), sc.next(), sc.next());
                break;
            case 2:
                System.out.println("Enter bank transfer details (accountNumber routingNumber): ");
                paymentMethod = new BankTransfer(amount, currrency, sc.next(), sc.next());
                break;
            case 3:
                System.out.println("Enter digital wallet details (walletId password): ");
                paymentMethod = new DigitalWallet(amount, currrency, sc.next(), sc.next());
                break;
            default:
                System.out.println("Invalid choice. Payment method not supported.");
        }
        if(paymentMethod != null){
            processPayment(paymentMethod);
        }
        sc.close();
    }
    public static void processPayment(PaymentMethod paymentMethod){
        Random random = new Random();
        int transactionId = random.nextInt(100000);
        System.out.println("Initiating payment transaction ID:" +transactionId);
        try {
            paymentMethod.processPayment();
        } catch (Exception e){
            System.out.println("Error: " +e.getMessage());
        } finally {
            logTransaction(transactionId, paymentMethod, e);
        }
        System.out.println("Transaction ID:" +transactionId+"completed\n");
    }

    private static void logTransaction(int transactionID, PaymentMethod paymentMethod, Exception e){
        System.out.println("Loggin transaction ID: " +transactionID);
        System.out.println("payment method:" +paymentMethod.getClass().getSimpleName());
        System.out.println("Logged successfully\n");

        if (e != null) {
            System.out.println("Error Details: " + e.getMessage());
        } else {
            System.out.println("Payment Successful");
        }
        System.out.println("Logged successfully\n");
    }
}
