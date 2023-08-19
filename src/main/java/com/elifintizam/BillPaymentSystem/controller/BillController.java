package com.elifintizam.BillPaymentSystem.controller;

import com.elifintizam.BillPaymentSystem.service.BillServiceImpl;
import com.elifintizam.BillPaymentSystem.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/bill")
public class BillController {

    private final BillServiceImpl billServiceImpl;

    @Autowired
    public BillController(BillServiceImpl billServiceImpl) {
        this.billServiceImpl = billServiceImpl;
    }

    @GetMapping
    public List<Bill> getBills() {
        return billServiceImpl.getBills();
    }

    @PostMapping
    public void postBill(@RequestBody Bill bill) {
        billServiceImpl.postBill(bill);
    }

    @DeleteMapping(path = "{billId}")
    public void deleteBill(@PathVariable("billId") int billId) {
        billServiceImpl.deleteBill(billId);
    }

    @PutMapping(path = "{billId}")
    public void updateBill(@PathVariable("billId") int billId,
                           @RequestParam(required = false) Double amount,
                           @RequestParam(required = false) Date processDate,
                           @RequestParam(required = false) String billType) {
        billServiceImpl.updateBill(billId, amount, processDate, billType);
    }

    @PutMapping(path = "/pay/{billId}")
    public void payBill(@PathVariable("billId") int billId,
                        @RequestParam String billType) {
        billServiceImpl.payBill(billId, billType);
    }

    @PutMapping(path = "/pay/cancel/{billId}")
    public void cancelPayment(@PathVariable("billId") int billId,
                              @RequestParam String billType) {
        billServiceImpl.cancelPayment(billId, billType);
    }

    @GetMapping(path = "/get/{billId}")
    public Bill getBill(@PathVariable("billId") int billId) {
        return billServiceImpl.getBill(billId);
    }
}
