package com.Finance.LoanService.Service.Imp;

import com.Finance.LoanService.Entity.Loan;
import com.Finance.LoanService.LoanRequestValidation.IClientIpValidation;
import com.Finance.LoanService.LoanRequestValidation.IClientValidation;
import com.Finance.LoanService.LoanRequestValidation.ITimeValidation;
import com.Finance.LoanService.Service.IValidationService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@Component
public class ValidationService implements IValidationService {

    private IClientValidation iClientValidation;
    private ITimeValidation iTimeValidation;
    private IClientIpValidation iClientIpValidation;

    @Autowired
    public ValidationService(IClientValidation iClientValidation,ITimeValidation iTimeValidation,IClientIpValidation iClientIpValidation){
        this.iClientIpValidation = iClientIpValidation;
        this.iTimeValidation = iTimeValidation;
        this.iClientValidation = iClientValidation;
    }

    @Override
    public String validateAll(Loan loan, String clientIp) {
        if(!iClientValidation.clientValidator(loan.getClient())){
            return "Client does not exist in System. Please check input data and try again.";
        }

        if(!iTimeValidation.timeValidator(loan.getTransactionTime())){
            return "Dismissed. You can not apply since 00:00 till 06:00. Please try later.";
        }

        if(iClientIpValidation.clientIpValidator(loan, clientIp) !=null){
            return "A lot of attempts has been detected. Please try again tomorrow.";
        }

        return null;
    }
}
