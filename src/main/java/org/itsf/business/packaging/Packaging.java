package org.itsf.business.packaging;

public enum Packaging {
    BAR("bar"), BOX("box"), PACKET("packet"), BOTTLE("bottle");

    public final String label;

    Packaging(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
