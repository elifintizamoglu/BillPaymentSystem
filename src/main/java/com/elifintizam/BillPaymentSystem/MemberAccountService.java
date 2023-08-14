package com.elifintizam.BillPaymentSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberAccountService {

    private  final MemberAccountRepository memberAccountRepository;

    @Autowired
    public MemberAccountService(MemberAccountRepository memberAccountRepository){
        this.memberAccountRepository = memberAccountRepository;
    }

    public List<MemberAccount> getMembers(){
        return memberAccountRepository.findAll();
    }
}
