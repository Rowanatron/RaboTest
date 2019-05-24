package statementprocessor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import statementprocessor.model.Report;
import statementprocessor.service.StatementService;

import java.util.Collection;


@RestController
@RequestMapping(path = "/statements")
public class StatementController {

    private final StatementService statementService;

    @Autowired
    public StatementController(final StatementService statementService) {
        this.statementService = statementService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Collection<Report> process(@RequestBody MultipartFile[] statements) {
        return statementService.process(statements);
    }
}
