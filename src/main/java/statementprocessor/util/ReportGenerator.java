package statementprocessor.util;

import org.springframework.util.CollectionUtils;
import statementprocessor.model.Report;
import statementprocessor.model.Transaction;
import statementprocessor.model.TransactionSummary;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public class ReportGenerator {

    public static final String PASS_MESSAGE = "All transactions were validated";
    public static final String FAIL_MASSAGE = "The following transactions could not be validated";
    public static final String NO_TRANSACTIONS = "The uploaded file contained no transactions";

    public static Report generate(String fileName, Collection<Transaction> transactions) {

        if (CollectionUtils.isEmpty(transactions)) {
            return new Report(fileName, NO_TRANSACTIONS);
        }

        Collection<TransactionSummary> invalidTransactions = Stream
                .of(getInvalidBalanceTransactions(transactions), getInvalidReferenceTransactions(transactions))
                .flatMap(Collection::stream)
                .distinct()
                .map(Transaction::summarize)
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(invalidTransactions)) {
            return new Report(fileName, PASS_MESSAGE);
        }

        return new Report(fileName, FAIL_MASSAGE, invalidTransactions);
    }

    private static Collection<Transaction> getInvalidReferenceTransactions(Collection<Transaction> transactions) {
        return transactions
                .parallelStream()
                .collect(groupingBy(Transaction::getReference))
                .values()
                .stream()
                .filter(duplicates -> duplicates.size() > 1)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private static Collection<Transaction> getInvalidBalanceTransactions(Collection<Transaction> transactions) {
        return transactions
                .parallelStream()
                .filter(Transaction::invalidBalance)
                .collect(Collectors.toList());
    }
}
