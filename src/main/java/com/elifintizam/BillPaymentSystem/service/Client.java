package com.elifintizam.BillPaymentSystem.service;

import com.elifintizam.BillPaymentSystem.model.Bill;
import com.elifintizam.BillPaymentSystem.repository.BillRepository;
import com.elifintizam.BillPaymentSystem.service.BillService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class Client extends BillService {

    public Client(BillRepository billRepository) {
        super(billRepository);
    }

    public List<Bill> getBills() {
        return super.getBills();
    }

    public void postBill(Bill bill) {
        super.postBill(bill);
    }

    public void deleteBill(int billId) {
        super.deleteBill(billId);
    }

    @Transactional
    public void updateBill(int billId, Double amount, Date processDate, String billType) {
        super.updateBill(billId, amount, processDate, billType);
    }

    @Transactional
    public void payBill(int billId, String billType) {
        super.payBill(billId, billType);
    }

    @Transactional
    public void cancelPayment(int billId, String billType) {
        super.cancelPayment(billId, billType);
    }

    public Bill getBill(int billId) {
        return super.getBill(billId);
    }


}
