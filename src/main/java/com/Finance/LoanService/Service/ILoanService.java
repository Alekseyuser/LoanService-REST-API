package com.Finance.LoanService.Service;

import com.Finance.LoanService.Entity.Loan;

import java.util.List;
import java.util.Optional;

public interface ILoanService {
     Loan createLoan(Loan loan);
     Optional<List<Loan>> getClientLoans(String firstName, String lastName, Long passportNumber);
}
