package com.conversor.exceptions;

public class ErrorMonedaBaseNoExiste extends RuntimeException {
    private String mensaje;

    public ErrorMonedaBaseNoExiste(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return this.mensaje;
    }
}
