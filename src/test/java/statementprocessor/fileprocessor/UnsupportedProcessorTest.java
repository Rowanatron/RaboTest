package statementprocessor.fileprocessor;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import statementprocessor.model.Report;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class UnsupportedProcessorTest {

    UnsupportedProcessor unsupportedProcessor;

    @Test
    public void process_UnsupportedFile_Test() {
        unsupportedProcessor = new UnsupportedProcessor();

        try {

        File file = new File("src/test/testUnsupportedFile.html");
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("testUnsupportedFile.html",
                file.getName(), "text/csv", IOUtils.toByteArray(input));

            Assert.assertEquals(new Report("testUnsupportedFile.html", "The uploaded file could not be processed, only CVS and XML are supported"), unsupportedProcessor.process(multipartFile));

        } catch (IOException e) {
            throw new RuntimeException("Something went wrong with IO in the CSV process test");
        }
    }
}