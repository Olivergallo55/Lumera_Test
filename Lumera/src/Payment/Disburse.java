package Payment;

import Enums.TypeOfFile;
import java.math.BigDecimal;
import java.util.Date;


public class Disburse extends PaymentType {
    private String currency;
    private Date date;


    /**
     * Public constructor. This class inherit from the superclass PaymentType.
     *
     * @param type is the payment type
     * @param clearingsNr the clearings number
     * @param accountNr the account number
     * @param totalAmount the total amount
     * @param date the date
     * @param amountOfPosts the amount of posts in the file
     * @param currency the given currency
     */
    public Disburse(TypeOfFile type, int clearingsNr, long accountNr, BigDecimal totalAmount, Date date,
                       int amountOfPosts, String currency) {
        super(type, clearingsNr, accountNr);
        if(!type.equals(TypeOfFile.DISBURSEPAYMENT)){
            throw new IllegalArgumentException("Disburse must be of the Enum type TypeOfFile.DisbursePayment");
        }
        this.currency = currency;
        this.date = date;
        setTotalAmount(totalAmount);
        setAmountOfPosts(amountOfPosts);
    }

    @Override
    public Date getDate(){
        return date;
    }
    @Override
    public String getCurrency(){
        return currency;
    }
}
