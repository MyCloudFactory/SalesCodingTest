package org.itsf.business.taxScope;

import org.itsf.business.adminData.AdminData;
import org.itsf.business.entity.Good;
import org.itsf.business.exceptions.OverTaxException;

import java.math.BigDecimal;

import static org.itsf.utils.math.Operations.round;

public class ImportedGood extends Good {


    public final static String IMPORTEDFLAG = "IMPORTED";
    private final static BigDecimal importDuty = AdminData.IMPORTDUTY;


    public ImportedGood(Good good) throws OverTaxException {
        super(good);

        if (!importDutyProcessDone) {
            priceAfterSalesTax = round(priceAfterSalesTax.add(grossPrice.multiply(importDuty)));
            importDutyProcessDone = true;
        } else {
            throw new OverTaxException(OverTaxException.OVERTAXMESSAGE + OverTaxException.IMPORTDUTYALREADYAPPLIED);
        }
    }

}
