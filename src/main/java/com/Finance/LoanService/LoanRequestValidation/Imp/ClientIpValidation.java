package com.Finance.LoanService.LoanRequestValidation.Imp;

import com.Finance.LoanService.Entity.Loan;
import com.Finance.LoanService.LoanRequestValidation.IClientIpValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ClientIpValidation implements IClientIpValidation {

    private Map<String,Integer> clientIpMap = new HashMap<>(); //store count of loans group by IP address
    private final int maxLoanRequestsFromSingleIp = 3;
    private LocalDate savedDate;

    @Override
    public String clientIpValidator(Loan loan, String clientIp){
        if(clientIpMap.get(clientIp)==null){ //put ip if it doesn't exist
            clientIpMap.put(clientIp,1);
            savedDate= LocalDate.now();
            return null;
        }
        else if (clientIpMap.get(clientIp) < maxLoanRequestsFromSingleIp) { //increment count of ip if it less than max possible value
            if(savedDate.equals(LocalDate.now()) ) {
                    clientIpMap.put(clientIp, clientIpMap.get(clientIp) + 1);
                    return null;
            }
                else{
                    savedDate = LocalDate.now();
                    return null;
                }
            }
            else {
                return "There were more than 3 attempt per day."; // if count of ip more than max possible value return false
            }
    }
}

