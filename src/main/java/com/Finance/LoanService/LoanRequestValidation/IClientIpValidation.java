package com.Finance.LoanService.LoanRequestValidation;

import com.Finance.LoanService.Entity.Loan;

public interface IClientIpValidation {
    public String clientIpValidator(Loan loan, String clientIp);
}
