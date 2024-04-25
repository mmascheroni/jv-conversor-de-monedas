package com.conversor.models;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Moneda {

    @SerializedName("base_code")
    private String monedaBase;

    @SerializedName("conversion_rates")
    private Map<String, Float> conversiones;
    private String conversionMonedaBuscada;
    private float conversion;

    public Moneda() {
    }

    public Moneda(String monedaBase, String conversionMonedaBuscada, float conversion) {
        this.monedaBase = monedaBase;
        this.conversionMonedaBuscada = conversionMonedaBuscada;
        this.conversion = conversion;
    }

    public Moneda(String monedaBase, Map<String, Float> conversiones, String conversionMonedaBuscada, float conversion) {
        this.monedaBase = monedaBase;
        this.conversiones = conversiones;
        this.conversionMonedaBuscada = conversionMonedaBuscada;
        this.conversion = conversion;
    }

    public String getMonedaBase() {
        return monedaBase;
    }

    public void setMonedaBase(String monedaBase) {
        this.monedaBase = monedaBase;
    }

    public String getConversionMonedaBuscada() {
        return conversionMonedaBuscada;
    }

    public void setConversionMonedaBuscada(String conversionMonedaBuscada) {
        this.conversionMonedaBuscada = conversionMonedaBuscada;
    }

    public float getConversion() {
        return conversion;
    }

    public void setConversion(float conversion) {
        this.conversion = conversion;
    }

    public Map<String, Float> getConversiones() {
        return conversiones;
    }

    public void setConversiones(Map<String, Float> conversiones) {
        this.conversiones = conversiones;
    }

    public Float obtenerConversion(String clave) {
        return conversiones.get(clave);
    }

    @Override
    public String toString() {
        return "Moneda{" +
                "monedaBase='" + monedaBase + '\'' +
                ", conversiones=" + conversiones +
                ", conversionMonedaBuscada='" + conversionMonedaBuscada + '\'' +
                ", conversion=" + conversion +
                '}';
    }
}
