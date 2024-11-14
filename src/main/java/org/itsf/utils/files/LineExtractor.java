package org.itsf.utils.files;

import org.apache.commons.lang3.StringUtils;
import org.itsf.business.exceptions.BadLineFormatException;
import org.itsf.business.packaging.Packaging;
import org.itsf.business.taxScope.ImportedGood;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

public class LineExtractor {


    private final String PRICEFLAG = " at ";

    private String lineContent;

    private Long quantity;

    private BigDecimal grossPrice;

    private String description;

    private Packaging packaging;

    private Boolean imported = false;

    public LineExtractor(String lineContent) throws BadLineFormatException {

        this.lineContent = lineContent.strip();

        quantity = processQuantity();

        grossPrice = processGrossprice();

        if (!quantity.equals(0L) && !grossPrice.equals(BigDecimal.ZERO)) processDescription();

    }

    private Long processQuantity() throws BadLineFormatException {

        String[] arr = lineContent.split(StringUtils.SPACE);

        if (arr.length == 0 || arr[0].strip().isEmpty()) {
            throw new BadLineFormatException(BadLineFormatException.QUANTITYUNKNOWN);
        }

        Long result;
        try {
            result = Long.parseLong(arr[0].strip());
        } catch (Exception e) {
            throw new BadLineFormatException(BadLineFormatException.QUANTITYUNKNOWN);
        }

        return result;
    }


    private BigDecimal processGrossprice() throws BadLineFormatException {

        if (!lineContent.contains(PRICEFLAG)) {
            throw new BadLineFormatException(BadLineFormatException.PRICEUNKNOWN);
        }

        return new BigDecimal(lineContent.substring(lineContent.indexOf(PRICEFLAG) + PRICEFLAG.length()).strip());

    }


    private void processDescription() {

        Integer startIndex = lineContent.indexOf(StringUtils.SPACE);
        Integer endIndex = lineContent.indexOf(PRICEFLAG);

        description = processPackaging(processImported(lineContent.substring(startIndex, endIndex).strip()));

    }


    private String processPackaging(String description) {

        List<String> packagingList = Stream.of(Packaging.values())
                .map(Packaging::getLabel)
                .map(String::toUpperCase)
                .toList();

        for (String packtype : packagingList) {

            if (description.toUpperCase().contains(packtype)) {
                packaging = Packaging.valueOf(packtype);
                break;
            }
        }


        return description;
    }

    private String processImported(String description) {

        if (description.toUpperCase().contains(ImportedGood.IMPORTEDFLAG)) {
            imported = true;
        }
        return description;
    }

    public Long getQuantity() {
        return quantity;
    }

    public BigDecimal getGrossPrice() {
        return grossPrice;
    }

    public String getDescription() {
        return description;
    }

    public Packaging getPackaging() {
        return packaging;
    }

    public Boolean isImported() {
        return imported;
    }
}
