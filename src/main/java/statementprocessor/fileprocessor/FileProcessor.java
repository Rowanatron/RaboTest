package statementprocessor.fileprocessor;

import org.springframework.web.multipart.MultipartFile;
import statementprocessor.model.Report;

public interface FileProcessor {

    Report process(MultipartFile file);

}
