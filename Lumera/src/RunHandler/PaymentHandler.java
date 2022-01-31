package RunHandler;

import Payment.*;
import java.io.*;
import Parser.FileReader;


/**
 * This class handles the payments and invokes the interface
 */
public class PaymentHandler {

    private final PaymentReceiverImplementation payment;

    /**
     * public Constructor
     *
     * @param payment is the interface implementation
     * @param file is the given file to parse
     */
    public PaymentHandler(PaymentReceiverImplementation payment, String file){

        this.payment = payment;
        parseFile(file);
    }

    /**
     * Parse the given file and invoke the interface methods
     *
     * @param line is the given file to read
     */
    private void parseFile(String line){
        FileReader file = new FileReader();

        PaymentType pay = file.parseReader(new File(line));
        invokeInterface(pay);
    }

    /**
     * Invokes the interface and starts the Bundle
     * @param pay is the payment type
     */
    private void invokeInterface(PaymentType pay){
        payment.startPaymentBundle(pay.getAccount(), pay.getDate(), pay.getCurrency());
        pay.getPaymentPosts().forEach(e-> payment.payment(e.getAmount(),e.getReference()));
        payment.endPaymentBundle();
    }
}
