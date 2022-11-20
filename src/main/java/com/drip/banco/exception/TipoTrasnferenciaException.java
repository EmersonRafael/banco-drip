package com.drip.banco.exception;

public class TipoTrasnferenciaException
        extends RuntimeException {
    private String message;
    public TipoTrasnferenciaException() {}
    public TipoTrasnferenciaException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}