package com.drip.banco.exception;

public class TipoTrasnferenciaException
        extends RuntimeException {
    String message;
    public TipoTrasnferenciaException() {}
    public TipoTrasnferenciaException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}