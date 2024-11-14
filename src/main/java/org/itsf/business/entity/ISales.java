package org.itsf.business.entity;

import org.itsf.business.entityImpl.Item;
import org.itsf.business.entityImpl.Sales;

import java.math.BigDecimal;
import java.util.List;

public interface ISales {

    BigDecimal getSalesTax();

    BigDecimal getTotal();

    List<Item> getItemsList();

    void addItem(Item item);

    Sales processInvoice();

}
