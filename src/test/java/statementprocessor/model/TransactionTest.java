package statementprocessor.model;

import org.assertj.core.util.Arrays;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TransactionTest {

    private Transaction transactionValidSubtract;
    private Transaction transactionValidAdd;
    private Transaction transactionInvalidBalance;
    private Transaction expectedFromArray;
    private Transaction toSummarize;
    private TransactionSummary summarized;


    @Before
    public void setUp() {

        transactionValidSubtract = new Transaction();
        transactionValidSubtract.setStartBalance(100.25);
        transactionValidSubtract.setEndBalance(24.75);
        transactionValidSubtract.setMutation(-75.50);

        transactionValidAdd = new Transaction();
        transactionValidAdd.setStartBalance(75.25);
        transactionValidAdd.setEndBalance(100.00);
        transactionValidAdd.setMutation(+24.75);

        transactionInvalidBalance = new Transaction();
        transactionInvalidBalance.setStartBalance(23.40);
        transactionInvalidBalance.setEndBalance(1234.99);
        transactionInvalidBalance.setMutation(-34.00);

    }

    @After
    public void tearDown() {
        transactionValidSubtract = null;
        transactionValidAdd = null;
        transactionInvalidBalance = null;
        expectedFromArray = null;
        toSummarize = null;
        summarized = null;
    }

    @Test
    public void validateBalance_SubtractAddAndInvalid_Test() {


        Assert.assertTrue(transactionValidSubtract.validateBalance());
        Assert.assertTrue(transactionValidAdd.validateBalance());
        Assert.assertFalse(transactionInvalidBalance.validateBalance());


    }

    @Test
    public void invalidBalance_SubtractAddAndInvalid_Test() {

        Assert.assertFalse(transactionValidSubtract.invalidBalance());
        Assert.assertFalse(transactionValidAdd.invalidBalance());
        Assert.assertTrue(transactionInvalidBalance.invalidBalance());
    }

    @Test
    public void fromArray_CreateTransactionFromArray_Test() {

        expectedFromArray = new Transaction(123456, "NL12TRIO2349092", "Money for Bobby", 12.33, 10.00, 23.33);

        Assert.assertEquals(expectedFromArray, Transaction.fromArray(Arrays.array("123456", "NL12TRIO2349092", "Money for Bobby", "12.33", "10.00", "23.33")));
        Assert.assertNotEquals(transactionInvalidBalance, Transaction.fromArray(Arrays.array("123456", "NL12TRIO2349092", "Money for Bobby", "12.33", "10.00", "23.33")));

    }

    @Test
    public void summerize_CreateSummaryObjectFromTransaction_Test() {

        toSummarize = new Transaction();
        toSummarize.setReference(123);
        toSummarize.setDescription("Hello");

        summarized = new TransactionSummary(123, "Hello");

        Assert.assertEquals(summarized, toSummarize.summarize());

    }
}