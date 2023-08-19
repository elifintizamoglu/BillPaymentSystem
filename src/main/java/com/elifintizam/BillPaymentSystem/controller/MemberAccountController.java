package com.elifintizam.BillPaymentSystem.controller;

import com.elifintizam.BillPaymentSystem.model.MemberAccount;
import com.elifintizam.BillPaymentSystem.service.MemberAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/memberAccount")
public class MemberAccountController {

    private final MemberAccountServiceImpl memberAccountServiceImpl;

    @Autowired
    public MemberAccountController(MemberAccountServiceImpl memberAccountServiceImpl) {
        this.memberAccountServiceImpl = memberAccountServiceImpl;
    }

    @GetMapping
    public List<MemberAccount> getMembers() {
        return memberAccountServiceImpl.getMembers();
    }

    @PostMapping
    public void postMember(@RequestBody MemberAccount memberAccount) {
        memberAccountServiceImpl.postMember(memberAccount);
    }

    @DeleteMapping(path = "{memberId}")
    public void deleteMember(@PathVariable("memberId") int memberId) {
        memberAccountServiceImpl.deleteMember(memberId);
    }

    @PutMapping(path = "{memberId}")
    public void updateMember(@PathVariable("memberId") int memberId,
                             @RequestParam(required = false) String firstName,
                             @RequestParam(required = false) String lastName,
                             @RequestParam(required = false) Double balance) {
        memberAccountServiceImpl.updateMember(memberId, firstName, lastName, balance);
    }


}
