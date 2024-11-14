import org.itsf.business.entityImpl.Sales;
import org.itsf.business.exceptions.BadLineFormatException;
import org.itsf.utils.files.GoodsReader;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Paths;

import static org.itsf.utils.math.Operations.round;

public class SalesTest {

    private static final String INPUT1 = "src/test/resources/input1";
    private static final String INPUT2 = "src/test/resources/input2";
    private static final String INPUT3 = "src/test/resources/input3";
    private static final String INPUTBADLINE1 = "src/test/resources/inputBadLine1";
    private static final String INPUTBADLINE2 = "src/test/resources/inputBadLine2";

    @Test
    @Ignore
    public void testProcessInvoice() {



        //TEST INPUT 1
        File fi = Paths.get(INPUT1).toFile();

        try {
            Sales sales = new GoodsReader(fi).getSales();
            sales.processInvoice();
            Assert.assertEquals(round(new BigDecimal(1.50)), sales.getSalesTax());
            Assert.assertEquals(round(new BigDecimal(29.83)), sales.getTotal());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //TEST INPUT 2
        fi = Paths.get(INPUT2).toFile();

        try {
            Sales sales = new GoodsReader(fi).getSales();
            sales.processInvoice();
            Assert.assertEquals(round(new BigDecimal(7.65)), sales.getSalesTax());
            Assert.assertEquals(round(new BigDecimal(65.15)), sales.getTotal());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //TEST INPUT 3
        fi = Paths.get(INPUT3).toFile();

        try {
            Sales sales = new GoodsReader(fi).getSales();
            sales.processInvoice();
            Assert.assertEquals(round(new BigDecimal(6.70)), sales.getSalesTax());
            Assert.assertEquals(round(new BigDecimal(74.68)), sales.getTotal());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //TEST OVERTAXEXCEPTION (NOT POSSIBLE IN CONTEXT , IMPLEMENTED FOR EXTENDED USE)

        //TEST BADLINEFORMATEXCEPTION : PRICEUNKNOWN
        fi = Paths.get(INPUTBADLINE1).toFile();

        try {
            new GoodsReader(fi).getSales();
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage().contains(BadLineFormatException.PRICEUNKNOWN));
        }

        //TEST BADLINEFORMATEXCEPTION : QUANTITYUNKNOWN
        fi = Paths.get(INPUTBADLINE2).toFile();

        try {
            new GoodsReader(fi).getSales();
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage().contains(BadLineFormatException.QUANTITYUNKNOWN));
        }
    }




}
