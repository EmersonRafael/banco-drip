package com.drip.banco.exception;

public class ContaException
        extends RuntimeException {
    private String message;
    public ContaException() {}
    public ContaException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}