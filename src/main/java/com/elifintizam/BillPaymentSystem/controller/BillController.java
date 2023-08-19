package com.elifintizam.BillPaymentSystem.controller;

import com.elifintizam.BillPaymentSystem.Client;
import com.elifintizam.BillPaymentSystem.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/bill")
public class BillController {

    private final Client client;

    @Autowired
    public BillController(Client client) {
        this.client = client;
    }

    @GetMapping
    public List<Bill> getBills(){
        return client.getBills();
    }

    @PostMapping
    public void postBill(@RequestBody Bill bill){
        client.postBill(bill);
    }

    @DeleteMapping(path = "{billId}")
    public void deleteBill(@PathVariable("billId") int billId){
        client.deleteBill(billId);
    }

    @PutMapping(path = "{billId}")
    public void updateBill(@PathVariable("billId") int billId,
                           @RequestParam(required = false) Double amount,
                           @RequestParam(required = false) Date processDate,
                           @RequestParam(required = false) String billType){
        client.updateBill(billId,amount,processDate,billType);
    }

    @PutMapping(path = "/pay/{billId}")
    public void payBill(@PathVariable("billId") int billId,
                        @RequestParam String billType){
        client.payBill(billId,billType);
    }

    @GetMapping(path = "/get/{billId}")
    public Bill getBill(@PathVariable("billId") int billId){
        return client.getBill(billId);
    }
}
