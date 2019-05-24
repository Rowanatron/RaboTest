package statementprocessor.fileprocessor;

import org.springframework.web.multipart.MultipartFile;
import statementprocessor.exception.CouldNotParseException;
import statementprocessor.model.Report;
import statementprocessor.model.Transaction;
import statementprocessor.util.ReportGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;

public class CsvProcessor implements FileProcessor {

    public static final String SEPARATOR = ",";

    @Override
    public Report process(MultipartFile file) {
        Collection<Transaction> transactions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.ISO_8859_1))) {
            String line;
            br.readLine(); // skip first line with details
            while ((line = br.readLine()) != null) {
                String[] details = line.split(SEPARATOR);
                transactions.add(Transaction.fromArray(details));
            }
        } catch (IOException e) {
            throw new CouldNotParseException("Report was not generated, could not parse CSV");
        }
        return ReportGenerator.generate(file.getOriginalFilename(), transactions);
    }
}
