package com.sakila.models;

/**
 * Modelo Personal (staff) simple.
 */
public class Personal extends EntidadAbstracta {
    private String nombre;
    public Personal() {}
    public Personal(int idRegistro) { this.idRegistro = idRegistro; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}