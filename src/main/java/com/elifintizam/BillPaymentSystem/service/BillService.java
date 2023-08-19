package com.elifintizam.BillPaymentSystem.service;

import com.elifintizam.BillPaymentSystem.exception.InsufficientBalanceException;
import com.elifintizam.BillPaymentSystem.model.Bill;
import com.elifintizam.BillPaymentSystem.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

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

    public Bill getBill(int billId) {
        return billRepository.findById(billId).orElseThrow(() ->
                new IllegalStateException("Bill with id " + billId + " does not exist."));
    }

    public void payPhoneBill(int billId) {
        payBill(billId, 0.05);
    }

    public void payInternetBill(int billId) {
        payBill(billId, 0.08);
    }

    public void payWaterBill(int billId) {
        payBill(billId, 0.03);
    }

    private void payBill(int billId, double taxAmount) {
        Bill bill = billRepository.findById(billId).orElseThrow(() ->
                new IllegalStateException("Bill with id " + billId + " does not exist."));
        double totalAmount = bill.getAmount() + (bill.getAmount() * taxAmount);
        if (bill.getMemberAccount().getBalance() >= totalAmount) {
            bill.getMemberAccount().setBalance(bill.getMemberAccount().getBalance() - totalAmount);
            bill.setAmount(0);
        }
        else {
            throw new InsufficientBalanceException("Balance of the member with id "+ bill.getMemberAccount().getMemberId() + " is not enough to pay the bill with id " + bill.getBillId());
        }
    }


}
