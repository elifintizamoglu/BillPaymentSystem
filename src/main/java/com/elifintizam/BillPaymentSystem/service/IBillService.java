package com.elifintizam.BillPaymentSystem.service;

import com.elifintizam.BillPaymentSystem.model.Bill;
import java.util.Date;
import java.util.List;

public interface IBillService {

    List<Bill> getBills();

    void postBill(Bill bill);

    void deleteBill(int billId);

    void updateBill(int billId, Double amount, Date processDate, String billType);

    void payBill(int billId, String billType);

    void cancelPayment(int billId, String billType);

    Bill getBill(int billId);
}
