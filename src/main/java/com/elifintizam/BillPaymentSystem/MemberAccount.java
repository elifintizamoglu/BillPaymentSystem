package com.elifintizam.BillPaymentSystem;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "member_account")
public class MemberAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String memberCode;
    private String firstName;
    private String lastName;
    private double balance;

    public void setMemberCode(int id, String firstName) {
        this.memberCode =  id + firstName.substring(0,2);
    }
}
