package com.Finance.LoanService.Repository;

import com.Finance.LoanService.Entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {
    /*List<Loan> getLoanByClient_FirstNameAnAndClient_LastNameAndClient_PassportNumber(String firstName,
                                                                                     String lastName,
                                                                                     Long passportNumber);*/
    Optional<List<Loan>> getLoanByClient_FirstNameAndClient_LastNameAndClient_PassportNumber(
            String firstName, String lastName, Long passportNumber);

}
