package Payment;

import Enums.TypeOfFile;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public abstract class PaymentType {
    private ArrayList<PaymentPosts> paymentPosts;
    private Enums.TypeOfFile type;
    private int clearingsNr;
    private long accountNr;
    private String currency;
    private String account;
    private BigDecimal totalAmount;
    private int amountOfPosts;
    private Date date;


    /**
     * Super class for the general attributes that the payment types can have. With this solution a new
     * payment type can be added, and uniquely modified to contain special attributes.
     *
     * @param type is the file type
     * @param clearingsNr the clearings number
     * @param accountNr the account number
     */
    protected PaymentType(Enums.TypeOfFile type, int clearingsNr, long accountNr) {
        this.type = type;
        this.clearingsNr = clearingsNr;
        this.accountNr = accountNr;
        this.account = clearingsNr + " " + accountNr;
        currency = "SEK";
        date = new Date();
        this.paymentPosts = new ArrayList<>();
    }
    //Add payment column to a list
    public void addPaymentPost(BigDecimal amount, String reference) {
        paymentPosts.add(new PaymentPosts(amount, reference));
    }

    /**
     * Get the paymentposts
     * @return amount of payment posts
     */
    public ArrayList<PaymentPosts> getPaymentPosts() {
        return paymentPosts;
    }

    /**
     * Get the type of a file
     * @return the file type
     */
    public TypeOfFile getType() {
        return type;
    }

    /**
     * Get the clearings number
     * @return the cearings number
     */
    public int getClearingsNr() {
        return clearingsNr;
    }

    /**
     * Get the account number
     * @return the account number
     */
    public long getAccountNr() {
        return accountNr;
    }

    /**
     * Get the currency
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Get the account (account = clearings number + account number)
     * @return the account
     */
    public String getAccount() {
        return account;
    }

    /**
     * Gets the date
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Get the total amount of the payment
     * @return the total amount
     */
    public BigDecimal getTotalAmount() { return totalAmount;}

    /**
     * Set a new total amount
     * @param totalAmount set it to a new total amount
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount.abs();
    }


    /**
     * Get the amount posts in the file
     * @return amount of posts in the file
     */
    public int getAmountOfPosts() {return amountOfPosts;}

    /**
     * Sets a new amount of posts
     * @param amountOfPosts set a new amount of posts
     */
    public void setAmountOfPosts(int amountOfPosts) {
        this.amountOfPosts = amountOfPosts;
    }

    /*
     * This inner class creates a post object. Another alternative would be to use a multimap
     * That allows duplicate keys.
     */
    public static class PaymentPosts {
        private BigDecimal amount;
        private String reference;

        public PaymentPosts(BigDecimal amount, String reference) {
            this.amount = amount;
            this.reference = reference;
        }

        public String getReference() {
            return reference;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        @Override
        public String toString() {
            return amount + " " + reference;
        }
    }
}
