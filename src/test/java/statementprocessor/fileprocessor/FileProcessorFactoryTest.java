package statementprocessor.fileprocessor;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FileProcessorFactoryTest {


    @Test
    public void getFileProcessorImplementation_XmlCsvUnsupported_Test() {

        assertTrue(FileProcessorFactory.getFileProcessorImplementation("csv") instanceof CsvProcessor);
        assertTrue(FileProcessorFactory.getFileProcessorImplementation("xml") instanceof XmlProcessor);
        assertTrue(FileProcessorFactory.getFileProcessorImplementation("pdf") instanceof UnsupportedProcessor);
    }


}