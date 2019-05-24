package statementprocessor.fileprocessor;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import statementprocessor.model.Report;
import statementprocessor.model.TransactionSummary;
import statementprocessor.util.ReportGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;

public class CsvProcessorTest {

    CsvProcessor csvProcessor;
    Report reportWithInvalid;
    Report reportNoTransactions;
    Report reportAllValidated;
    TransactionSummary transactionSummary;
    Collection<TransactionSummary> transactionSummaryCollection;

    @Before
    public void setUp() {
        csvProcessor = new CsvProcessor();
    }

    @Test
    public void process_WithTransactionsThatCanNotBeValidated_Test() {

        transactionSummary = new TransactionSummary(1234, "Failed transaction from testfile");
        transactionSummaryCollection = Arrays.asList(transactionSummary);
        reportWithInvalid = new Report("testCsvFail.csv", ReportGenerator.FAIL_MASSAGE, transactionSummaryCollection);

        try {

            File file = new File("src/test/testCsvFail.csv");
            FileInputStream input = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile("file",
                    file.getName(), "text/csv", IOUtils.toByteArray(input));

            Assert.assertEquals(reportWithInvalid, csvProcessor.process(multipartFile));

        } catch (IOException e) {
            throw new RuntimeException("Something went wrong with IO in the CSV process test");
        }
    }

    @Test
    public void process_NoTransactionsInFile_Test() {
        reportNoTransactions = new Report("testCsvEmpty.csv", ReportGenerator.NO_TRANSACTIONS);

        try {

            File file = new File("src/test/testCsvEmpty.csv");
            FileInputStream input = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile("file",
                    file.getName(), "text/csv", IOUtils.toByteArray(input));

            Assert.assertEquals(reportNoTransactions, csvProcessor.process(multipartFile));

        } catch (IOException e) {
            throw new RuntimeException("Something went wrong with IO in the CSV process test");
        }
    }

    @Test
    public void process_AllTransactionsValidated_Test() {
        reportAllValidated = new Report("testCsvValid.csv", ReportGenerator.PASS_MESSAGE);



        try {

            File file = new File("src/test/testCsvValid.csv");
            FileInputStream input = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile("file",
                    file.getName(), "text/csv", IOUtils.toByteArray(input));

            Assert.assertEquals(reportAllValidated, csvProcessor.process(multipartFile));

        } catch (IOException e) {
            throw new RuntimeException("Something went wrong with IO in the CSV process test");
        }

    }
}