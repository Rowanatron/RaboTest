package statementprocessor.fileprocessor;

public class FileProcessorFactory {

    private FileProcessorFactory() {

    }

    public static FileProcessor getFileProcessorImplementation(String fileExtension) {
        switch (fileExtension) {
            case "csv":
                return new CsvProcessor();
            case "xml":
                return new XmlProcessor();
            default:
                return new UnsupportedProcessor();
        }
    }
}
