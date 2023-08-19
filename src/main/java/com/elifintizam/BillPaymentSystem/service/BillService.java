package com.elifintizam.BillPaymentSystem.service;

import com.elifintizam.BillPaymentSystem.exception.InsufficientBalanceException;
import com.elifintizam.BillPaymentSystem.exception.WrongBillTypeException;
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

    private double getTaxAmount(String billType) {
        double taxAmount;
        switch (billType) {
            case "Phone" -> taxAmount = 0.05;
            case "Internet" -> taxAmount = 0.08;
            case "Water" -> taxAmount = 0.03;
            default -> taxAmount = 0;
        }
        return taxAmount;
    }

    private double calculateTotalAmount(double billAmount, double taxAmount) {
        return billAmount + (billAmount * taxAmount);
    }

    public void payBill(int billId, String billType) {
        Bill bill = billRepository.findById(billId).orElseThrow(() ->
                new IllegalStateException("Bill with id " + billId + " does not exist."));
        if(bill.isPayed()){
            throw new IllegalStateException("Bill is already paid!");
        }
        if(bill.getBillType().equals(billType)){
            double totalAmount = calculateTotalAmount(bill.getAmount(), getTaxAmount(billType));
            if (bill.getMemberAccount().getBalance() >= totalAmount) {
                bill.getMemberAccount().setBalance(bill.getMemberAccount().getBalance() - totalAmount);
                bill.setPayed(true);
            } else {
                throw new InsufficientBalanceException("Balance of the member with id " + bill.getMemberAccount().getMemberId() + " is not enough to pay the bill with id " + bill.getBillId());
            }
        }
        else{
            throw new WrongBillTypeException("Bill type is wrong or empty!");
        }
    }

    public void cancelPayment(int billId, String billType) {
        Bill bill = billRepository.findById(billId).orElseThrow(() ->
                new IllegalStateException("Bill with id " + billId + " does not exist."));
        if (bill.isPayed()) {
            bill.getMemberAccount().setBalance(bill.getMemberAccount().getBalance() + calculateTotalAmount(bill.getAmount(), getTaxAmount(billType)));
            bill.setPayed(false);
        } else {
            throw new IllegalStateException("Bill is not payed. Cancellation can not be done.");
        }
    }

    public Bill getBill(int billId) {
        return billRepository.findById(billId).orElseThrow(() ->
                new IllegalStateException("Bill with id " + billId + " does not exist."));
    }
}
