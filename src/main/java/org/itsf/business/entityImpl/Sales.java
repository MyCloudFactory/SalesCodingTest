package org.itsf.business.entityImpl;

import org.itsf.business.entity.ISales;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.itsf.utils.math.Operations.round;
import static org.itsf.utils.math.Operations.roundUp;

public class Sales implements ISales {

    private BigDecimal salesTax;

    private BigDecimal total;

    private List<Item> listItems;


    public Sales() {
        this.listItems = new ArrayList<>();
    }


    public Sales(List<Item> listItems) {
        this.listItems = listItems;
    }

    @Override
    public BigDecimal getSalesTax() {
        return salesTax;
    }

    @Override
    public BigDecimal getTotal() {
        return total;
    }

    @Override
    public void addItem(Item item) {
        listItems.add(item);
    }

    @Override
    public List<Item> getItemsList() {
        return listItems;
    }

    @Override
    public Sales processInvoice() {

        //Init
        salesTax = BigDecimal.valueOf(0);
        total = BigDecimal.valueOf(0);

        for(Item item : listItems) {
            salesTax = salesTax.add(item.getGood().getPriceAfterSalesTax().subtract(item.getGood().getGrossPrice()).multiply(BigDecimal.valueOf(item.getQuantity())));
            total = total.add(item.getGood().getPriceAfterSalesTax().multiply(BigDecimal.valueOf(item.getQuantity())));
        }

        salesTax = roundUp(salesTax);
        total = round(total);

        return this;
    }



}
