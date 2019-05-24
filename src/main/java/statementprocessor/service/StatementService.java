package statementprocessor.service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import statementprocessor.fileprocessor.FileProcessorFactory;
import statementprocessor.model.Report;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


@Service
public class StatementService {


    public Collection<Report> process(MultipartFile[] files) {
        Collection<Report> reports = new ArrayList<>();
        Arrays.stream(files).parallel().forEach(file ->
                reports.add(FileProcessorFactory
                        .getFileProcessorImplementation(FilenameUtils.getExtension(file.getOriginalFilename()))
                        .process(file)));
        return reports;
    }
}

