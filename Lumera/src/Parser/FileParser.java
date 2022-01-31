package Parser;

import Enums.PostType;
import Enums.TypeOfFile;
import Payment.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;


/**
 * Class is used to parse a file into one of the supported formats
 */
public class FileParser {
    private PaymentType payment;


    /**
     * Parse the file depending on the files type
     *
     * @param file is the given file
     * @param type the type of the file
     */
    public PaymentType parseFile(String file, TypeOfFile type) {
        try {
            return switch (type) {
                case INCOMINGPAYMENT -> parseIncomingPayment(file);
                case DISBURSEPAYMENT -> parseDisbursePayment(file);
                default -> throw new IllegalArgumentException("Please put in a file that is supported");
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Parse and create disbursePayment object
     *
     * @param file is the given file
     */
    private PaymentType parseDisbursePayment(String file) {
        try {
            if (file.substring(0, 1).equals(PostType.DISBURSESTART.VALUE)) {
                int clearingNr = Integer.parseInt(file.substring(1, 5).trim());
                long accountNr = Long.parseLong(file.substring(6, 16).trim());
                BigDecimal totalAmount = new BigDecimal(file.substring(16, 30).trim().replace(',', '.'));
                int numberOfPayments = Integer.parseInt(file.substring(30, 40).trim());
                String date = file.substring(40, 48).trim();
                String currency = file.substring(48, 51).trim();
                payment = new Disburse(TypeOfFile.DISBURSEPAYMENT, clearingNr, accountNr, totalAmount,
                        reformatDate(date), numberOfPayments, currency);
            } else if (file.substring(0, 1).equals(PostType.DISBURSEPOST.VALUE)) {
                if(payment == null)
                    throw new NoSuchElementException("No opening post were found");
                else {
                    BigDecimal amount = new BigDecimal(file.substring(1, 15).trim().replace(',', '.'));
                    String reference = file.substring(15, 25).trim();
                    payment.addPaymentPost(amount, reference);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payment;
    }

    /**
     * Parse and create IncomingPayment object
     *
     * @param file is the given file
     */
    public PaymentType parseIncomingPayment(String file) {
        try {
            if (file.substring(0, 2).equals(PostType.PAYMENTSTART.VALUE)) {
                int clearingNr = Integer.parseInt(file.substring(10, 14));
                long accountNr = Integer.parseInt(file.substring(14, 24));
                payment = new IncomingPayment(TypeOfFile.INCOMINGPAYMENT, clearingNr, accountNr);
            } else if (file.substring(0, 2).equals(PostType.PAYMENTPOST.VALUE)) {
                if(payment == null)
                    throw new NoSuchElementException("No opening post were found");
                BigDecimal amount = getDecimal(file.substring(2, 22));
                String reference = file.substring(40, 50);
                payment.addPaymentPost(amount, reference);
            } else if (file.substring(0, 2).equals(PostType.PAYMENTEND.VALUE)) {
                if(payment == null)
                    throw new NoSuchElementException("No opening post were found");
                BigDecimal totalAmount = getDecimal(file.substring(2, 22));
                payment.setTotalAmount(totalAmount);
                int numberOfPayments = Integer.parseInt(file.substring(30, 38));
                payment.setAmountOfPosts(numberOfPayments);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payment;
    }

    /**
     * Get the amount by extracting the last two decimals and transforming the value to a BigDecimal
     *
     * @param amount is the given amount
     */
    private BigDecimal getDecimal(String amount) {
        String decimalNr = amount.substring(amount.length() - 2); //get the last two decimals
        amount = amount.substring(0, amount.length() - 2) + "." + decimalNr;
        return new BigDecimal(amount);
    }

    /**
     * Reformats the date given by a String
     *
     * @param date is the Date String
     */
    private Date reformatDate(String date) {
        Date formattedDate = null;
        try {
            formattedDate = new SimpleDateFormat("yyyyMMdd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }
}
