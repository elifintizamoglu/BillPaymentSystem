package com.elifintizam.BillPaymentSystem.controller;

import com.elifintizam.BillPaymentSystem.model.MemberAccount;
import com.elifintizam.BillPaymentSystem.service.MemberAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<MemberAccount> getMembers() {
        return memberAccountService.getMembers();
    }

    @PostMapping
    public void postMember(@RequestBody MemberAccount memberAccount) {
        memberAccountService.postMember(memberAccount);
    }

    @DeleteMapping(path = "{memberId}")
    public void deleteMember(@PathVariable("memberId") int memberId) {
        memberAccountService.deleteMember(memberId);
    }

    @PutMapping(path = "{memberId}")
    public void updateMember(@PathVariable("memberId") int memberId,
                             @RequestParam(required = false) String firstName,
                             @RequestParam(required = false) String lastName,
                             @RequestParam(required = false) Double balance) {
        memberAccountService.updateMember(memberId, firstName, lastName, balance);
    }


}
