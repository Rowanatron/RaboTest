package statementprocessor.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.CollectionUtils;
import statementprocessor.model.Transaction;
import statementprocessor.model.TransactionSummary;

import java.util.Arrays;
import java.util.Collection;

public class ReportGeneratorTest {

    private Transaction transactionValid1;
    private Transaction transactionValid2;
    private Transaction transactionInvalid;
    private Transaction transactionDuplicate1;
    private Transaction transactionDuplicate2andInvalid;

    private TransactionSummary transactionSummaryInv;
    private TransactionSummary transactionSummaryDup;
    private TransactionSummary transactionSummaryDupAndInv;

    private Collection<Transaction> transactionsWithFail;
    private Collection<Transaction> transactionsAllPass;
    private Collection<TransactionSummary> transactionsFailed;


    @Before
    public void setUp() {
        transactionValid1 = new Transaction(123456, "NL12TRIO2349092", "Money for Bobby", 12.33, 10, 22.33);
        transactionValid2 = new Transaction(123457, "NL12TRIO2349092", "Money for Robert", 12.33, -10, 2.33);
        transactionInvalid = new Transaction(123458, "NL12ABNA1349092", "Presents for Mom", 1000.00, -100.00, 1300.12);
        transactionDuplicate1 = new Transaction(111111, "NL12INGB2349092", "Cold hard Cash", -12.25, +10.00, -2.25);
        transactionDuplicate2andInvalid = new Transaction(111111, "NL12BICK2239092", "Description here please", 25.55, 10.00, 25.55);

        transactionSummaryDup = new TransactionSummary(transactionDuplicate1.getReference(), transactionDuplicate1.getDescription());
        transactionSummaryInv = new TransactionSummary(transactionInvalid.getReference(), transactionInvalid.getDescription());
        transactionSummaryDupAndInv = new TransactionSummary(transactionDuplicate2andInvalid.getReference(), transactionDuplicate2andInvalid.getDescription());

        transactionsWithFail = Arrays.asList(transactionValid1, transactionValid2, transactionInvalid, transactionDuplicate1, transactionDuplicate2andInvalid);
        transactionsAllPass = Arrays.asList(transactionValid1, transactionValid2, transactionDuplicate1);
        transactionsFailed = Arrays.asList(transactionSummaryDup, transactionSummaryDupAndInv, transactionSummaryInv);
    }

    @After
    public void tearDown() {

        transactionValid1 = null;
        transactionValid2 = null;
        transactionInvalid = null;
        transactionDuplicate1 = null;
        transactionDuplicate2andInvalid = null;

        transactionSummaryInv = null;
        transactionSummaryDup = null;
        transactionSummaryDupAndInv = null;

        transactionsWithFail = null;
        transactionsAllPass = null;
        transactionsFailed = null;

    }

    @Test
    public void generate_ReportWithPassAndFail_Test() {

        Assert.assertTrue(ReportGenerator.generate("fileName", transactionsWithFail).getTransactions().containsAll(transactionsFailed));
        Assert.assertEquals(ReportGenerator.FAIL_MASSAGE, ReportGenerator.generate("fileName", transactionsWithFail).getMessage());
        Assert.assertEquals(3, ReportGenerator.generate("fileName", transactionsWithFail).getTransactions().size());

        Assert.assertEquals(ReportGenerator.PASS_MESSAGE, ReportGenerator.generate("fileName", transactionsAllPass).getMessage());
        Assert.assertTrue(CollectionUtils.isEmpty(ReportGenerator.generate("fileName", transactionsAllPass).getTransactions()));


    }
}