package com.elifintizam.BillPaymentSystem.service;

import com.elifintizam.BillPaymentSystem.exception.InsufficientBalanceException;
import com.elifintizam.BillPaymentSystem.model.Bill;
import com.elifintizam.BillPaymentSystem.repository.BillRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BillService {

    private final BillRepository billRepository;

    @Autowired
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public List<Bill> getBills() {
        return billRepository.findAll();
    }

    public void postBill(Bill bill) {
        billRepository.save(bill);
    }

    public void deleteBill(int billId) {
        boolean exists = billRepository.existsById(billId);
        if (!exists) {
            throw new IllegalStateException("Bill with id " + billId + " does not exist.");
        }
        billRepository.deleteById(billId);
    }

    @Transactional
    public void updateBill(int billId, Double amount, Date processDate, String billType) {
        Bill bill = billRepository.findById(billId).orElseThrow(() ->
                new IllegalStateException("Bill with id " + billId + " does not exist."));
        if (amount != null &&
                amount >= 0 &&
                amount != bill.getAmount()) {
            bill.setAmount(amount);
        }
        if (processDate != null &&
                processDate != bill.getProcessDate()) {
            bill.setProcessDate(processDate);
        }
        if (billType != null &&
                (billType.equals("Phone") || billType.equals("Internet") || billType.equals("Water")) &&
                !billType.equals(bill.getBillType())) {
            bill.setBillType(billType);
        }
    }

    @Transactional
    public void payBill(int billId) {
        Bill bill = billRepository.findById(billId).orElseThrow(() ->
                new IllegalStateException("Bill with id " + billId + " does not exist."));
        if (bill.getMemberAccount().getBalance() >= bill.getAmount()) {
            bill.getMemberAccount().setBalance(bill.getMemberAccount().getBalance() - bill.getAmount());
            bill.setAmount(0);
        }
        else {
            throw new InsufficientBalanceException("Balance of the member with id "+ bill.getMemberAccount().getMemberId() + " is not enough to pay the bill with id " + bill.getBillId());
        }
    }

    public Bill getBill(int billId) {
        return billRepository.findById(billId).orElseThrow(() ->
                new IllegalStateException("Bill with id " + billId + " does not exist."));
    }
}
