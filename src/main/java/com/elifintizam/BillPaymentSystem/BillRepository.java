package com.elifintizam.BillPaymentSystem;

import com.elifintizam.BillPaymentSystem.Model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
}
