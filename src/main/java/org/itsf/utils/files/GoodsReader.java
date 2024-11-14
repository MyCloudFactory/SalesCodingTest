package org.itsf.utils.files;

import org.itsf.business.adminData.AdminData;
import org.itsf.business.entity.Good;
import org.itsf.business.entityImpl.GoodDefinition;
import org.itsf.business.entityImpl.Item;
import org.itsf.business.entityImpl.Sales;
import org.itsf.business.exceptions.BadLineFormatException;
import org.itsf.business.exceptions.OverTaxException;
import org.itsf.business.taxScope.BasicGood;
import org.itsf.business.taxScope.ImportedGood;
import org.itsf.business.taxScope.SpecialGood;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

public class GoodsReader {

    private final File inputFile;

    public GoodsReader(File inputFile) {
        this.inputFile = inputFile;
    }

    public Sales getSales() throws IOException, BadLineFormatException, OverTaxException {

        Sales result = new Sales();



        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {

            // Good Type resolution
            while (reader.ready()) {
                LineExtractor le = new LineExtractor(reader.readLine());

                //NEW GOOD
                Good newGood = new GoodDefinition(le);

                //GOOD TYPE
                if (taxFreeProductDetection(newGood.getName())) {
                    newGood = new SpecialGood(newGood);
                } else {
                    //By default basic Good
                    newGood = new BasicGood(newGood);
                }

                //IMPORTED TYPE
                if (newGood.isImported()) {

                    newGood = new ImportedGood(newGood);
                }


                //ADDITION TO SALES
                result.addItem(new Item(newGood, le.getQuantity()));


            }
        } catch (Exception e) {
            throw e;
        }

        return result;
    }



    private Boolean taxFreeProductDetection(String description) {

        List<String> taxfreeList = Stream.of(AdminData.GOODSTAXFREE.values())
                .map(Enum::name)
                .map(String::toUpperCase)
                .toList();

        for(String keyword : taxfreeList) {
            if (description.toUpperCase().contains(keyword)) {
                return true;
            }
        }

        return false;
    }



}
