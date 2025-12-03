package com.sakila.models;

/**
 * Modelo Cliente simple (solo id por ahora)
 */
public class Cliente extends EntidadAbstracta {
    private String nombre;

    public Cliente(int idRegistro) { this.idRegistro = idRegistro; }
    public Cliente() {}
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}