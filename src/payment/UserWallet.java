package payment;

public class UserWallet implements Payment {
    private Double balance;

    public UserWallet() {
        this.balance = Double.valueOf(0);
    }

    public UserWallet(double balance) {
        this.balance = Double.valueOf(balance);
    }

    public void addBalance(double balance) {
        this.balance += balance;
        System.out.println(Double.toString(balance) + " Baht added to your wallet");
        printBalance();
    }

    public void printBalance() {
        System.out.println("Current Balance: " + this.balance.toString() + " Baht");
    }
    
    @Override
    public boolean validatePayment(double payAmount) {
        System.out.println("Pay Amount: " + Double.toString(payAmount));
        printBalance();
        System.out.println("----------------------------------------");
        if (payAmount <= this.balance) {
            this.balance -= payAmount;
            System.out.println("Wallet payment successful");
            printBalance();
            return true;
        } else {
            System.out.println("Payment Error: Insufficient balance");
            return false;
        }
    }

}
