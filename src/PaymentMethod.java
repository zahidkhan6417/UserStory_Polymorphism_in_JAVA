import java.sql.SQLOutput;

abstract class PaymentMethod {
    protected double amount;
    protected String currency;

    public PaymentMethod(double amount, String currency){
        this.amount = amount;
        this.currency = currency;
    }

    public abstract void processPayment();
}

class CreditCard extends PaymentMethod {
    private String cardNumber;
    private String expirationDate;
    private String CVV;

    public CreditCard(double amount, String currency, String cardNumber, String expirationDate, String CVV) {
        super(amount, currency);
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.CVV = CVV;

    }
    public void processPayment(){
        System.out.println("Processing credit card payment....");
        if(isInvalidCard()){
            System.out.println("payment processed  successfully");
        }else {
            System.out.println("Error, invalid credit card details");
        }
    }
    private boolean isInvalidCard(){
        return cardNumber.length() == 16 && expirationDate.matches("\\d{2}/\\d{2}") && CVV.length() == 3;
    }
}

class BankTransfer extends PaymentMethod {
    private String accountNumber;
    private String routingNumber;
    public BankTransfer(double amount, String currency, String accountNumber, String routingNumber){
        super(amount, currency);
        this.accountNumber = accountNumber;
        this.routingNumber = routingNumber;
    }
    public void processPayment(){
        System.out.println("Processing Bank Transfer  Payemnt");
        if(isValidBankAccount()){
            System.out.println("Payment successfull");
        } else {
            System.out.println("Error: invalid account details");
        }
    }

    private boolean isValidBankAccount(){
        return accountNumber.length() == 10 && routingNumber.length() == 9;
    }
}

class DigitalWallet extends PaymentMethod {
    private String walletId;
    private String password;
    public DigitalWallet(double amount, String currency, String walletId, String password){
        super(amount, currency);
        this.walletId = walletId;
        this.password = password;
    }

    public void processPayment(){
        System.out.println("proceessing digital wallet payment");
        if(isValidWallet()){
            System.out.println("payment proceed successfully");
        } else {
            System.out.println("Error: ivaalid walletid and password");
        }
    }

    private boolean isValidWallet(){
        return walletId.contains("@") && password.length() >= 8;
    }
}
