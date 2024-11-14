package org.itsf.business.entityImpl;

import org.itsf.business.entity.Good;
import org.itsf.utils.files.LineExtractor;

public class GoodDefinition extends Good {


    public GoodDefinition(LineExtractor le) {
        super();
        this.imported = le.isImported();
        this.name = le.getDescription();
        this.grossPrice = le.getGrossPrice();
        this.packaging = le.getPackaging();
        this.priceAfterSalesTax = le.getGrossPrice();

    }
}
