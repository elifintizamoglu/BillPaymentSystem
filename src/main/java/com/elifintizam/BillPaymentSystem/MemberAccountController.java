package com.elifintizam.BillPaymentSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/memberAccount")
public class MemberAccountController {

    private final MemberAccountService memberAccountService;

    @Autowired
    public MemberAccountController(MemberAccountService memberAccountService) {
        this.memberAccountService = memberAccountService;
    }

    @GetMapping
    public List<MemberAccount> getMembers(){
        return memberAccountService.getMembers();
    }
}
