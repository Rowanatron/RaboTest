package statementprocessor.fileprocessor;

import org.springframework.web.multipart.MultipartFile;
import statementprocessor.model.Report;

public class UnsupportedProcessor implements FileProcessor {

    @Override
    public Report process(MultipartFile file) {
        return new Report(file.getOriginalFilename(), "The uploaded file could not be processed, only CVS and XML are supported");
    }
}
