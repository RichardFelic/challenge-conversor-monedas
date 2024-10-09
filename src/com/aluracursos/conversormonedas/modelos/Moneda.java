package com.aluracursos.conversormonedas.modelos;

public class Moneda {
    private String codigo;
    private double tasaConversion;

    public Moneda(String codigo, double tasaConversion) {
        this.codigo = codigo;
        this.tasaConversion = tasaConversion;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getTasaConversion() {
        return tasaConversion;
    }

    @Override
    public String toString() {
        return codigo + ": " + tasaConversion;
    }
}
