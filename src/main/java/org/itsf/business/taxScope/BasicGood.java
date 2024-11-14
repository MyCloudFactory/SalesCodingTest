package org.itsf.business.taxScope;

import org.itsf.business.adminData.AdminData;
import org.itsf.business.entity.Good;
import org.itsf.business.exceptions.OverTaxException;

import java.math.BigDecimal;

import static org.itsf.utils.math.Operations.round;

public class BasicGood extends Good {

    private final static BigDecimal salesTax = AdminData.BASICSALESTAX;


    public BasicGood(Good good) throws OverTaxException {
        super(good);

        if (!salesTaxProcessDone) {
            priceAfterSalesTax = round(priceAfterSalesTax.add(grossPrice.multiply(salesTax)));
            salesTaxProcessDone = true;
        } else {
            throw new OverTaxException(OverTaxException.OVERTAXMESSAGE + OverTaxException.SALESTAXALREADYAPPLIED);
        }
    }

}
