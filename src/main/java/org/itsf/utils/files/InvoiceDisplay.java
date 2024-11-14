package org.itsf.utils.files;

import org.apache.commons.lang3.StringUtils;
import org.itsf.business.entityImpl.Sales;
import org.itsf.business.taxScope.ImportedGood;

public class InvoiceDisplay {

    private final static String INFOSEPARATOR = ":";
    private Sales sales;

    public InvoiceDisplay(Sales sales) {
        this.sales = sales;
    }

    public void display() {

        //display results
        sales.getItemsList().forEach(item -> {

            StringBuilder sb = new StringBuilder();

            sb.append(item.getQuantity());
            sb.append(StringUtils.SPACE);
            if (item.getGood().isImported()) {
                sb.append(ImportedGood.IMPORTEDFLAG.toLowerCase());
                sb.append(StringUtils.SPACE);
            }
            sb.append(item.getGood().getName());
            sb.append(INFOSEPARATOR);
            sb.append(item.getGood().getPriceAfterSalesTax());

            System.out.println(sb);

        });

        //
        StringBuilder sbfin = new StringBuilder();
        sbfin.append("Sales Taxes: ");
        sbfin.append(sales.getSalesTax());
        sbfin.append(" Total: ");
        sbfin.append(sales.getTotal());

        System.out.println(sbfin);
        System.out.println(StringUtils.EMPTY);
        System.out.println(StringUtils.EMPTY);

    }





}
