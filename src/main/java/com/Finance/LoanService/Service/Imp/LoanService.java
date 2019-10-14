package com.Finance.LoanService.Service.Imp;

import com.Finance.LoanService.Entity.Loan;
import com.Finance.LoanService.Repository.LoanRepository;
import com.Finance.LoanService.Service.ILoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class LoanService implements ILoanService {

    private LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository){
        this.loanRepository = loanRepository;
    }

    @Override
    public Loan createLoan(Loan loan) {
        return loanRepository.save(loan);
    }



    @Override
    public Optional<List<Loan>> getClientLoans(String firstName, String lastName, Long passportNumber) {
        return loanRepository.getLoanByClient_FirstNameAndClient_LastNameAndClient_PassportNumber(
                firstName, lastName, passportNumber);
    }


}
