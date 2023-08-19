package com.elifintizam.BillPaymentSystem.exception;

public class WrongBillTypeException extends RuntimeException{
    public WrongBillTypeException(String message) {
        super(message);
    }
}
