package com.Finance.LoanService.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import org.springframework.lang.NonNull;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Loans")
public class Loan implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="loanId")
    private Integer loanId;
    @NonNull
    @Column(name="transactionTime")
    private LocalDateTime transactionTime;
    @NonNull
    @Column(name="amount")
    //@Size(max = 10000000)
    @Range(min=1, max=1000000)
    private Integer amount;
    @NonNull
    @Column(name="monthTerm")
    //@Size(max = 360)
    @Range(min=1, max=360)
    private Integer monthTerm;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clientId")
    @OnDelete(action = OnDeleteAction.CASCADE)

    private Client client;

}
