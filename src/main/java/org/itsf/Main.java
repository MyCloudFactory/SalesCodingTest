package org.itsf;

import org.apache.commons.lang3.StringUtils;
import org.itsf.business.entityImpl.Sales;
import org.itsf.utils.files.GoodsReader;
import org.itsf.utils.files.InvoiceDisplay;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

public class Main {

    private static final String RESOURCES_PATH = "src/test/resources";
    public static void main(String[] args) {

        System.out.println("****************");
        System.out.println("Sales Simulation");
        System.out.println("****************");


        //Files walking

        try (Stream<Path> stream = Files.list(Paths.get(RESOURCES_PATH))) {

            stream.map(Path::toFile)
                    .peek(x -> {
                        StringBuilder sbstart = new StringBuilder();
                        sbstart.append("Sales for : ");
                        sbstart.append(x.getName());

                        System.out.println(sbstart);
                        System.out.println(StringUtils.EMPTY);

                    })
                    .map(GoodsReader::new)
                    .map(x -> {
                        Sales sales = null;
                        try {
                            sales = x.getSales();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        return sales;
                    })
                    .filter(Objects::nonNull)
                    .map(Sales::processInvoice)
                    .map(InvoiceDisplay::new)
                    .forEach(InvoiceDisplay::display);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}