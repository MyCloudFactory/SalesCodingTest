package org.itsf.business.exceptions;

public class OverTaxException extends Exception {


    public static final String OVERTAXMESSAGE = "overtaxed good : ";
    public static final String SALESTAXALREADYAPPLIED = "sales tax already applied";
    public static final String IMPORTDUTYALREADYAPPLIED = "import duty already applied";

    public OverTaxException(String message) {
        super(message);
    }
}
