package org.itsf.business.taxScope;

import org.itsf.business.entity.Good;
import org.itsf.business.exceptions.OverTaxException;

public class SpecialGood extends Good {


    public SpecialGood(Good good) throws OverTaxException {
        super(good);

        if (!salesTaxProcessDone) {
            salesTaxProcessDone = true;
        } else {
            throw new OverTaxException(OverTaxException.OVERTAXMESSAGE + OverTaxException.SALESTAXALREADYAPPLIED);
        }
    }

}
