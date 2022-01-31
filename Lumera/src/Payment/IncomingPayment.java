package Payment;

import Enums.TypeOfFile;


public class IncomingPayment extends PaymentType {

    /**
     * Public constructor. This class inherit from the superclass PaymentType
     *
     * @param type is the file type
     * @param clearingsNr the clearings number
     * @param accountNr the account number
     */
    public IncomingPayment(TypeOfFile type, int clearingsNr, long accountNr) {
        super(type, clearingsNr, accountNr);
        if(!type.equals(TypeOfFile.INCOMINGPAYMENT)){
            throw new IllegalArgumentException("IncomingPayment must be of the Enum type TypeOfFile.INCOMINGPAYMENT");
        }
    }
}
