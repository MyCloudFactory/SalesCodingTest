package org.itsf.business.entityImpl;

import org.itsf.business.entity.Good;
import org.itsf.business.entity.IItem;

public class Item implements IItem {

    private Good good;

    private Long quantity;


    public Item(Good good, Long quantity) {
        this.good = good;
        this.quantity = quantity;
    }

    @Override
    public Good getGood() {
        return good;
    }

    @Override
    public Long getQuantity() {
        return quantity;
    }

}
