package com.Finance.LoanService.Service;

import com.Finance.LoanService.Entity.Loan;


public interface IValidationService {
    public String validateAll(Loan loan, String clientIp);
}
