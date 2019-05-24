package statementprocessor.model;


import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@Data
@XmlRootElement(name = "records")
@XmlAccessorType(XmlAccessType.FIELD)
public class Statement {


    @XmlElement(name = "record")
    private Collection<Transaction> transactions;

}

