package org.itsf.utils.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.itsf.business.adminData.AdminData.ROUNDUP_FACTOR;

public class Operations {

    public static BigDecimal roundUp(BigDecimal val)
    {
        return val.divide(ROUNDUP_FACTOR).setScale(0, RoundingMode.CEILING).multiply(ROUNDUP_FACTOR);
    }

    public static BigDecimal round(BigDecimal val)
    {
        return val.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
}
