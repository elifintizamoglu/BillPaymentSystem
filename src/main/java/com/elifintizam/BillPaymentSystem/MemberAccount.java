package com.elifintizam.BillPaymentSystem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberAccount {

    private Long id;
    private String memberCode;
    private String firstName;
    private String lastName;
    private double balance;

}
