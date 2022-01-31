/**
* @author Oliver Gallo
* */

import Payment.PaymentReceiverImplementation;
import RunHandler.PaymentHandler;

public class Main {

    /**
     * Main method to start the API
     * @param args can be used to run it as a jar file
     */
    public static void main(String[] args) {
        //this is an example of how a file can be tested. When tested please put your own file!
        String income_payment = "C:/Users/Admin/Desktop/Lumera/Test_Files/Exempelfil_inbetalningstjansten.txt";
        new PaymentHandler(new PaymentReceiverImplementation(), income_payment);
    }
}
