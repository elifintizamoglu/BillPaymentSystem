package com.elifintizam.BillPaymentSystem;

import com.elifintizam.BillPaymentSystem.Model.Bill;
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
        //billRepository.findById(bill.getMemberAccount().getId());

        billRepository.save(bill);
        System.out.println(bill.getMemberAccount()+" /// "+ bill.getMemberAccount().getFirstName());
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
}
