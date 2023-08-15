package com.elifintizam.BillPaymentSystem;

import com.elifintizam.BillPaymentSystem.Model.MemberAccount;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MemberAccountService {

    private final MemberAccountRepository memberAccountRepository;

    @Autowired
    public MemberAccountService(MemberAccountRepository memberAccountRepository) {
        this.memberAccountRepository = memberAccountRepository;
    }

    public List<MemberAccount> getMembers() {
        return memberAccountRepository.findAll();
    }

    public void postMember(MemberAccount memberAccount) {
        memberAccountRepository.save(memberAccount);
        memberAccount.setMemberCode(memberAccount.getId(), memberAccount.getFirstName());
        memberAccountRepository.save(memberAccount);
    }

    public void deleteMember(int memberId) {
        boolean exists = memberAccountRepository.existsById(memberId);
        if (!exists) {
            throw new IllegalStateException("Member with id " + memberId + " does not exist.");
        }
        memberAccountRepository.deleteById(memberId);
    }

    @Transactional
    public void updateMember(int memberId, String firstName, String lastName, Double balance) {
        MemberAccount memberAccount = memberAccountRepository.findById(memberId).orElseThrow(() ->
                new IllegalStateException("Member with id " + memberId + " does not exist."));
        if (firstName != null &&
                firstName.length() > 0 &&
                !Objects.equals(memberAccount.getFirstName(), firstName)) {
            memberAccount.setFirstName(firstName);
        }
        if (lastName != null &&
                lastName.length() > 0 &&
                !Objects.equals(memberAccount.getLastName(), lastName)) {
            memberAccount.setLastName(lastName);
        }
        if (balance != null &&
                balance >= 0 &&
                balance != memberAccount.getBalance()) {
            memberAccount.setBalance(balance);
        }
    }
}
