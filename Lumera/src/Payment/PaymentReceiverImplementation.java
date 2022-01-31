package Payment;

import java.math.BigDecimal;
import java.util.Date;

/**
 * This class implements the given interface and invokes it to start the Bundle
 * */

public class PaymentReceiverImplementation implements PaymentReceiver{
    @Override
    public void startPaymentBundle(String accountNumber, Date paymentDate, String currency) {
        System.out.println("Payment Started");
        System.out.println("Account Number: " + accountNumber + " Date: " + paymentDate + " Currency: " + currency);
    }

    @Override
    public void payment(BigDecimal amount, String reference) {
        System.out.println(amount + " " + reference);
    }

    @Override
    public void endPaymentBundle() {
        System.out.println("Payment Ended");
    }
}
