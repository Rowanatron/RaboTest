package statementprocessor.fileprocessor;

import org.apache.commons.io.IOUtils;
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
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class XmlProcessorTest {

    XmlProcessor xmlProcessor;
    Report reportWithInvalid;
    Report reportNoTransactions;
    Report reportAllValidated;
    TransactionSummary transactionSummary;
    Collection<TransactionSummary> transactionSummaryCollection;

    @Before
    public void setUp() {
        xmlProcessor = new XmlProcessor();
    }

    @Test
    public void process_WithTransactionsThatCanNotBeValidated_Test() {

        transactionSummary = new TransactionSummary(1234, "Failed transaction from testfile");
        transactionSummaryCollection = Arrays.asList(transactionSummary);
        reportWithInvalid = new Report("testXmlFail.xml", ReportGenerator.FAIL_MASSAGE, transactionSummaryCollection);

        try {

            File file = new File("src/test/testXmlFail.xml");
            FileInputStream input = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile("file",
                    file.getName(), "text/csv", IOUtils.toByteArray(input));

            assertEquals(reportWithInvalid, xmlProcessor.process(multipartFile));

        } catch (IOException e) {
            throw new RuntimeException("Something went wrong with IO in the XML process test");
        }
    }


    @Test
    public void process_NoTransactionsInFile_Test() {
        reportNoTransactions = new Report("testXmlEmpty.xml", ReportGenerator.NO_TRANSACTIONS);

        try {

            File file = new File("src/test/testXmlEmpty.xml");
            FileInputStream input = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile("file",
                    file.getName(), "text/csv", IOUtils.toByteArray(input));

            assertEquals(reportNoTransactions, xmlProcessor.process(multipartFile));

        } catch (IOException e) {
            throw new RuntimeException("Something went wrong with IO in the XML process test");
        }
    }

    @Test
    public void process_AllTransactionsValidated_Test() {
        reportAllValidated = new Report("testXmlValid.xml", ReportGenerator.PASS_MESSAGE);

        try {

            File file = new File("src/test/testXmlValid.xml");
            FileInputStream input = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile("file",
                    file.getName(), "text/csv", IOUtils.toByteArray(input));

            assertEquals(reportAllValidated, xmlProcessor.process(multipartFile));

        } catch (IOException e) {
            throw new RuntimeException("Something went wrong with IO in the XML process test");
        }
    }
}