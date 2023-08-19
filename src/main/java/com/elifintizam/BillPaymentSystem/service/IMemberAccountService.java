package com.elifintizam.BillPaymentSystem.service;

import com.elifintizam.BillPaymentSystem.model.MemberAccount;

import java.util.List;

public interface IMemberAccountService {

    List<MemberAccount> getMembers();

    void postMember(MemberAccount memberAccount);

    void deleteMember(int memberId);

    void updateMember(int memberId, String firstName, String lastName, Double balance);

}
