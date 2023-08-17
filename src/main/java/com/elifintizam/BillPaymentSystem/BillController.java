package com.elifintizam.BillPaymentSystem;

import com.elifintizam.BillPaymentSystem.Model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/bill")
public class BillController {

    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping
    public List<Bill> getBills(){
        return billService.getBills();
    }

    @PostMapping
    public void postBill(@RequestBody Bill bill){
        billService.postBill(bill);
    }

    @DeleteMapping(path = "{billId}")
    public void deleteBill(@PathVariable("billId") int billId){
        billService.deleteBill(billId);
    }

    @PutMapping(path = "{billId}")
    public void updateBill(@PathVariable("billId") int billId,
                           @RequestParam(required = false) Double amount,
                           @RequestParam(required = false) Date processDate,
                           @RequestParam(required = false) String billType){
        billService.updateBill(billId,amount,processDate,billType);
    }
}
