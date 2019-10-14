package com.Finance.LoanService.LoanRequestValidation.Imp;

import com.Finance.LoanService.LoanRequestValidation.ITimeValidation;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@Component
public class TimeValidation implements ITimeValidation {

    @Override
    public boolean timeValidator(LocalDateTime transactionDateTime){
        if(transactionDateTime.getHour()>=0 && transactionDateTime.getHour()<6) { // Risk time since 0 till 6 a.m.
            return false;
        }
        else{
            return true;
        }
    }

}
