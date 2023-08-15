package com.elifintizam.BillPaymentSystem;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table
public class MemberAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String memberCode;
    private String firstName;
    private String lastName;
    private double balance;

    public MemberAccount(String memberCode, String firstName, String lastName, double balance) {
        this.memberCode = memberCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        //setMemberCode(this.id,this.firstName);
    }

    /*public void setMemberCode(int id, String firstName) {
        this.memberCode = String.valueOf(id) + firstName.substring(0,2);
    }*/
}
