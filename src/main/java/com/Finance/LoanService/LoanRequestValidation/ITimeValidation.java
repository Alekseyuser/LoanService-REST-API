package com.Finance.LoanService.LoanRequestValidation;

import java.time.LocalDateTime;

public interface ITimeValidation {
    public boolean timeValidator(LocalDateTime transactionDateTime);
}
