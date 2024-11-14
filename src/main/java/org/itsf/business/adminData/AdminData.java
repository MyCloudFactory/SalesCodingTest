package org.itsf.business.adminData;

import java.math.BigDecimal;

public class AdminData {

    //Applied tax
    public static final BigDecimal BASICSALESTAX = java.math.BigDecimal.valueOf(0.10);
    public static final BigDecimal IMPORTDUTY = BigDecimal.valueOf(0.05);

    public static final String IMPORTEDKEYWORD = "IMPORTED";

    public static final BigDecimal ROUNDUP_FACTOR = BigDecimal.valueOf(0.05);


    //Tax free Goods
    public enum GOODSTAXFREE {
        BOOK,
        FOOD,
        CHOCOLATE,
        PILLS,
        MEDICAL};

}
