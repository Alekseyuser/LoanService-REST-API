package com.Finance.LoanService.Controller;

import com.Finance.LoanService.Entity.Client;
import com.Finance.LoanService.Entity.Loan;
import com.Finance.LoanService.Service.IClientService;
import com.Finance.LoanService.Service.ILoanService;
import com.Finance.LoanService.Service.IValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class LoanServiceController {

//    @Autowired
//    IClientService clientService;
//
//    @Autowired
//    ILoanService loanService;
//
//    @Autowired
//    IValidationService iValidationService;


    private Map<Loan,String> loanMap;
    private String validationResult;

    private IClientService clientService;
    private ILoanService loanService;
    private IValidationService iValidationService;

    @Autowired
    public LoanServiceController(IClientService clientService,ILoanService loanService,IValidationService iValidationService){
        this.clientService = clientService;
        this.loanService = loanService;
        this.iValidationService = iValidationService;
        loanMap = new HashMap();
    }


    @PostMapping(value = "client")
    public ResponseEntity<Client> createClient(@RequestBody Client client){
        return new ResponseEntity<>(clientService.createClient(client), HttpStatus.CREATED);
    }

    @PostMapping(value = "loan")
    public ResponseEntity<Map<Loan,String>> createLoan(@RequestBody Loan loan, HttpServletRequest request){
        loanMap.clear();
        validationResult = iValidationService.validateAll(loan, request.getRemoteAddr());
        if(validationResult == null) {
            loanMap.put(loanService.createLoan(loan),"Loan has been approved");
            return new ResponseEntity<>(loanMap, HttpStatus.CREATED);
        }
        else{
            loanMap.put(loan,"Loan has been dismissed. " + validationResult);

            return new ResponseEntity<>(loanMap, HttpStatus.EXPECTATION_FAILED);

        }
    }

    @GetMapping(value = "loanhistory/{firstName}+{lastName}+{passportNumber}")
    public ResponseEntity<List<Loan>> getLoanByUser(@PathVariable String firstName,@PathVariable String lastName, @PathVariable Long passportNumber) {
        Assert.notNull(firstName, "firstName can not be null");
        if(loanService.getClientLoans(firstName, lastName, passportNumber).isPresent()) {
            return new ResponseEntity<>(loanService.getClientLoans(firstName, lastName, passportNumber).get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
