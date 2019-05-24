package statementprocessor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "record")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transaction {

    public static final int REFERENCE = 0;
    public static final int ACCOUNT_NR = 1;
    public static final int DESCRIPTION = 2;
    public static final int START_BALANCE = 3;
    public static final int MUTATION = 4;
    public static final int END_BALANCE = 5;

    @XmlAttribute
    private int reference;
    @XmlElement
    private String accountNumber;
    @XmlElement
    private String description;
    @XmlElement
    private double startBalance;
    @XmlElement
    private double mutation;
    @XmlElement
    private double endBalance;


    public boolean validateBalance() {
        double roundingErrorMargin = 0.0001;
        double expectedBalance = this.startBalance + this.mutation;
        return Math.abs(this.endBalance - expectedBalance) < roundingErrorMargin;
    }

    public boolean invalidBalance() {
        return !this.validateBalance();
    }

    public static Transaction fromArray(String[] array) {
        return new Transaction(Integer.parseInt(array[REFERENCE]), array[ACCOUNT_NR], (array[DESCRIPTION]), Double.parseDouble(array[START_BALANCE]), Double.parseDouble(array[MUTATION]), Double.parseDouble(array[END_BALANCE]));
    }

    public TransactionSummary summarize() {
        return new TransactionSummary(this.reference, this.description);
    }


}
