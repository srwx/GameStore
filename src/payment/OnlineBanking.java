package payment;

public class OnlineBanking implements Payment{
    @Override
    public boolean validatePayment(double payAmount) {
        System.out.println("Pay Amount: " + Double.toString(payAmount));
        System.out.println("Online banking payment successful");
        return true;
    }
}
