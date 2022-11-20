package com.drip.banco.exception;

public class ParametroTransferenciaExeption    extends RuntimeException {
    String message;
    public ParametroTransferenciaExeption() {}
    public ParametroTransferenciaExeption(String msg)
    {
        super(msg);
        this.message = msg;
    }
}
