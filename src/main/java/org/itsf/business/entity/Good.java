package org.itsf.business.entity;

import org.itsf.business.packaging.Packaging;

import java.math.BigDecimal;

public abstract class Good {

    protected String name;
    protected BigDecimal grossPrice;
    protected BigDecimal priceAfterSalesTax;
    protected Packaging packaging;

    protected Boolean imported;

    protected Boolean salesTaxProcessDone = false;
    protected Boolean importDutyProcessDone = false;

    public Good() {
    }

    public Good(Good good) {
        this();
        this.name = good.name;
        this.imported = good.imported;
        setGrossPrice(good.grossPrice);
        this.packaging = good.packaging;
        this.salesTaxProcessDone = good.salesTaxProcessDone;
        this.importDutyProcessDone = good.importDutyProcessDone;
        this.priceAfterSalesTax = good.priceAfterSalesTax;
    }

    public BigDecimal getPriceAfterSalesTax() {
        return priceAfterSalesTax;
    }

    public BigDecimal getGrossPrice() {
        return grossPrice;
    }

    public void setGrossPrice(BigDecimal grossPrice) {

        if (this.grossPrice == null || !this.grossPrice.equals(grossPrice)) {
            this.grossPrice = grossPrice;
            //compute new priceAfterTax
            this.priceAfterSalesTax = this.grossPrice;
        }

    }

    public Packaging getPackaging() {
        return packaging;
    }

    public void setPackaging(Packaging packaging) {
        this.packaging = packaging;
    }

    public Boolean IsSalesTaxProcessDone() {
        return salesTaxProcessDone;
    }

    public Boolean iSDutyImportProcessDone() {
        return importDutyProcessDone;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setImported(Boolean imported) {
        this.imported = imported;
    }

    public Boolean isImported() {
        return imported;
    }


}
