package statementprocessor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    private String fileName;
    private String message;
    private Collection<TransactionSummary> transactions;

    public Report(String fileName, String message) {
        this.fileName = fileName;
        this.message = message;
    }


}