package com.elifintizam.BillPaymentSystem;

import com.elifintizam.BillPaymentSystem.model.MemberAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberAccountRepository extends JpaRepository<MemberAccount,Integer> {
}
