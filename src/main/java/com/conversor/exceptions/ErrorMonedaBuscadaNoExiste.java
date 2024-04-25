package com.conversor.exceptions;

public class ErrorMonedaBuscadaNoExiste extends RuntimeException {
    private String mensaje;

    public ErrorMonedaBuscadaNoExiste(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return this.mensaje;
    }
}
