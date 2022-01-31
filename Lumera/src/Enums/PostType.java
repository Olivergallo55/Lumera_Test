package Enums;


/**
 * Enum class to set the unique attribute of a certain payment type. With this solution a new unique attribute
 * can be added for a new or existing payment type
 * */

public enum PostType {

    PAYMENTSTART("00"),
    PAYMENTPOST("30"),
    PAYMENTEND("99"),
    DISBURSESTART("O"),
    DISBURSEPOST("B");

    public final String VALUE;

    PostType(String value) {
        VALUE = value;
    }
}
