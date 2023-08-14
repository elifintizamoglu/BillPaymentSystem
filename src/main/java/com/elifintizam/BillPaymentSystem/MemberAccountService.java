package com.elifintizam.BillPaymentSystem;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberAccountService {

    public List<MemberAccount> getMembers(){
        return List.of(
                new MemberAccount(1L,"1El","Elif","İntizamoğlu",100));
    }
}
