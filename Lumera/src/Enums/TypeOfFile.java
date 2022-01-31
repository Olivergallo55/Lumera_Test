package Enums;


/**
 * Enum class to set the unique name type for a certain payment type. With this solution a new payment type
 * can be safely added and checked.
 * */
public enum TypeOfFile {

    DISBURSEPAYMENT("_betalningsservice.txt"),
    INCOMINGPAYMENT("_inbetalningstjansten.txt");

   public final String NAME;

    TypeOfFile(String type){
       NAME = type;
   }
}
