package statementprocessor.fileprocessor;

import org.springframework.web.multipart.MultipartFile;
import statementprocessor.exception.CouldNotParseException;
import statementprocessor.model.Report;
import statementprocessor.model.Statement;
import statementprocessor.util.ReportGenerator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;

public class XmlProcessor implements FileProcessor {

    public Report process(MultipartFile file) {
        Statement statementObj;

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Statement.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            statementObj = (Statement) jaxbUnmarshaller.unmarshal(file.getInputStream());
        } catch (JAXBException | IOException e) {
            throw new CouldNotParseException("Report was not generated, could not parse XML");
        }
        return ReportGenerator.generate(file.getOriginalFilename(), statementObj.getTransactions());
    }
}
