package com.Finance.LoanService.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Clients")
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="clientId")
    private Integer clientId;
    @NonNull
    @Column(name="firstName")
    private String firstName;
    @NonNull
    @Column(name="lastName")
    private String lastName;
    @NonNull
    @Column(name="passportNumber")
    private Long passportNumber;
    @NonNull
    @Column(name="citizenship")
    private String citizenship;

    public Client(String firstName, String lastName, Long passportNumber, String citizenship) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.citizenship = citizenship;
    }
}
