package br.com.boteco.comanda.exception;

public class SQLException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public SQLException(String msg) {
        super(msg);
    }
    public SQLException(String msg, Throwable cause) {
        super(msg, cause);
    }
}