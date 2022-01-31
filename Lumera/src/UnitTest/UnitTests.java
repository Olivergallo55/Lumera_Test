package UnitTest;

import Enums.TypeOfFile;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import Parser.*;
import Payment.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;


public class UnitTests {


    @Test
    public void successfulParseOnIncomePayment() {
        File CORRECT_FILE = new File("C:/Users/Admin/Desktop/Lumera_Inbetalningar/Exempelfil_inbetalningstjansten.txt");
        FileReader fileReader = new FileReader();
        PaymentType returnValue = fileReader.parseReader(CORRECT_FILE);

        PaymentType expected_outcome = new IncomingPayment(TypeOfFile.INCOMINGPAYMENT, 1234, 1234567897);
        expected_outcome.setTotalAmount(new BigDecimal("15300.00"));

        assertEquals(expected_outcome.getType(), returnValue.getType());
        assertEquals(expected_outcome.getAccount(), returnValue.getAccount());
        assertEquals(expected_outcome.getTotalAmount(), returnValue.getTotalAmount());
    }

    @Test
    public void testCreatingWrongPaymentObjects() {

        assertThrowsExactly(IllegalArgumentException.class, () -> new IncomingPayment(TypeOfFile.DISBURSEPAYMENT, 1234, 1234567897));
        assertThrowsExactly(IllegalArgumentException.class, () -> new Disburse(TypeOfFile.INCOMINGPAYMENT, 5555, 5555555555L,
                new BigDecimal(4711.17), reformatDate("20110315"), 4, "SEK"));

    }


    @Test
    public void successfulParseDisbursePayment(){
        File CORRECT_FILE = new File("C:/Users/Admin/Desktop/Lumera_Inbetalningar/Exempelfil_betalningsservice.txt");
        FileReader fileReader = new FileReader();
        PaymentType returnValue = fileReader.parseReader(CORRECT_FILE);

        PaymentType expected_outcome = new Disburse(TypeOfFile.DISBURSEPAYMENT, 5555, 5555555555L,
                new BigDecimal("4711.17"), reformatDate("20110315"), 4, "SEK");

        assertEquals(expected_outcome.getAccount(), returnValue.getAccount());
        assertEquals(expected_outcome.getTotalAmount(), returnValue.getTotalAmount());
        assertEquals(expected_outcome.getAmountOfPosts(), returnValue.getAmountOfPosts());
    }

    private Date reformatDate(String date) {
        Date formattedDate = null;
        try {
            formattedDate = new SimpleDateFormat("yyyyMMdd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }

    private BigDecimal getDecimal(String amount) {
        String decimalNr = amount.substring(amount.length() - 2); //get the last two decimals
        amount = amount.substring(0, amount.length() - 2) + "." + decimalNr;
        return new BigDecimal(amount);
    }
}
