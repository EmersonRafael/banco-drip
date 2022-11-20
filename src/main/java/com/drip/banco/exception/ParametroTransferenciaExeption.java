package com.drip.banco.exception;

public class ParametroTransferenciaExeption    extends RuntimeException {
    private String message;
    public ParametroTransferenciaExeption() {}
    public ParametroTransferenciaExeption(String msg)
    {
        super(msg);
        this.message = msg;
    }
}
