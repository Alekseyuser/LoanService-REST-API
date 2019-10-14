package com.Finance.LoanService;

import com.Finance.LoanService.Entity.Client;
import com.Finance.LoanService.Entity.Loan;
import com.Finance.LoanService.Repository.ClientRepository;
import com.Finance.LoanService.Repository.LoanRepository;
import com.Finance.LoanService.Service.IClientService;
import com.Finance.LoanService.Service.ILoanService;
import com.Finance.LoanService.Service.IValidationService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDateTime;
import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class LoanServiceApplicationTests {

	private ILoanService iLoanService;
	private IClientService iClientService;
	private IValidationService iValidationService;
    private ClientRepository clientRepository;
    private LoanRepository loanRepository;

    @Autowired
    public LoanServiceApplicationTests (ILoanService iLoanService,
                                        IClientService iClientService,
                                        IValidationService iValidationService,
                                        ClientRepository clientRepository,
                                        LoanRepository loanRepository){

        this.iLoanService = iLoanService;
        this.iClientService = iClientService;
        this.iValidationService = iValidationService;
        this.clientRepository = clientRepository;
        this.loanRepository = loanRepository;
    }



    @Test
	public void createClient() {
        System.out.println(clientRepository.findById(1).orElse(null));
        Client client = new Client();
        client.setFirstName("Den");
        client.setLastName("Kotov");
        client.setPassportNumber(new Long(1234567));
        client.setCitizenship("Czech");
        iClientService.createClient(client);
        assertEquals(clientRepository.findById(1).orElse(null),client);
	}

    @Test
    public void createLoan() {
        Loan loan = new Loan();
        loan.setTransactionTime(LocalDateTime.now());
        loan.setAmount(1000);
        loan.setMonthTerm(12);
        loan.setClient(new Client(1,"Den","Kotov", new Long(1234567),"Czech" ));
        iLoanService.createLoan(loan);
        assertEquals(loanRepository.findById(1).orElse(null),loan);
    }

    @Test
    public void validateAll(){
        Loan loan = new Loan();
        loan.setTransactionTime(LocalDateTime.of(2019,9,2,10,10,10,111));
        loan.setAmount(1000);
        loan.setMonthTerm(12);
        loan.setClient(new Client(1,"Den","Kotov", new Long(1234567),"Czech" ));
        String ip = "1.1.1.1";
        assertThat(iValidationService.validateAll(loan,ip)).isNullOrEmpty();
    }
}
