package payment;

public class CreditCard implements Payment{
    @Override
    public boolean validatePayment(double payAmount) {
        System.out.println("Pay Amount: " + Double.toString(payAmount));
        System.out.println("Credit Card payment successful");
        return true;
    } 
}
